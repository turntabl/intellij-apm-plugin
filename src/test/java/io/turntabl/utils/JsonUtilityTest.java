package io.turntabl.utils;

import io.turntabl.model.events.JfrMethodSample;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class JsonUtilityTest {
    static String metricsJson, eventsJson;
    static JfrMethodSampleUtil jfrMethodSampleUtil;
    private static JsonUtility jsonUtility;

    @BeforeAll
    public static void setupAll() {
        System.out.println("Setup started.............");
        jsonUtility = new JsonUtility();
        jfrMethodSampleUtil = new JfrMethodSampleUtil(jsonUtility);
        metricsJson = "[\n" +
                "    {\n" +
                "        \"common\": {\n" +
                "            \"attributes\": {\n" +
                "                \"instrumentation.name\": \"JFR\",\n" +
                "                \"host.hostname\": \"DESKTOP-8AO41P3/172.21.224.1\",\n" +
                "                \"collector.name\": \"JFR-Uploader\",\n" +
                "                \"instrumentation.provider\": \"JFR-Uploader\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"metrics\": [\n" +
                "            {\n" +
                "                \"name\": \"jfr.ThreadAllocationStatistics.allocated\",\n" +
                "                \"type\": \"gauge\",\n" +
                "                \"value\": 1.95738E7,\n" +
                "                \"timestamp\": 1619441624895,\n" +
                "                \"attributes\": {\n" +
                "                    \"thread.osName\": \"main\",\n" +
                "                    \"thread.name\": \"main\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"jfr.ThreadAllocationStatistics.allocated\",\n" +
                "                \"type\": \"gauge\",\n" +
                "                \"value\": 0.0,\n" +
                "                \"timestamp\": 1619441624895,\n" +
                "                \"attributes\": {\n" +
                "                    \"thread.osName\": \"Reference Handler\",\n" +
                "                    \"thread.name\": \"Reference Handler\"\n" +
                "                }\n" +
                "            }]}]";

        eventsJson = "[\n" +
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
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readMetricsJson() {
        Optional<JSONArray> metricArray = jsonUtility.readMetricsJson(metricsJson);
        assertTrue(metricArray.isPresent());
        assertEquals(2, metricArray.get().size());
    }

    @Test
    void readEventsJson() {
        assertTrue(jsonUtility.readEventsJson(eventsJson) instanceof Stream);
    }

    @Test
    void getStackPayload() {
        List<JfrMethodSample> jfrMethodSampleList = jfrMethodSampleUtil.getJfrMethodSample(eventsJson);
        JSONArray stackTrace = jsonUtility.getStackPayload(jfrMethodSampleList.get(0).getStackTrace());
        assertFalse(stackTrace.isEmpty(), "Stack trace array empty");
        assertEquals(19, stackTrace.size(), "Size does not match");
    }

    @Test
    void getStream() {
    }

    @Test
    void getTime() {
    }


}