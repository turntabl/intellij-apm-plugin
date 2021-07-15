package io.turntabl.jetty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class MetricGraphHandler extends HttpServlet {
    private String cpuLoadString;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.endsWith("cpu-load")) {
            resp.getWriter().println(cpuLoadString);
            resp.setStatus(HttpServletResponse.SC_OK);
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
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("{ \"status\": \"ok\"}");
    }
}
