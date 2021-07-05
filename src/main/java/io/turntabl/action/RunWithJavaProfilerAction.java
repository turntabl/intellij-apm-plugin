package io.turntabl.action;

import com.intellij.execution.Executor;
import com.intellij.execution.ProgramRunnerUtil;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.execution.application.ApplicationConfigurationType;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiJavaFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class RunWithJavaProfilerAction extends AnAction {
    private HashMap<String, String> environmentVariables = new HashMap<>();
    private String vmOptions;

    public RunWithJavaProfilerAction() {
        super(IconLoader.getIcon("/icons/play_icon.png"));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project currentProject = e.getProject();
        String jarFolderName = currentProject.getName();
        jarFolderName = jarFolderName.replace("-", "_");
        jarFolderName = jarFolderName.replace(".", "_");


        String projectJarPath = "./out/artifacts/" + jarFolderName + "_jar/" + currentProject.getName() + ".jar";
        // versions 2020.1 and above
        // windows
        String jfrJarPath = System.getenv("APPDATA") + "\\JetBrains\\IdeaIC2021.1\\plugins\\profiler\\lib\\jfr-daemon-1.2.0-SNAPSHOT.jar";

        //macOs
        // ~/Library/Application Support/JetBrains/IntelliJIdea2021.1/plugins/profiler...

        //linux
        // ~/.local/share/JetBrains/IntelliJIdea2021.1/profiler...

        @Nullable
        Module module = ModuleUtil.findModuleForFile(currentProject.getProjectFile(), currentProject);

        environmentVariables.put("METRICS_INGEST_URI", "http://localhost:8787/metrics");
        environmentVariables.put("EVENTS_INGEST_URI", "http://localhost:8787/events");
        environmentVariables.put("INSIGHTS_INSERT_KEY", "");
        vmOptions = "-javaagent:" + jfrJarPath + " -jar " + projectJarPath;
        System.out.println(vmOptions);

        PsiJavaFile psiJavaFile = (PsiJavaFile)e.getData(CommonDataKeys.PSI_FILE);
        PsiClass psiClass = psiJavaFile.getClasses()[0];

        // run settings
        RunManager runManager = RunManager.getInstance(currentProject);

        // get main configuration type, then factory from it
        RunnerAndConfigurationSettings runnerAndConfigurationSettings = runManager.createConfiguration("NewRelic Profiler", new ApplicationConfigurationType().getConfigurationFactories()[0]);

        ApplicationConfiguration applicationConfiguration = (ApplicationConfiguration) runnerAndConfigurationSettings.getConfiguration();

        applicationConfiguration.setEnvs(environmentVariables);
        applicationConfiguration.setVMParameters(vmOptions);
        applicationConfiguration.setModule(module);
        applicationConfiguration.setWorkingDirectory(currentProject.getBasePath());
        applicationConfiguration.setMainClass(psiClass);

        // run automatically on click
        Executor runExecutor = new DefaultRunExecutor();
        ProgramRunnerUtil.executeConfiguration(runnerAndConfigurationSettings, runExecutor);
    }
}

