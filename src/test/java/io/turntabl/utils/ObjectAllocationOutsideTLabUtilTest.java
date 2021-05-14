package io.turntabl.utils;

;
import io.turntabl.model.metrics.ObjectAllocationOutsideTLab;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectAllocationOutsideTLabUtilTest {
    private static JsonUtility jsonUtil;
    private static ObjectAllocationOutsideTLabUtil objectAllocationOutsideTLabUtil;
    private static String jsonString;

    @BeforeAll
    public static void setUpAll() {
        jsonUtil = new JsonUtility();
        objectAllocationOutsideTLabUtil = new ObjectAllocationOutsideTLabUtil(jsonUtil);
        jsonString = "[\n" +
                "    {\n" +
                "        \"metrics\": [\n" +
                "            {\n" +
                "                \"name\": \"jfr.ObjectAllocationOutsideTLAB.allocation\",\n" +
                "                \"type\": \"summary\",\n" +
                "                \"value\": {\n" +
                "                   \"count\": 2, \n" +
                "                   \"sum\": 1.8232336E7,\n" +
                "                   \"min\": 7292936.0, \n" +
                "                   \"max\": 1.09394E7, \n" +
                "                      }, \n" +
                "                \"timestamp\": 1619441633833,\n" +
                "                \"interval.ms\": 439,\n" +
                "                \"attributes\": {\n" +
                "                    \"thread.name\": \"MyThread1\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"jfr.ObjectAllocationOutsideTLAB.allocation\",\n" +
                "                \"type\": \"summary\",\n" +
                "                \"value\": {\n" +
                "                   \"count\": 1, \n" +
                "                   \"sum\": 2672.0,\n" +
                "                   \"min\":2672.0, \n" +
                "                   \"max\": 2672.0, \n" +
                "                      }, \n" +
                "                \"timestamp\": 1619441625153,\n" +
                "                \"interval.ms\": 0,\n" +
                "                \"attributes\": {\n" +
                "                    \"thread.name\": \"JfrController\"\n" +
                "                }\n" +
                "            },\n" +
                "]}]";
    }

    @Test
    void getObjectAllocationOutsideTLab() {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<ObjectAllocationOutsideTLab> objectAllocationOutsideTLabList = objectAllocationOutsideTLabUtil.getObjectAllocationOutsideTLab(jsonArray.get());
        assertEquals(2, objectAllocationOutsideTLabList.size());
    }
}