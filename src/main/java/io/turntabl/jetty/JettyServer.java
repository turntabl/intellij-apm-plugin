package io.turntabl.jetty;

import io.turntabl.ui.NewRelicJavaProfilerToolWindow;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class JettyServer implements Runnable {
    private final NewRelicJavaProfilerToolWindow toolWindowComponent;

    public JettyServer(NewRelicJavaProfilerToolWindow content) {
        this.toolWindowComponent = content;
    }

    @Override
    public void run() {
        Server server = new Server(8787);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(new ServletHolder(new EventsHandler(toolWindowComponent)), "/events");
        handler.addServletWithMapping(new ServletHolder(new MetricHandler(toolWindowComponent)),"/metrics");
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
