package io.turntabl.utils;

import io.turntabl.model.events.JavaMonitorWait;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class JavaMonitorWaitUtilTest extends TestCase {
    private static JsonUtility jsonUtil;
    private static JavaMonitorWaitUtil javaMonitorWaitUtil;
    private static String eventsJson;


    @BeforeAll
    public static void setUpAll() {
        jsonUtil = new JsonUtility();
        javaMonitorWaitUtil = new JavaMonitorWaitUtil(jsonUtil);
        eventsJson = "[{\n" +
                "        \"eventType\": \"JfrJavaMonitorWait\", \n" +
                "         \"timestamp\": 1619441645494,\n" +
                "          \"duration\": 28, \n" +
                "           \"stackTrace\": {\"type\":\"stacktrace\",\"language\":\"java\",\"version\":1,\"truncated\":false,\"payload\":[{\"desc\":\"java.lang.Object.wait(J)V\",\"line\":\"-1\",\"bytecodeIndex\":\"0\"},{\"desc\":\"jdk.jfr.internal.PlatformRecorder.takeNap(J)V\",\"line\":\"448\",\"bytecodeIndex\":\"24\"},{\"desc\":\"jdk.jfr.internal.PlatformRecorder.periodicTask()V\",\"line\":\"441\",\"bytecodeIndex\":\"51\"},{\"desc\":\"jdk.jfr.internal.PlatformRecorder.lambda$startDiskMonitor$1()V\",\"line\":\"386\",\"bytecodeIndex\":\"1\"},{\"desc\":\"jdk.jfr.internal.PlatformRecorder$$Lambda$118.778155119.run()V\",\"line\":\"-1\",\"bytecodeIndex\":\"4\"},{\"desc\":\"java.lang.Thread.run()V\",\"line\":\"834\",\"bytecodeIndex\":\"11\"}]}," + " \n" +
                "           \"instrumentation.name\": \"JFR\", \n" +
                "           \"host.hostname\": \"DESKTOP-8AO41P3/172.21.224.1\", \n" +
                "            \"thread.name\": \"JFR Periodic Tasks\", \n" +
                "            \"class\": \"java.lang.Object\", \n" +
                "            \"collector.name\": \"JFR-Uploader\", \n" +
                "            \"instrumentation.provider\": \"JFR-Uploader\", \n" +
                "}]";
    }

    @Test
    void getJavaMonitorWaitList() {
        List<JavaMonitorWait> eventList = javaMonitorWaitUtil.getJavaMonitorWaitList(eventsJson);
        assertEquals(1, eventList.size());
    }
}