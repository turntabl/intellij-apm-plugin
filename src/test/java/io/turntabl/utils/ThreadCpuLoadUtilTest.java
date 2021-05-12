package io.turntabl.utils;

import Java.utils.JsonUtility;
import Java.utils.ThreadCpuLoadUtil;
import io.turntabl.model.metrics.ThreadCpuLoad;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ThreadCpuLoadUtilTest {
    JsonUtility jsonUtil = new JsonUtility();
    ThreadCpuLoadUtil threadCpuLoadUtil = new ThreadCpuLoadUtil(jsonUtil);
    String jsonString =  "[\n" +
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
            "                \"name\": \"jfr.ThreadCPULoad.user\",\n" +
            "                \"type\": \"gauge\",\n" +
            "                \"value\": 0.004777687136083841,\n" +
            "                \"timestamp\": 1619441634794,\n" +
            "                \"attributes\": {\n" +
            "                    \"thread.name\": \"main\"\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"jfr.ThreadCPULoad.system\",\n" +
            "                \"type\": \"gauge\",\n" +
            "                \"value\": 0.001365053467452526,\n" +
            "                \"timestamp\": 1619441634794,\n" +
            "                \"attributes\": {\n" +
            "                    \"thread.name\": \"main\"\n" +
            "                }\n" +
            "            }"+
            "]\n" +
            "    }\n" +
            "]";

    @Test
    void getThreadCpuLoad() {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<ThreadCpuLoad> threadCpuLoadList = threadCpuLoadUtil.getThreadCpuLoad(jsonArray.get());
        System.out.println(threadCpuLoadList);

        assertEquals(2,threadCpuLoadList.size());
    }

    @Test
    void getThreadCpuLoadConsolidated() {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<ThreadCpuLoad> threadCpuLoadList = threadCpuLoadUtil.getThreadCpuLoad(jsonArray.get());
        List<ThreadCpuLoad> consolidatedList = threadCpuLoadUtil.getThreadCpuLoadConsolidated(threadCpuLoadList);

        System.out.println(consolidatedList);
        assertEquals(1,consolidatedList.size());
    }
}