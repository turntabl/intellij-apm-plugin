package io.turntabl.ui.flame_graph;

import io.turntabl.utils.flame_graph_util.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void canAddStack() {
        Node rootNode = new Node("root", 0, new HashMap<>());
        List<String> stack1 = new ArrayList<>(Arrays.asList("JFR Periodic Tasks", "java.lang.Thread.run", "jdk.jfr.internal.PlatformRecorder$$Lambda$115+0x0000000800c445f0.1997398351.run", "jdk.jfr.internal.PlatformRecorder.lambda$startDiskMonitor$1", "jdk.jfr.internal.PlatformRecorder.periodicTask", "jdk.jfr.internal.RequestEngine.doPeriodic", "jdk.jfr.internal.RequestEngine.run_requests", "jdk.jfr.internal.RequestEngine$RequestHook.execute", "jdk.jfr.internal.JVM.emitEvent"));
        List<String> stack2 = new ArrayList<>(Arrays.asList("JfrController", "java.lang.Thread.run", "java.util.concurrent.ThreadPoolExecutor$Worker.run", "java.util.concurrent.ThreadPoolExecutor.runWorker", "java.util.concurrent.FutureTask.run", "java.util.concurrent.Executors$RunnableAdapter.call", "com.newrelic.jfr.daemon.JfrController$$Lambda$134+0x0000000800bfeb88.1007611585.run", "com.newrelic.jfr.daemon.JfrController.lambda$loop$1", "com.newrelic.jfr.daemon.JFRUploader.handleFile", "com.newrelic.jfr.daemon.JFRUploader.deleteFile", "java.nio.file.Files.delete", "sun.nio.fs.AbstractFileSystemProvider.delete", "sun.nio.fs.WindowsFileSystemProvider.implDelete", "sun.nio.fs.WindowsNativeDispatcher.DeleteFile", "sun.nio.fs.WindowsNativeDispatcher.DeleteFile0"));

        rootNode.add(stack1, stack1.size() - 1, 1);
        rootNode.add(stack2, stack2.size() - 1, 1);

        assertEquals(2, rootNode.getValue());
    }

}