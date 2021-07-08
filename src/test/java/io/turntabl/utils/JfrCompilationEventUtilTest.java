package io.turntabl.utils;

import io.turntabl.model.events.JVMInfoEvent;
import io.turntabl.model.events.JfrCompilation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JfrCompilationEventUtilTest {

    String eventsJson;
    JfrCompilationEventUtil jfrCompilationEventUtil;
    JsonUtility jsonUtil;


    @BeforeEach
    void setUp() {
        jsonUtil = new JsonUtility();
        jfrCompilationEventUtil = new JfrCompilationEventUtil(jsonUtil);
        eventsJson = "[{\n" +
                "        \"eventType\": \"JfrCompilation\",\n" +
                "        \"timestamp\": 1619441645384,\n" +
                "        \"duration\": 184,\n" +
                "        \"instrumentation.name\": \"JFR\",\n" +
                "        \"host.hostname\": \"DESKTOP-8AO41P3/172.21.224.1\",\n" +
                "        \"thread.name\": \"C2 CompilerThread0\",\n" +
                "        \"collector.name\": \"JFR-Uploader\",\n" +
                "        \"desc\": \"jdk.jfr.internal.MetadataReader.makeAnnotation(Ljdk/jfr/internal/MetadataDescriptor$Element;)Ljdk/jfr/AnnotationElement;\",\n" +
                "        \"succeeded\": true,\n" +
                "        \"instrumentation.provider\": \"JFR-Uploader\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"eventType\": \"JfrCompilation\",\n" +
                "        \"timestamp\": 1619441653724,\n" +
                "        \"duration\": 162,\n" +
                "        \"instrumentation.name\": \"JFR\",\n" +
                "        \"host.hostname\": \"DESKTOP-8AO41P3/172.21.224.1\",\n" +
                "        \"thread.name\": \"C2 CompilerThread0\",\n" +
                "        \"collector.name\": \"JFR-Uploader\",\n" +
                "        \"desc\": \"jdk.internal.math.FloatingDecimal$BinaryToASCIIBuffer.dtoa(IJIZ)V\",\n" +
                "        \"succeeded\": true,\n" +
                "        \"instrumentation.provider\": \"JFR-Uploader\"\n" +
                "    }]";
    }

    @Test
    void getJfrCompilationList() {
        List<JfrCompilation> eventList = jfrCompilationEventUtil.getJfrCompilationList(eventsJson);
        assertEquals(2, eventList.size());
    }

}