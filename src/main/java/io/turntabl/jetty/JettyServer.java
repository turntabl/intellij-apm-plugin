package io.turntabl.jetty;


import io.turntabl.ui.NewRelicJavaProfilerToolWindow;

import org.apache.tools.ant.Project;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.nio.file.Paths;


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

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[]{"flame_graph.html"});
//        resourceHandler.setResourceBase("C:/Users/zaneta.asare/Documents/Java/intellij-apm-plugin/src/main/resources");
        resourceHandler.setResourceBase("/home/zaneta-work/Documents/Java/intellij-apm-plugin/src/main/resources");

        ResourceHandler resourceHandler2 = new ResourceHandler();
        resourceHandler2.setDirectoriesListed(true);
        resourceHandler2.setWelcomeFiles(new String[]{"flame_graph_no_thread_names.html"});
//        resourceHandler2.setResourceBase("C:/Users/zaneta.asare/Documents/Java/intellij-apm-plugin/src/main/resources");
        resourceHandler2.setResourceBase("/home/zaneta-work/Documents/Java/intellij-apm-plugin/src/main/resources");

        ContextHandler contextHandler1 = new ContextHandler("/flame-graph");
        contextHandler1.setHandler(resourceHandler);

        ContextHandler contextHandler2 = new ContextHandler("/flame-graph-no-thread-names");
        contextHandler2.setHandler(resourceHandler2);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { contextHandler1, contextHandler2, handler });
        server.setHandler(handlers);

        try {
            server.start();
            System.out.println("Jetty Server started on port 8787");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
