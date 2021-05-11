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
        "        \"name\": \"jfr.MetaspaceSummary.dataSpace.used\",\n" +
        "            \"type\": \"gauge\",\n" +
        "            \"value\": 1.8565112E7,\n" +
        "            \"timestamp\": 1619441634271,\n" +
        "            \"attributes\": {\n" +
        "        \"when\": \"Before GC\"\n" +
        "    }\n" +
        "    },\n" +
        "    {\n" +
        "        \"name\": \"jfr.MetaspaceSummary.dataSpace.reserved\",\n" +
        "            \"type\": \"gauge\",\n" +
        "            \"value\": 1.8874368E7,\n" +
        "            \"timestamp\": 1619441634271,\n" +
        "            \"attributes\": {\n" +
        "        \"when\": \"Before GC\"\n" +
        "    }\n" +
        "    },\n" +
        "    {\n" +
        "        \"name\": \"jfr.MetaspaceSummary.classSpace.committed\",\n" +
        "            \"type\": \"gauge\",\n" +
        "            \"value\": 2097152.0,\n" +
        "            \"timestamp\": 1619441634271,\n" +
        "            \"attributes\": {\n" +
        "        \"when\": \"Before GC\"\n" +
        "    }\n" +
        "    },\n" +
        "        ]";

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