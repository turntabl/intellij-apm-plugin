package io.turntabl.utils;

import com.google.protobuf.Any;
import io.turntabl.model.metrics.SummaryMetaspace;
import io.turntabl.model.metrics.ThreadCpuLoad;
import org.json.simple.JSONArray;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummaryMetaspaceUtilTest {

    JsonUtility jsonUtility = new JsonUtility();
    SummaryMetaspaceUtil summaryMetaspaceUtil = new SummaryMetaspaceUtil(jsonUtility);
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
        "        \"metrics\": [    {\n" +
        "        \"name\": \"jfr.MetaspaceSummary.metaspace.committed\",\n" +
        "            \"type\": \"gauge\",\n" +
        "            \"value\": 1.9267584E7,\n" +
        "            \"timestamp\": 1619441625066,\n" +
        "            \"attributes\": {\n" +
        "        \"when\": \"Before GC\"\n" +
        "    }\n" +
        "    },\n" +
        "    {\n" +
        "        \"name\": \"jfr.MetaspaceSummary.metaspace.used\",\n" +
        "            \"type\": \"gauge\",\n" +
        "            \"value\": 1.8665456E7,\n" +
        "            \"timestamp\": 1619441625066,\n" +
        "            \"attributes\": {\n" +
        "        \"when\": \"Before GC\"\n" +
        "    }\n" +
        "    },\n" +
        "    {\n" +
        "        \"name\": \"jfr.MetaspaceSummary.metaspace.reserved\",\n" +
        "            \"type\": \"gauge\",\n" +
        "            \"value\": 1.092616192E9,\n" +
        "            \"timestamp\": 1619441625066,\n" +
        "            \"attributes\": {\n" +
        "        \"when\": \"Before GC\"\n" +
        "    }\n" +
        "    }]\n" +
        "    }\n" +
        "]";;

    Optional<JSONArray> jsonArray = jsonUtility.readMetricsJson(jsonString);
    @Test
    void getSummaryMetaspaceMetric(){
        List<SummaryMetaspace> summaryMetaspaceList = summaryMetaspaceUtil.getSummaryMetaspaceMetric(jsonArray.get());
        assertEquals(3, summaryMetaspaceList.size());
    }

    @Test
    void  groupSummaryMetaspaceByTimestamp(){

    }

    @Test
    void  getSummaryMetaspaceConsolidated(){
        List<SummaryMetaspace> summaryMetaspaceList = summaryMetaspaceUtil.getSummaryMetaspaceMetric(jsonArray.get());
        List<SummaryMetaspace> consolidatedList = summaryMetaspaceUtil.getSummaryMetaspaceConsolidated(summaryMetaspaceList);
        assertEquals(3,consolidatedList.size());
    }

}