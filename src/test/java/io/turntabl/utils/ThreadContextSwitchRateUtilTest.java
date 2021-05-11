package io.turntabl.utils;

import io.turntabl.model.metrics.ThreadAllocationStatistics;
import io.turntabl.model.metrics.ThreadContextSwitchRate;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadContextSwitchRateUtilTest {
    JsonUtility jsonUtil = new JsonUtility();
    ThreadContextSwitchRateUtil threadContextSwitchRateUtil = new ThreadContextSwitchRateUtil(jsonUtil);


    String jsonString = "[\n" +
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
            "                \"name\": \"jfr.ThreadContextSwitchRate\",\n" +
            "                \"type\": \"gauge\",\n" +
            "                \"value\": 21703.333984375,\n" +
            "                \"timestamp\": 1619441634798,\n" +
            "                \"attributes\": {}\n" +
            "            }" +
            "]\n" +
                "}\n" +
            "]";

    @Test
    void getThreadContextSwitchRate() {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<ThreadContextSwitchRate> threadContextSwitchRateList = threadContextSwitchRateUtil.getThreadContextSwitchRate(jsonArray.get());
        assertEquals(1,threadContextSwitchRateList.size());
    }

}