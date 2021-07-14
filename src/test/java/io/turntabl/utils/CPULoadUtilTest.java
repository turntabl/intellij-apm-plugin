package io.turntabl.utils;

import io.turntabl.model.metrics.CpuLoad;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CPULoadUtilTest {
    private static CPULoadUtil cpuLoadUtil;
    private static JsonUtility jsonUtility;
    private static String metricsJson;

    @BeforeAll
    public static void setupAll() {
        System.out.println("Setup started.............");
        jsonUtility = new JsonUtility();
        cpuLoadUtil = new CPULoadUtil(jsonUtility);

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
                "{\n " +
                "                \"name\": \"jfr.CPULoad.jvmUser\",\n" +
                "                \"type\": \"gauge\",\n" +
                "                \"value\": 0.10448416322469711,\n" +
                "                \"timestamp\": 1619441634964,\n" +
                "                \"attributes\": {}\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"jfr.CPULoad.jvmSystem\",\n" +
                "                \"type\": \"gauge\",\n" +
                "                \"value\": 0.017414027824997902,\n" +
                "                \"timestamp\": 1619441634964,\n" +
                "                \"attributes\": {}\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"jfr.CPULoad.machineTotal\",\n" +
                "                \"type\": \"gauge\",\n" +
                "                \"value\": 0.17614521086215973,\n" +
                "                \"timestamp\": 1619441634964,\n" +
                "                \"attributes\": {}\n" +
                "            }]}]";
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCPULoad() {
        JSONArray arr = jsonUtility.readMetricsJson(metricsJson).get();
        assertTrue(cpuLoadUtil.getCPULoad(arr).size() > 0);
    }

    @Test
    void groupCpuLoadByTimestamp() {
        JSONArray arr = jsonUtility.readMetricsJson(metricsJson).get();
        List<CpuLoad> cpuLoadList = cpuLoadUtil.getCPULoad(arr);
        assertEquals(1, cpuLoadUtil.groupCpuLoadByTimestamp(cpuLoadList).size());
    }

    @Test
    void getCPULoadConsolidated() {
        JSONArray arr = jsonUtility.readMetricsJson(metricsJson).get();
        List<CpuLoad> cpuLoadList = cpuLoadUtil.getCPULoadConsolidated(cpuLoadUtil.getCPULoad(arr));
        System.out.println(cpuLoadList);
        assertEquals(1, cpuLoadList.size());
    }
}