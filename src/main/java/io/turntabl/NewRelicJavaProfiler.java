package io.turntabl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import io.turntabl.jetty.JettyServer;
import io.turntabl.ui.NewRelicJavaProfilerToolWindow;
import org.jetbrains.annotations.NotNull;

public class NewRelicJavaProfiler implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        System.out.println("About to start server");
        NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow = new NewRelicJavaProfilerToolWindow(toolWindow, project);

        Thread serverThread = new Thread(new JettyServer(newRelicJavaProfilerToolWindow));
        serverThread.start();

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(newRelicJavaProfilerToolWindow.getContent(), "NewRelic Profiler", false);
        toolWindow.getContentManager().addContent(content);

    }
}
