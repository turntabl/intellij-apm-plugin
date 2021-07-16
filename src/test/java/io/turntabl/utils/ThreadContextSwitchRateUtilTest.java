package io.turntabl.utils;

import io.turntabl.model.metrics.ThreadContextSwitchRate;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ThreadContextSwitchRateUtilTest {

    private static ThreadContextSwitchRateUtil threadContextSwitchRateUtil;
    private static JsonUtility jsonUtility;
    private static String exampleJsonMetric;
    private static Optional<JSONArray> jsonArray;

    @BeforeAll
    public static void setupAll() {
        System.out.println("Pre Testing Setup running.....");
        jsonUtility = new JsonUtility();
        threadContextSwitchRateUtil = new ThreadContextSwitchRateUtil(jsonUtility);
        exampleJsonMetric = "[\n" +
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

        jsonArray = jsonUtility.readMetricsJson(exampleJsonMetric);
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getThreadContextSwitchRate() {
        List<ThreadContextSwitchRate> threadContextSwitchRateList = threadContextSwitchRateUtil.getThreadContextSwitchRate(jsonArray.get());
        assertEquals(1, threadContextSwitchRateList.size());
    }

}