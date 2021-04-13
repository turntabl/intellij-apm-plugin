package io.turntabl.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {
    public static void main(String[] args) {
        Server server = new Server(8787);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(io.turntabl.jetty.MetricHandler.class, "/metrics");
        server.setHandler(handler);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
