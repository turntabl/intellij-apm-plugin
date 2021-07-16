package io.turntabl.jetty;


import io.turntabl.ui.NewRelicJavaProfilerToolWindow;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.MalformedURLException;
import java.net.URL;


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
        handler.addServletWithMapping(new ServletHolder(new MetricHandler(toolWindowComponent)), "/metrics");
        handler.addServletWithMapping(new ServletHolder(new FlameGraphHandler()), "/fg/*");
        handler.addServletWithMapping(new ServletHolder(new MetricGraphHandler()), "/mg/*");

        WebAppContext webAppContext1 = createWebAppContext("flame_graph.html", "/flame-graph");
        WebAppContext webAppContext2 = createWebAppContext("flame_graph_no_thread_names.html", "/flame-graph-no-thread-names");
        WebAppContext webAppContext3 = createWebAppContext("cpu_load.html", "/cpu-load-file");
        WebAppContext webAppContext4 = createWebAppContext("thread_contextswitch_rate.html", "/thread-contextswitch-rate-file");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{webAppContext1, webAppContext2, webAppContext3, webAppContext4, handler});
        server.setHandler(handlers);

        try {
            Thread.currentThread().setContextClassLoader(JettyServer.class.getClassLoader());
            server.start();
            System.out.println("Jetty Server started on port 8787");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebAppContext createWebAppContext(String fullFilename, String endpoint) {
        WebAppContext webAppContext = new WebAppContext();
        try {
            webAppContext.setResourceBase(String.valueOf(Resource.newResource(new URL(JettyServer.class.getResource("/webapp/html/" + fullFilename), "."))));
            webAppContext.setClassLoader(JettyServer.class.getClassLoader());
            webAppContext.setContextPath(endpoint);
            webAppContext.setWelcomeFiles(new String[]{fullFilename});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return webAppContext;
    }
}
