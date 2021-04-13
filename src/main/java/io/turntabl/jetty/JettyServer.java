package io.turntabl.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer implements Runnable {
    @Override
    public void run() {
        Server server = new Server(8787);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(io.turntabl.jetty.MetricHandler.class, "/metrics");
        server.setHandler(handler);

        try {
            server.start();
            System.out.println("Jetty server started on port 8787");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
