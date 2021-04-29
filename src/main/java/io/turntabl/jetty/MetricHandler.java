package io.turntabl.jetty;

import io.turntabl.ui.NewRelicJavaProfilerToolWindow;
import io.turntabl.ui.model.CpuLoad;
import io.turntabl.ui.operating_system.CpuLoadPanel;
import io.turntabl.utils.JsonUtility;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MetricHandler extends HttpServlet {
    private final NewRelicJavaProfilerToolWindow toolWindowComponent;
    private final Logger logger = LoggerFactory.getLogger(MetricHandler.class);
    private final ServletUtils servletUtils = new ServletUtils();
    private final JsonUtility jsonUtil = new JsonUtility();

    public MetricHandler() {
        toolWindowComponent = null;
    }

    public MetricHandler(NewRelicJavaProfilerToolWindow toolWindowComponent) {
        this.toolWindowComponent = toolWindowComponent;
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String decompressedString = servletUtils.decompress(req);
        updateCpuLoadPanel(decompressedString); //update the cpuload table
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("{ \"status\": \"ok\"}");
        toolWindowComponent.getContent().repaint();
        toolWindowComponent.getContent().validate();
    }

    public void updateCpuLoadPanel(String jsonString) {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        if (jsonArray.isPresent()) {
            List<CpuLoad> cpuLoadList = jsonUtil.getCPULoad(jsonArray.get());
            toolWindowComponent.getMetricsTree().getCpuLoadTable().setModel(new CpuLoadPanel.CpuLoadTableModel(cpuLoadList));
            toolWindowComponent.getMetricsTree().updateComponentMap("CPU Load", (new CpuLoadPanel(new CpuLoadPanel.CpuLoadTableModel(cpuLoadList))).getCpuLoadComponent());
        }
    }
}
