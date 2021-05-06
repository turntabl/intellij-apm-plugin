package io.turntabl.jetty;

import io.turntabl.ui.CpuGraph;
import io.turntabl.ui.NewRelicJavaProfilerToolWindow;
import io.turntabl.ui.flight_recorder.JfrSocketReadBytesReadPanel;
import io.turntabl.ui.flight_recorder.JfrSocketReadDurationPanel;
import io.turntabl.ui.model.CpuLoad;
import io.turntabl.ui.model.ThreadCpuLoad;
import io.turntabl.ui.model.JfrSocketReadBytesRead;
import io.turntabl.ui.model.JfrSocketReadDuration;
import io.turntabl.ui.operating_system.CpuLoadPanel;
import io.turntabl.ui.operating_system.ThreadCpuLoadPanel;
import io.turntabl.utils.CPULoadUtil;
import io.turntabl.utils.JfrSocketReadUtil;
import io.turntabl.utils.JsonUtility;
import io.turntabl.utils.ThreadCpuLoadUtil;
import org.jfree.data.xy.XYDataset;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MetricHandler extends HttpServlet {
    private final NewRelicJavaProfilerToolWindow toolWindowComponent;
    private final Logger logger = LoggerFactory.getLogger(MetricHandler.class);
    private final ServletUtils servletUtils = new ServletUtils();
    private final JsonUtility jsonUtil = new JsonUtility();
    private final CPULoadUtil cpuLoadUtil = new CPULoadUtil(jsonUtil);
    private List<CpuLoad> cumulativeCpuLoadList = new ArrayList<>();
    private  final ThreadCpuLoadUtil threadCpuLoadUtil = new ThreadCpuLoadUtil(jsonUtil);
    private List<ThreadCpuLoad> cumulativeThreadCpuLoadList = new ArrayList<>();

    private final JfrSocketReadUtil jfrSocketReadUtil = new JfrSocketReadUtil(jsonUtil);
    private List<JfrSocketReadBytesRead> cumulativeBytesReadList = new ArrayList<>();
    private List<JfrSocketReadDuration> cumulativeDurationList = new ArrayList<>();

    public MetricHandler() {
        toolWindowComponent = null;
    }

    public MetricHandler(NewRelicJavaProfilerToolWindow toolWindowComponent) {
        this.toolWindowComponent = toolWindowComponent;
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String decompressedString = servletUtils.decompress(req);

        updateCpuLoadPanel(decompressedString); //update the cpuload table
        updateThreadLoadPanel(decompressedString); //update the threadCpuLoad table

        updateJfrSocketReadPanels(decompressedString);

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("{ \"status\": \"ok\"}");

//        toolWindowComponent.getContent().repaint();
//        toolWindowComponent.getContent().validate();
    }

    public void updateCpuLoadPanel(String jsonString) {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()) {
            List<CpuLoad> cpuLoadList = cpuLoadUtil.getCPULoad(jsonArray.get());
            List<CpuLoad> consolidatedList = cpuLoadUtil.getCPULoadConsolidated(cpuLoadList);

            cumulativeCpuLoadList.addAll(consolidatedList);

            toolWindowComponent.getMetricsTree().getCpuLoadTable().setModel(new CpuLoadPanel.CpuLoadTableModel(cumulativeCpuLoadList));
            toolWindowComponent.getMetricsTree().updateComponentMap("CPU Load", (new CpuLoadPanel(new CpuLoadPanel.CpuLoadTableModel(cumulativeCpuLoadList))).getCpuLoadComponent());

            XYDataset dataset = cpuLoadUtil.createDataSet(cumulativeCpuLoadList);
            toolWindowComponent.getMetricsTree().updateCpuLoadGraph(new CpuGraph(dataset, "CPU Load Metric", "Values", "Start Time"));
        }


    }

    public void updateThreadLoadPanel(String jsonString) {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        if (jsonArray.isPresent()) {
            List<ThreadCpuLoad> threadCpuLoadList = threadCpuLoadUtil.getThreadCpuLoad(jsonArray.get());
            List<ThreadCpuLoad> consolidatedList = threadCpuLoadUtil.getThreadCpuLoadConsolidated(threadCpuLoadList);

            cumulativeThreadCpuLoadList.addAll(consolidatedList);
            toolWindowComponent.getMetricsTree().getThreadCpuTable().setModel(new ThreadCpuLoadPanel.ThreadCpuLoadTableModel(cumulativeThreadCpuLoadList));
            toolWindowComponent.getMetricsTree().updateComponentMap("Thread CPU Load",(new ThreadCpuLoadPanel(new ThreadCpuLoadPanel.ThreadCpuLoadTableModel(cumulativeThreadCpuLoadList))).getThreadCpuLoadComponent());




        }
    }

    private void updateJfrSocketReadPanels(String jsonString){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()){
            List<JfrSocketReadBytesRead> bytesReadList = jfrSocketReadUtil.getJfrSocketReadBytesRead(jsonArray.get());
            List<JfrSocketReadDuration> durationList = jfrSocketReadUtil.getJfrSocketReadDuration(jsonArray.get());

            cumulativeBytesReadList.addAll(bytesReadList);
            cumulativeDurationList.addAll(durationList);

            TableModel bytesReadTableModel = new JfrSocketReadBytesReadPanel.JfrSocketReadBytesReadTableModel(cumulativeBytesReadList);
            TableModel durationTableModel = new JfrSocketReadDurationPanel.JfrSocketReadDurationTableModel(cumulativeDurationList);

            toolWindowComponent.getMetricsTree().getJfrSocketReadBytesReadTable().setModel(bytesReadTableModel);
            toolWindowComponent.getMetricsTree().getJfrSocketReadDurationTable().setModel(durationTableModel);

            toolWindowComponent.getMetricsTree().updateComponentMap("Bytes Read", new JfrSocketReadBytesReadPanel(bytesReadTableModel).getJfrSocketReadBytesReadComponent());
            toolWindowComponent.getMetricsTree().updateComponentMap("Duration", new JfrSocketReadDurationPanel(durationTableModel).getJfrSocketReadDurationComponent());
        }
    }
}
