package io.turntabl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import io.turntabl.jetty.JettyServer;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewRelicJavaProfiler implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow = new NewRelicJavaProfilerToolWindow(toolWindow);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(newRelicJavaProfilerToolWindow.getContent(), "NewRelic Profiler", false);
        toolWindow.getContentManager().addContent(content);

        //run the jetty server
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new JettyServer(newRelicJavaProfilerToolWindow));
    }
}
