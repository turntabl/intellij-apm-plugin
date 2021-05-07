package io.turntabl.jetty;

import io.turntabl.ui.CpuGraph;
import io.turntabl.ui.NewRelicJavaProfilerToolWindow;
import io.turntabl.ui.java_application.ObjectAllocationInNewTLabPanel;
import io.turntabl.ui.model.CpuLoad;
import io.turntabl.ui.model.ObjectAllocationInNewTLab;
import io.turntabl.ui.operating_system.CpuLoadPanel;
import io.turntabl.utils.CPULoadUtil;
import io.turntabl.utils.JsonUtility;
import io.turntabl.utils.ObjectAllocationInNewTLabUtil;
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
    private final ObjectAllocationInNewTLabUtil objectAllocationInNewTLabUtil = new ObjectAllocationInNewTLabUtil(jsonUtil);
    private List<ObjectAllocationInNewTLab> cumulativeObjectAllocationList = new ArrayList<>();
    private List<CpuLoad> cumulativeCpuLoadList = new ArrayList<>();

    public MetricHandler() {
        toolWindowComponent = null;
    }

    public MetricHandler(NewRelicJavaProfilerToolWindow toolWindowComponent) {
        this.toolWindowComponent = toolWindowComponent;
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String decompressedString = servletUtils.decompress(req);
        updateCpuLoadPanel(decompressedString); //update the cpuload table
        updateObjectAllocationInNewTLabPanel(decompressedString);

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

    public void updateObjectAllocationInNewTLabPanel(String jsonString) {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()) {
            List<ObjectAllocationInNewTLab> objectAllocationInNewTLabsList = objectAllocationInNewTLabUtil.getObjectAllocationInNewTLab(jsonArray.get());
        cumulativeObjectAllocationList.addAll(objectAllocationInNewTLabsList);

            toolWindowComponent.getMetricsTree().getObjectAllocationInNewTLabTable().setModel(new ObjectAllocationInNewTLabPanel.ObjectAllocationInNewTLabTableModel(cumulativeObjectAllocationList));
            toolWindowComponent.getMetricsTree().updateComponentMap("Object Allocation in new TLAB", (new ObjectAllocationInNewTLabPanel(new ObjectAllocationInNewTLabPanel.ObjectAllocationInNewTLabTableModel(cumulativeObjectAllocationList))).getObjectAllocationInNewTLabComponent());


        }
    }
}
