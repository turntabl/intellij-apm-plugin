package io.turntabl.jetty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class MetricGraphHandler extends HttpServlet {
    private String cpuLoadString;
    private String heapSummaryBeforeGC;
    private String heapSummaryAfterGC;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        resp.setStatus(HttpServletResponse.SC_OK);

        if (url.endsWith("cpu-load")) {
            resp.getWriter().println(cpuLoadString);
        } else if (url.endsWith("heap-summary-before-gc")) {
            resp.getWriter().println(heapSummaryBeforeGC);
        } else if (url.endsWith("heap-summary-after-gc")) {
            resp.getWriter().println(heapSummaryAfterGC);
        } else {
            resp.getWriter().println("Request Not Found");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.endsWith("cpu-load")) {
            cpuLoadString = req.getReader().lines().collect(Collectors.joining());
        } else if (url.endsWith("heap-summary-before-gc")) {
            heapSummaryBeforeGC = req.getReader().lines().collect(Collectors.joining());
        } else if (url.endsWith("heap-summary-after-gc")) {
            heapSummaryAfterGC = req.getReader().lines().collect(Collectors.joining());
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("{ \"status\": \"ok\"}");
    }
}
