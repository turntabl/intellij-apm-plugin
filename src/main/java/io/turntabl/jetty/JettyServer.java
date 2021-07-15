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
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;

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
        handler.addServletWithMapping(new ServletHolder(new MetricGraphHandler()), "/mg/*");

        WebAppContext webAppContext = new WebAppContext();
        try {
            webAppContext.setResourceBase(String.valueOf(Resource.newResource(new URL(JettyServer.class.getResource("/webapp/html/flame_graph.html"), "."))));
            webAppContext.setClassLoader(JettyServer.class.getClassLoader());
            webAppContext.setContextPath("/flame-graph");
            webAppContext.setWelcomeFiles(new String[]{"flame_graph.html"});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        WebAppContext webAppContext2 = new WebAppContext();
        try {
            webAppContext2.setResourceBase(String.valueOf(Resource.newResource(new URL(JettyServer.class.getResource("/webapp/html/flame_graph_no_thread_names.html"), "."))));
            webAppContext2.setClassLoader(JettyServer.class.getClassLoader());
            webAppContext2.setContextPath("/flame-graph-no-thread-names");
            webAppContext2.setWelcomeFiles(new String[]{"flame_graph_no_thread_names.html"});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        WebAppContext webAppContext3 = new WebAppContext();
        try {
            webAppContext3.setResourceBase(String.valueOf(Resource.newResource(new URL(JettyServer.class.getResource("/webapp/html/cpu_load.html"), "."))));
            webAppContext3.setClassLoader(JettyServer.class.getClassLoader());
            webAppContext3.setContextPath("/cpu-load-file");
            webAppContext3.setWelcomeFiles(new String[]{"cpu_load.html"});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { webAppContext, webAppContext2, webAppContext3, handler });
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
}
