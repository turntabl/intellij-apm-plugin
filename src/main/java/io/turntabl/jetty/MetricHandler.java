package io.turntabl.jetty;

import io.turntabl.ui.CpuGraph;
import io.turntabl.ui.NewRelicJavaProfilerToolWindow;
import io.turntabl.ui.java_virtual_machine.GcHeapSummaryPanel;
import io.turntabl.ui.model.CpuLoad;
import io.turntabl.ui.model.GcHeapSummary;
import io.turntabl.ui.operating_system.CpuLoadPanel;
import io.turntabl.utils.CPULoadUtil;
import io.turntabl.utils.GcHeapSummaryUtil;
import io.turntabl.utils.JsonUtility;
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
    private final GcHeapSummaryUtil gcHeapSummaryUtil = new GcHeapSummaryUtil(jsonUtil);
    private List<GcHeapSummary> cumulativeGcHeapSummaryList = new ArrayList<>();

    public MetricHandler() {
        toolWindowComponent = null;
    }

    public MetricHandler(NewRelicJavaProfilerToolWindow toolWindowComponent) {
        this.toolWindowComponent = toolWindowComponent;
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String decompressedString = servletUtils.decompress(req);
        updateCpuLoadPanel(decompressedString); //update the cpuload table
        updateGcHeapSummaryPanel(decompressedString);

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

    public void updateGcHeapSummaryPanel(String jsonString){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()){
            List<GcHeapSummary> gcHeapSummaryList = gcHeapSummaryUtil.getGcHeapSummary(jsonArray.get());
            List<GcHeapSummary> consolidatedList = gcHeapSummaryUtil.getGcHeapSummaryConsolidated(gcHeapSummaryList);

            cumulativeGcHeapSummaryList.addAll(consolidatedList);

            TableModel tableModel = new GcHeapSummaryPanel.GcHeapSummaryTableModel(cumulativeGcHeapSummaryList);

            toolWindowComponent.getMetricsTree().getGcHeapSummaryTable().setModel(tableModel);
            toolWindowComponent.getMetricsTree().updateComponentMap("GC Heap Summary", (new GcHeapSummaryPanel(tableModel)).getGcHeapSummaryComponent());
        }
    }
}
