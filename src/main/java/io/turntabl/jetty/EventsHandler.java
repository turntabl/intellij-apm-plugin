package io.turntabl.jetty;

import io.turntabl.model.events.JVMInfoEvent;
import io.turntabl.model.events.JfrMethodSample;
import io.turntabl.ui.NewRelicJavaProfilerToolWindow;
import io.turntabl.ui.events.JfrMethodSamplePanel;
import io.turntabl.ui.java_virtual_machine.JVMInfoEventPanel;
import io.turntabl.ui.operating_system.CpuLoadPanel;
import io.turntabl.utils.JVMInfoEventUtil;
import io.turntabl.utils.JfrMethodSampleUtil;
import io.turntabl.utils.JsonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventsHandler extends HttpServlet {
    private NewRelicJavaProfilerToolWindow toolWindowComponent;
    private final Logger logger = LoggerFactory.getLogger(MetricHandler.class);
    private final ServletUtils servletUtils = new ServletUtils();
    private final JsonUtility jsonUtil = new JsonUtility();
    private final JVMInfoEventUtil jvmInfoEventUtil = new JVMInfoEventUtil(jsonUtil);
    private List<JVMInfoEvent> cumulativeJVMInfoEvents = new ArrayList<>();
    private final JfrMethodSampleUtil jfrMethodSampleUtil = new JfrMethodSampleUtil(jsonUtil);
    private List<JfrMethodSample> cumulativeJfrMethodSampleList = new ArrayList<>();

    public EventsHandler(NewRelicJavaProfilerToolWindow toolWindowComponent) {
        this.toolWindowComponent = toolWindowComponent;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String decompressedString = servletUtils.decompress(req);
        updateJVMInfoPanel(decompressedString);
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

    private void updateJfrMethodSamplePanel(String jsonString){
        cumulativeJfrMethodSampleList.addAll(jfrMethodSampleUtil.getJfrMethodSample(jsonString));

        TableModel tableModel = new JfrMethodSamplePanel.JfrMethodSampleTableModel(cumulativeJfrMethodSampleList);

        toolWindowComponent.getEventsTree().getJfrMethodSampleTable().setModel(tableModel);
        toolWindowComponent.getEventsTree().updateComponentMap("JFR Method Sample", (new JfrMethodSamplePanel(tableModel)).getJfrMethodSampleComponent());
    }
}
