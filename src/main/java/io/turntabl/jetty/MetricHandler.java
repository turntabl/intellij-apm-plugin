package io.turntabl.jetty;

import com.intellij.ui.components.JBTabbedPane;
import io.turntabl.NewRelicJavaProfilerToolWindow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MetricHandler extends HttpServlet {
    private final NewRelicJavaProfilerToolWindow toolWindowComponent;
    Logger logger = Logger.getLogger(MetricHandler.class.getName());

    public MetricHandler() {
        toolWindowComponent = null;
    }

    public MetricHandler(NewRelicJavaProfilerToolWindow toolWindowComponent) {
        this.toolWindowComponent = toolWindowComponent;
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_OK);
        String payload = req.getReader().lines().collect(Collectors.joining());
        logger.info(payload);

        toolWindowComponent.setEventlableText(payload);
        toolWindowComponent.getContent().repaint();
        toolWindowComponent.getContent().validate();

        resp.getWriter().println("acknowledgement: data received successfully");
    }


}
