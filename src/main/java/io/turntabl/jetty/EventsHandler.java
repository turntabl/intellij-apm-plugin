package io.turntabl.jetty;

import io.turntabl.model.events.*;
import io.turntabl.ui.NewRelicJavaProfilerToolWindow;
import io.turntabl.ui.flight_recorder.JfrCompilationPanel;
import io.turntabl.ui.java_virtual_machine.JVMInfoEventPanel;
import io.turntabl.ui.java_virtual_machine.JavaMonitorWaitPanel;
import io.turntabl.utils.*;
import io.turntabl.ui.events.JfrMethodSamplePanel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventsHandler extends HttpServlet {
    private NewRelicJavaProfilerToolWindow toolWindowComponent;
    private final ServletUtils servletUtils = new ServletUtils();
    private final JsonUtility jsonUtil = new JsonUtility();
    private final JVMInfoEventUtil jvmInfoEventUtil = new JVMInfoEventUtil(jsonUtil);
    private List<JVMInfoEvent> cumulativeJVMInfoEvents = new ArrayList<>();
    private final JfrCompilationEventUtil jfrCompilationEventUtil = new JfrCompilationEventUtil(jsonUtil);
    private List<JfrCompilation> cumulativeJfrCompilationEvents = new ArrayList<>();
    private final JfrMethodSampleUtil jfrMethodSampleUtil = new JfrMethodSampleUtil(jsonUtil);
    private List<JfrMethodSample> cumulativeJfrMethodSampleList = new ArrayList<>();
    private final JavaMonitorWaitUtil javaMonitorWaitUtil = new JavaMonitorWaitUtil(jsonUtil);
    private List<JavaMonitorWait> cumulativeJavaMonitorWait = new ArrayList<>();
    private Map<String, List<EventStackTrace>> stackTraceMap = new HashMap<>();

    public EventsHandler(NewRelicJavaProfilerToolWindow toolWindowComponent) {
        this.toolWindowComponent = toolWindowComponent;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String decompressedString = servletUtils.decompress(req);
        updateJVMInfoPanel(decompressedString);
        updateJavaMonitorWaitPanel(decompressedString);
        updateJFRCompilationPanel(decompressedString);
        updateJfrMethodSamplePanel(decompressedString);
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("{ \"status\": \"ok\"}");
    }

    private void updateJVMInfoPanel(String jsonString) {
        cumulativeJVMInfoEvents.addAll(jvmInfoEventUtil.getJVMInfoList(jsonString));
        toolWindowComponent.getEventsTree().getJVMInfoTable().setModel(new JVMInfoEventPanel.JVMInfoEventTableModel(cumulativeJVMInfoEvents));
        toolWindowComponent.getEventsTree().updateComponentMap("JVM Information", (new JVMInfoEventPanel(new JVMInfoEventPanel.JVMInfoEventTableModel(cumulativeJVMInfoEvents))).getJVMInfoEventComponent());
    }

    private void updateJfrMethodSamplePanel(String jsonString) {
        cumulativeJfrMethodSampleList.addAll(jfrMethodSampleUtil.getJfrMethodSample(jsonString));

        cumulativeJfrMethodSampleList.forEach(s -> {
            List<EventStackTrace> stackTraceList = jfrMethodSampleUtil.getStackTrace(s.getStackTrace());
            if (stackTraceMap.size() > 0 && stackTraceMap.get(s.getThreadName()) != null) {
                stackTraceMap.get(s.getThreadName()).addAll(stackTraceList);
            } else {
                stackTraceMap.put(s.getThreadName(), stackTraceList);
            }
        });

        try {
            jfrMethodSampleUtil.writeEventStackToFile(stackTraceMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jfrMethodSampleUtil.createFlameGraph();
        System.out.println("created flame graph..............");

        TableModel tableModel = new JfrMethodSamplePanel.JfrMethodSampleTableModel(cumulativeJfrMethodSampleList);

        toolWindowComponent.getEventsTree().getJfrMethodSampleTable().setModel(tableModel);
        toolWindowComponent.getEventsTree().updateComponentMap("JFR Method Sample", (new JfrMethodSamplePanel(tableModel)).getJfrMethodSampleComponent());
    }

    private void updateJFRCompilationPanel(String jsonString) {
        cumulativeJfrCompilationEvents.addAll(jfrCompilationEventUtil.getJfrCompilationList(jsonString));
        toolWindowComponent.getEventsTree().getJVMInfoTable().setModel(new JfrCompilationPanel.JfrCompilationTableModel(cumulativeJfrCompilationEvents));
        toolWindowComponent.getEventsTree().updateComponentMap("JFR Compilation", (new JfrCompilationPanel(new JfrCompilationPanel.JfrCompilationTableModel(cumulativeJfrCompilationEvents))).getJfrCompilationComponent());
    }

    private void updateJavaMonitorWaitPanel(String jsonString) {
        cumulativeJavaMonitorWait.addAll(javaMonitorWaitUtil.getJavaMonitorWaitList(jsonString));
        toolWindowComponent.getEventsTree().getJavaMonitorWaitTable().setModel(new JavaMonitorWaitPanel.JavaMonitorWaitTableModel(cumulativeJavaMonitorWait));
        toolWindowComponent.getEventsTree().updateComponentMap("Java Monitor Wait",
                (new JavaMonitorWaitPanel(new JavaMonitorWaitPanel.JavaMonitorWaitTableModel(cumulativeJavaMonitorWait))).getJavaMonitorWaitComponent());
    }

}
