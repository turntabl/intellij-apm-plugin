package io.turntabl.jetty;

import io.turntabl.ui.NewRelicJavaProfilerToolWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MetricHandler extends HttpServlet {
    private final NewRelicJavaProfilerToolWindow toolWindowComponent;
    private final Logger logger = LoggerFactory.getLogger(MetricHandler.class);
    private final ServletUtils servletUtils = new ServletUtils();

    public MetricHandler() {
        toolWindowComponent = null;
    }

    public MetricHandler(NewRelicJavaProfilerToolWindow toolWindowComponent) {
        this.toolWindowComponent = toolWindowComponent;
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String decompressedString = servletUtils.decompress(req);
        toolWindowComponent.updateMetricsPanelText(decompressedString);
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("{ \"status\": \"ok\"}");
        toolWindowComponent.getContent().repaint();
        toolWindowComponent.getContent().validate();
    }
}
