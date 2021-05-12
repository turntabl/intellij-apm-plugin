package io.turntabl.utils;

import io.turntabl.model.metrics.*;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GarbageCollectionUtilTest {
    JsonUtility jsonUtil = new JsonUtility();
    GarbageCollectionUtil gbUtil = new GarbageCollectionUtil(jsonUtil);

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
            "                \"name\": \"jfr.GarbageCollection.minorDuration\",\n" +
            "                \"type\": \"summary\",\n" +
            "                \"value\": {\n" +
            "                    \"count\": 0,\n" +
            "                    \"sum\": 0.0,\n" +
            "                    \"min\": 9.223372036854E12,\n" +
            "                    \"max\": -9.223372036854E12\n" +
            "                },\n" +
            "                \"timestamp\": 1619441613596,\n" +
            "                \"interval.ms\": -1619441613596,\n" +
            "                \"attributes\": {}\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"jfr.GarbageCollection.majorDuration\",\n" +
            "                \"type\": \"summary\",\n" +
            "                \"value\": {\n" +
            "                    \"count\": 0,\n" +
            "                    \"sum\": 0.0,\n" +
            "                    \"min\": 9.223372036854E12,\n" +
            "                    \"max\": -9.223372036854E12\n" +
            "                },\n" +
            "                \"timestamp\": 1619441613596,\n" +
            "                \"interval.ms\": -1619441613596,\n" +
            "                \"attributes\": {}\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"jfr.G1GarbageCollection.duration\",\n" +
            "                \"type\": \"summary\",\n" +
            "                \"value\": {\n" +
            "                    \"count\": 0,\n" +
            "                    \"sum\": 0.0,\n" +
            "                    \"min\": 9.223372036854E12,\n" +
            "                    \"max\": -9.223372036854E12\n" +
            "                },\n" +
            "                \"timestamp\": 1619441613598,\n" +
            "                \"interval.ms\": -1619441613598,\n" +
            "                \"attributes\": {}\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"jfr.GarbageCollection.duration\",\n" +
            "                \"type\": \"summary\",\n" +
            "                \"value\": {\n" +
            "                    \"count\": 0,\n" +
            "                    \"sum\": 0.0,\n" +
            "                    \"min\": 9.223372036854E12,\n" +
            "                    \"max\": -9.223372036854E12\n" +
            "                },\n" +
            "                \"timestamp\": 1619441613599,\n" +
            "                \"interval.ms\": -1619441613599,\n" +
            "                \"attributes\": {}\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"jfr.GarbageCollection.longestPause\",\n" +
            "                \"type\": \"gauge\",\n" +
            "                \"value\": 18686.0,\n" +
            "                \"timestamp\": 1619441634271,\n" +
            "                \"attributes\": {\n" +
            "                    \"name\": \"G1Old\",\n" +
            "                    \"cause\": \"G1 Humongous Allocation\"\n" +
            "                }\n" +
            "            }" +
            "]\n" +
            "    }\n" +
            "]";

    @Test
    void canGetGCMinorDuration(){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<GCMinorDuration> extractedList = gbUtil.getGCMinorDuration(jsonArray.get());
        assertEquals(1, extractedList.size());
    }

    @Test
    void canGetGCMajorDuration(){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<GCMajorDuration> extractedList = gbUtil.getGCMajorDuration(jsonArray.get());
        assertEquals(1, extractedList.size());
    }

    @Test
    void canGetG1GCDuration(){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<G1GarbageCollectionDuration> extractedList = gbUtil.getG1GarbageCollectionDuration(jsonArray.get());
        assertEquals(1, extractedList.size());
    }

    @Test
    void canGetGCDuration(){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<GCDuration> extractedList = gbUtil.getGCDuration(jsonArray.get());
        assertEquals(1, extractedList.size());
    }

    @Test
    void canGetGCLongestPause(){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<GCLongestPause> extractedList = gbUtil.getGCLongestPause(jsonArray.get());
        assertEquals(1, extractedList.size());
    }
}