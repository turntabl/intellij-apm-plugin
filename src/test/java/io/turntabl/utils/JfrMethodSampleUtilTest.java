package io.turntabl.utils;

import io.turntabl.model.events.EventStackTrace;
import io.turntabl.model.events.JfrMethodSample;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JfrMethodSampleUtilTest {
    JsonUtility jsonUtil = new JsonUtility();
    JfrMethodSampleUtil jfrMethodSampleUtil = new JfrMethodSampleUtil(jsonUtil);
    String jsonString = "[\n" +
            "    {\n" +
            "        \"eventType\": \"JfrMethodSample\",\n" +
            "        \"timestamp\": 1619441624855,\n" +
            "        \"thread.state\": \"STATE_RUNNABLE\",\n" +
            "        \"stackTrace\": \"{\\\"type\\\":\\\"stacktrace\\\",\\\"language\\\":\\\"java\\\",\\\"version\\\":1,\\\"truncated\\\":true,\\\"payload\\\":[{\\\"desc\\\":\\\"jdk.jfr.internal.EventInstrumentation.getEventHandler(Ljdk/internal/org/objectweb/asm/MethodVisitor;)V\\\",\\\"line\\\":\\\"477\\\",\\\"bytecodeIndex\\\":\\\"52\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.EventInstrumentation.lambda$makeInstrumented$0(Ljdk/internal/org/objectweb/asm/MethodVisitor;)V\\\",\\\"line\\\":\\\"336\\\",\\\"bytecodeIndex\\\":\\\"30\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.EventInstrumentation$$Lambda$128.1809249771.accept(Ljava/lang/Object;)V\\\",\\\"line\\\":\\\"-1\\\",\\\"bytecodeIndex\\\":\\\"8\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.EventInstrumentation.updateMethod(Ljdk/internal/org/objectweb/asm/commons/Method;Ljava/util/function/Consumer;)V\\\",\\\"line\\\":\\\"517\\\",\\\"bytecodeIndex\\\":\\\"66\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.EventInstrumentation.makeInstrumented()V\\\",\\\"line\\\":\\\"330\\\",\\\"bytecodeIndex\\\":\\\"10\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.EventInstrumentation.buildInstrumented()[B\\\",\\\"line\\\":\\\"310\\\",\\\"bytecodeIndex\\\":\\\"1\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.JVMUpcalls.onRetransform(JZLjava/lang/Class;[B)[B\\\",\\\"line\\\":\\\"64\\\",\\\"bytecodeIndex\\\":\\\"128\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.JVM.retransformClasses([Ljava/lang/Class;)V\\\",\\\"line\\\":\\\"-1\\\",\\\"bytecodeIndex\\\":\\\"0\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.SettingsManager.updateRetransform(Ljava/util/List;)V\\\",\\\"line\\\":\\\"170\\\",\\\"bytecodeIndex\\\":\\\"110\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.SettingsManager.setSettings(Ljava/util/List;)V\\\",\\\"line\\\":\\\"150\\\",\\\"bytecodeIndex\\\":\\\"136\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.MetadataRepository.setSettings(Ljava/util/List;)V\\\",\\\"line\\\":\\\"200\\\",\\\"bytecodeIndex\\\":\\\"5\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.PlatformRecorder.updateSettingsButIgnoreRecording(Ljdk/jfr/internal/PlatformRecording;)V\\\",\\\"line\\\":\\\"338\\\",\\\"bytecodeIndex\\\":\\\"74\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.PlatformRecorder.updateSettings()V\\\",\\\"line\\\":\\\"327\\\",\\\"bytecodeIndex\\\":\\\"2\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.PlatformRecorder.start(Ljdk/jfr/internal/PlatformRecording;)V\\\",\\\"line\\\":\\\"237\\\",\\\"bytecodeIndex\\\":\\\"168\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.PlatformRecording.start()V\\\",\\\"line\\\":\\\"114\\\",\\\"bytecodeIndex\\\":\\\"65\\\"},{\\\"desc\\\":\\\"jdk.jfr.Recording.start()V\\\",\\\"line\\\":\\\"169\\\",\\\"bytecodeIndex\\\":\\\"4\\\"},{\\\"desc\\\":\\\"com.newrelic.jfr.daemon.agent.FileJfrRecorderFactory.getRecorder()Lcom/newrelic/jfr/daemon/JfrRecorder;\\\",\\\"line\\\":\\\"34\\\",\\\"bytecodeIndex\\\":\\\"59\\\"},{\\\"desc\\\":\\\"com.newrelic.jfr.daemon.JfrController.resetJfrRecorder()V\\\",\\\"line\\\":\\\"79\\\",\\\"bytecodeIndex\\\":\\\"5\\\"},{\\\"desc\\\":\\\"com.newrelic.jfr.daemon.JfrController.loop()V\\\",\\\"line\\\":\\\"60\\\",\\\"bytecodeIndex\\\":\\\"32\\\"},{\\\"desc\\\":\\\"com.newrelic.jfr.daemon.agent.AgentMain.lambda$start$0(Lcom/newrelic/jfr/daemon/JfrController;)V\\\",\\\"line\\\":\\\"49\\\",\\\"bytecodeIndex\\\":\\\"1\\\"},{\\\"desc\\\":\\\"com.newrelic.jfr.daemon.agent.AgentMain$$Lambda$70.709865851.run()V\\\",\\\"line\\\":\\\"-1\\\",\\\"bytecodeIndex\\\":\\\"4\\\"},{\\\"desc\\\":\\\"java.util.concurrent.Executors$RunnableAdapter.call()Ljava/lang/Object;\\\",\\\"line\\\":\\\"515\\\",\\\"bytecodeIndex\\\":\\\"4\\\"},{\\\"desc\\\":\\\"java.util.concurrent.FutureTask.run()V\\\",\\\"line\\\":\\\"264\\\",\\\"bytecodeIndex\\\":\\\"39\\\"},{\\\"desc\\\":\\\"java.util.concurrent.ThreadPoolExecutor.runWorker(Ljava/util/concurrent/ThreadPoolExecutor$Worker;)V\\\",\\\"line\\\":\\\"1128\\\",\\\"bytecodeIndex\\\":\\\"92\\\"},{\\\"desc\\\":\\\"java.util.concurrent.ThreadPoolExecutor$Worker.run()V\\\",\\\"line\\\":\\\"628\\\",\\\"bytecodeIndex\\\":\\\"5\\\"}]}\",\n" +
            "        \"instrumentation.name\": \"JFR\",\n" +
            "        \"host.hostname\": \"DESKTOP-8AO41P3/172.21.224.1\",\n" +
            "        \"thread.name\": \"pool-1-thread-1\",\n" +
            "        \"collector.name\": \"JFR-Uploader\",\n" +
            "        \"instrumentation.provider\": \"JFR-Uploader\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"eventType\": \"JfrMethodSample\",\n" +
            "        \"timestamp\": 1619441625031,\n" +
            "        \"thread.state\": \"STATE_RUNNABLE\",\n" +
            "        \"stackTrace\": \"{\\\"type\\\":\\\"stacktrace\\\",\\\"language\\\":\\\"java\\\",\\\"version\\\":1,\\\"truncated\\\":false,\\\"payload\\\":[{\\\"desc\\\":\\\"jdk.jfr.internal.SettingsManager$InternalSetting.add(Ljdk/jfr/internal/SettingsManager$InternalSetting;)V\\\",\\\"line\\\":\\\"95\\\",\\\"bytecodeIndex\\\":\\\"16\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.SettingsManager.createSettingsMap(Ljava/util/List;)Ljava/util/Map;\\\",\\\"line\\\":\\\"182\\\",\\\"bytecodeIndex\\\":\\\"118\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.SettingsManager.setSettings(Ljava/util/List;)V\\\",\\\"line\\\":\\\"135\\\",\\\"bytecodeIndex\\\":\\\"3\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.MetadataRepository.setSettings(Ljava/util/List;)V\\\",\\\"line\\\":\\\"200\\\",\\\"bytecodeIndex\\\":\\\"5\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.PlatformRecorder.updateSettingsButIgnoreRecording(Ljdk/jfr/internal/PlatformRecording;)V\\\",\\\"line\\\":\\\"338\\\",\\\"bytecodeIndex\\\":\\\"74\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.PlatformRecorder.stop(Ljdk/jfr/internal/PlatformRecording;)V\\\",\\\"line\\\":\\\"298\\\",\\\"bytecodeIndex\\\":\\\"203\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.PlatformRecording.stop(Ljava/lang/String;)Z\\\",\\\"line\\\":\\\"157\\\",\\\"bytecodeIndex\\\":\\\"38\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.PlatformRecording.newSnapshotClone(Ljava/lang/String;Ljava/lang/Boolean;)Ljdk/jfr/internal/PlatformRecording;\\\",\\\"line\\\":\\\"337\\\",\\\"bytecodeIndex\\\":\\\"319\\\"},{\\\"desc\\\":\\\"jdk.jfr.internal.PlatformRecording.dump(Ljdk/jfr/internal/WriteableUserPath;)V\\\",\\\"line\\\":\\\"684\\\",\\\"bytecodeIndex\\\":\\\"11\\\"},{\\\"desc\\\":\\\"jdk.jfr.Recording.dump(Ljava/nio/file/Path;)V\\\",\\\"line\\\":\\\"376\\\",\\\"bytecodeIndex\\\":\\\"17\\\"},{\\\"desc\\\":\\\"com.newrelic.jfr.daemon.agent.FileJfrRecorder.recordToFile()Ljava/nio/file/Path;\\\",\\\"line\\\":\\\"23\\\",\\\"bytecodeIndex\\\":\\\"26\\\"},{\\\"desc\\\":\\\"com.newrelic.jfr.daemon.JfrController.loop()V\\\",\\\"line\\\":\\\"64\\\",\\\"bytecodeIndex\\\":\\\"39\\\"},{\\\"desc\\\":\\\"com.newrelic.jfr.daemon.agent.AgentMain.lambda$start$0(Lcom/newrelic/jfr/daemon/JfrController;)V\\\",\\\"line\\\":\\\"49\\\",\\\"bytecodeIndex\\\":\\\"1\\\"},{\\\"desc\\\":\\\"com.newrelic.jfr.daemon.agent.AgentMain$$Lambda$70.709865851.run()V\\\",\\\"line\\\":\\\"-1\\\",\\\"bytecodeIndex\\\":\\\"4\\\"},{\\\"desc\\\":\\\"java.util.concurrent.Executors$RunnableAdapter.call()Ljava/lang/Object;\\\",\\\"line\\\":\\\"515\\\",\\\"bytecodeIndex\\\":\\\"4\\\"},{\\\"desc\\\":\\\"java.util.concurrent.FutureTask.run()V\\\",\\\"line\\\":\\\"264\\\",\\\"bytecodeIndex\\\":\\\"39\\\"},{\\\"desc\\\":\\\"java.util.concurrent.ThreadPoolExecutor.runWorker(Ljava/util/concurrent/ThreadPoolExecutor$Worker;)V\\\",\\\"line\\\":\\\"1128\\\",\\\"bytecodeIndex\\\":\\\"92\\\"},{\\\"desc\\\":\\\"java.util.concurrent.ThreadPoolExecutor$Worker.run()V\\\",\\\"line\\\":\\\"628\\\",\\\"bytecodeIndex\\\":\\\"5\\\"},{\\\"desc\\\":\\\"java.lang.Thread.run()V\\\",\\\"line\\\":\\\"834\\\",\\\"bytecodeIndex\\\":\\\"11\\\"}]}\",\n" +
            "        \"instrumentation.name\": \"JFR\",\n" +
            "        \"host.hostname\": \"DESKTOP-8AO41P3/172.21.224.1\",\n" +
            "        \"thread.name\": \"pool-1-thread-1\",\n" +
            "        \"collector.name\": \"JFR-Uploader\",\n" +
            "        \"instrumentation.provider\": \"JFR-Uploader\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"eventType\": \"JfrJVMInformation\",\n" +
            "        \"timestamp\": 1619441624892,\n" +
            "        \"jvmVersion\": \"Java HotSpot(TM) 64-Bit Server VM (11.0.10+8-LTS-162) for windows-amd64 JRE (11.0.10+8-LTS-162), built on Dec  8 2020 16:32:57 by \\\"mach5one\\\" with MS VC++ 15.9 (VS2017)\",\n" +
            "        \"jvmArguments\": \"-javaagent:./lib/jfr-daemon-1.2.0-SNAPSHOT.jar\",\n" +
            "        \"jvmStartTime\": 1619441611974,\n" +
            "        \"instrumentation.name\": \"JFR\",\n" +
            "        \"host.hostname\": \"DESKTOP-8AO41P3/172.21.224.1\",\n" +
            "        \"collector.name\": \"JFR-Uploader\",\n" +
            "        \"instrumentation.provider\": \"JFR-Uploader\"\n" +
            "    }]";

    @Test
    void canGetJfrMethodSample() {
        List<JfrMethodSample> extractedList  = jfrMethodSampleUtil.getJfrMethodSample(jsonString);
        assertEquals(2, extractedList.size());
    }

    @Test
    void canGetStackTrace(){
        List<JfrMethodSample> extractedList  = jfrMethodSampleUtil.getJfrMethodSample(jsonString);
        List<EventStackTrace> stackTraceList1 = jfrMethodSampleUtil.getStackTrace(extractedList.get(0).getStackTrace());
        List<EventStackTrace> stackTraceList2 = jfrMethodSampleUtil.getStackTrace(extractedList.get(1).getStackTrace());
        assertEquals(25, stackTraceList1.size());
        assertEquals(19, stackTraceList2.size());
    }
}