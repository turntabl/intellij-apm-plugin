package io.turntabl.jetty;

import io.turntabl.ui.CpuGraph;
import io.turntabl.ui.NewRelicJavaProfilerToolWindow;
import io.turntabl.ui.flight_recorder.SummaryMetaspacePanel;
import io.turntabl.ui.model.CpuLoad;
import io.turntabl.ui.model.SummaryMetaspace;
import io.turntabl.ui.operating_system.CpuLoadPanel;
import io.turntabl.utils.CPULoadUtil;
import io.turntabl.utils.JsonUtility;
import io.turntabl.utils.SummaryMetaspaceUtil;
import org.jfree.data.xy.XYDataset;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private final SummaryMetaspaceUtil summaryMetaspaceUtil = new SummaryMetaspaceUtil(jsonUtil);
    private List<SummaryMetaspace> cumulativeSummaryMetaspaceList = new ArrayList<>();
    public MetricHandler() {
        toolWindowComponent = null;
    }

    public MetricHandler(NewRelicJavaProfilerToolWindow toolWindowComponent) {
        this.toolWindowComponent = toolWindowComponent;
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String decompressedString = servletUtils.decompress(req);
        updateCpuLoadPanel(decompressedString); //update the cpuload table
        updateSummaryMetaspacePanel(decompressedString);

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

    public void updateSummaryMetaspacePanel(String jsonString) {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()) {
            List<SummaryMetaspace> summaryMetaspaceList = summaryMetaspaceUtil.getSummaryMetaspaceMetric(jsonArray.get());
            List<SummaryMetaspace> consolidatedList = summaryMetaspaceUtil.getSummaryMetaspaceConsolidated(summaryMetaspaceList);

            cumulativeSummaryMetaspaceList.addAll(consolidatedList);

            toolWindowComponent.getMetricsTree().getCpuLoadTable().setModel(new SummaryMetaspacePanel.SummaryMetaspaceTableModel(cumulativeSummaryMetaspaceList));
            toolWindowComponent.getMetricsTree().updateComponentMap("Summary Metaspace", (new SummaryMetaspacePanel(new SummaryMetaspacePanel.SummaryMetaspaceTableModel(cumulativeSummaryMetaspaceList))).getSummaryMetaspaceComponent());

            //XYDataset dataset = summaryMetaspaceUtil.createDataSet(cumulativeSummaryMetaspaceList);
            //toolWindowComponent.getMetricsTree().updateCpuLoadGraph(new CpuGraph(dataset, "CPU Load Metric", "Values", "Start Time"));
        }
    }
}
