package io.turntabl.utils;

import io.turntabl.model.metrics.GcHeapSummary;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GcHeapSummaryUtilTest {
    JsonUtility jsonUtil = new JsonUtility();
    GcHeapSummaryUtil gcHeapSummaryUtil = new GcHeapSummaryUtil(jsonUtil);

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
            "                \"name\": \"jfr.GCHeapSummary.heapCommittedSize\",\n" +
            "                \"type\": \"gauge\",\n" +
            "                \"value\": 2.01326592E8,\n" +
            "                \"timestamp\": 1619441625066,\n" +
            "                \"attributes\": {\n" +
            "                    \"heapStart\": 31155290112,\n" +
            "                    \"reservedEnd\": 34359738368,\n" +
            "                    \"committedEnd\": 31356616704,\n" +
            "                    \"when\": \"Before GC\"\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"jfr.GCHeapSummary.reservedSize\",\n" +
            "                \"type\": \"gauge\",\n" +
            "                \"value\": 3.204448256E9,\n" +
            "                \"timestamp\": 1619441625066,\n" +
            "                \"attributes\": {\n" +
            "                    \"heapStart\": 31155290112,\n" +
            "                    \"reservedEnd\": 34359738368,\n" +
            "                    \"committedEnd\": 31356616704,\n" +
            "                    \"when\": \"Before GC\"\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"jfr.GCHeapSummary.heapUsed\",\n" +
            "                \"type\": \"gauge\",\n" +
            "                \"value\": 1.19968768E8,\n" +
            "                \"timestamp\": 1619441625066,\n" +
            "                \"attributes\": {\n" +
            "                    \"heapStart\": 31155290112,\n" +
            "                    \"reservedEnd\": 34359738368,\n" +
            "                    \"committedEnd\": 31356616704,\n" +
            "                    \"when\": \"Before GC\"\n" +
            "                }\n" +
            "            }" +
            "]\n" +
            "    }\n" +
            "]";

    @Test
    void canGetGcHeapSummaryList(){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<GcHeapSummary> extractedList = gcHeapSummaryUtil.getGcHeapSummary(jsonArray.get());
        assertEquals(3, extractedList.size());
    }

    @Test
    void canGetGcHeapSummaryListConsolidated(){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<GcHeapSummary> extractedList = gcHeapSummaryUtil.getGcHeapSummary(jsonArray.get());
        List<GcHeapSummary> consolidatedList = gcHeapSummaryUtil.getGcHeapSummaryConsolidated(extractedList);
        assertEquals(1, consolidatedList.size());
    }

}