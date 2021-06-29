package io.turntabl.jetty;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class FlameGraphHandler extends HttpServlet {
    private String jsonString;
    private String jsonStringWithoutThreadNames;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = req.getRequestURI();
        resp.setStatus(HttpServletResponse.SC_OK);

        if (url.endsWith("with-thread-names")) {
            resp.getWriter().println(jsonString);
        } else if (url.endsWith("without-thread-names")) {
            resp.getWriter().println(jsonStringWithoutThreadNames);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = req.getRequestURI();

        if (url.endsWith("with-thread-names")) {
            jsonString = req.getReader().lines().collect(Collectors.joining());
        } else if (url.endsWith("without-thread-names")) {
            jsonStringWithoutThreadNames = req.getReader().lines().collect(Collectors.joining());
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("{ \"status\": \"ok\"}");
    }
}

