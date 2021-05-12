package io.turntabl.utils;

import io.turntabl.model.metrics.ObjectAllocationInNewTLab;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectAllocationInNewTLabUtilTest {
    private static JsonUtility jsonUtil;
    private static ObjectAllocationInNewTLabUtil objectAllocationInNewTLabUtil;
    private static String jsonString;

    @BeforeAll
    public static void setUpAll() {
        jsonUtil = new JsonUtility();
        objectAllocationInNewTLabUtil = new ObjectAllocationInNewTLabUtil(jsonUtil);
        jsonString = "[\n" +
                "    {\n" +
                "        \"metrics\": [\n" +
                "            {\n" +
                "                \"name\": \"jfr.ObjectAllocationInNewTLAB.allocation\",\n" +
                "                \"type\": \"summary\",\n" +
                "                \"value\": {\n" +
                "                   \"count\": 4, \n" +
                "                   \"sum\": 1572848.0,\n" +
                "                   \"min\": 2048.0, \n" +
                "                   \"max\": 524288.0, \n" +
                "                      }, \n" +
                "                \"timestamp\": 1619441625031,\n" +
                "                \"interval.ms\": -116,\n" +
                "                \"attributes\": {\n" +
                "                    \"thread.name\": \"pool-1-thread-1\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"jfr.ObjectAllocationInNewTLAB.allocation\",\n" +
                "                \"type\": \"summary\",\n" +
                "                \"value\": {\n" +
                "                   \"count\": 1, \n" +
                "                   \"sum\": 2064.0,\n" +
                "                   \"min\": 2064.0, \n" +
                "                   \"max\": 2064.0, \n" +
                "                      }, \n" +
                "                \"timestamp\": 1619441624858,\n" +
                "                \"interval.ms\": 0,\n" +
                "                \"attributes\": {\n" +
                "                    \"thread.name\": \"pool-1-thread-1\"\n" +
                "                }\n" +
                "            },\n" +
                "]}]";
    }

    @Test
    void getObjectAllocationInNewTLab() {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<ObjectAllocationInNewTLab> objectAllocationInNewTLabList = objectAllocationInNewTLabUtil.getObjectAllocationInNewTLab(jsonArray.get());
        assertEquals(2, objectAllocationInNewTLabList.size());
    }
}