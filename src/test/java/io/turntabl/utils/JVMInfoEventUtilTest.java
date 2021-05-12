package io.turntabl.utils;

import io.turntabl.model.events.JVMInfoEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JVMInfoEventUtilTest {
    String eventsJson;
    JVMInfoEventUtil jvmInfoEventUtil;
    JsonUtility jsonUtil;


    @BeforeEach
    void setUp() {
        jsonUtil = new JsonUtility();
        jvmInfoEventUtil = new JVMInfoEventUtil(jsonUtil);
        eventsJson = "[{\n" +
                "        \"eventType\": \"JfrJVMInformation\",\n" +
                "        \"timestamp\": 1619441624894,\n" +
                "        \"jvmProperty\": \"java.vm.version\",\n" +
                "        \"jvmPropertyValue\": \"11.0.10+8-LTS-162\",\n" +
                "        \"instrumentation.name\": \"JFR\",\n" +
                "        \"host.hostname\": \"DESKTOP-8AO41P3/172.21.224.1\",\n" +
                "        \"collector.name\": \"JFR-Uploader\",\n" +
                "        \"instrumentation.provider\": \"JFR-Uploader\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"eventType\": \"JfrJVMInformation\",\n" +
                "        \"timestamp\": 1619441624894,\n" +
                "        \"jvmProperty\": \"java.vm.name\",\n" +
                "        \"jvmPropertyValue\": \"Java HotSpot(TM) 64-Bit Server VM\",\n" +
                "        \"instrumentation.name\": \"JFR\",\n" +
                "        \"host.hostname\": \"DESKTOP-8AO41P3/172.21.224.1\",\n" +
                "        \"collector.name\": \"JFR-Uploader\",\n" +
                "        \"instrumentation.provider\": \"JFR-Uploader\"\n" +
                "    }]";
    }

    @Test
    void getJVMInfoList() {
        List<JVMInfoEvent> eventList = jvmInfoEventUtil.getJVMInfoList(eventsJson);
        assertEquals(2, eventList.size());
    }
}