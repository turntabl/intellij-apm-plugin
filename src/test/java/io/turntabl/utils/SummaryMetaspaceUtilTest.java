package io.turntabl.utils;

import io.turntabl.model.metrics.SummaryMetaspace;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SummaryMetaspaceUtilTest {

    private static  SummaryMetaspaceUtil summaryMetaspaceUtil;
    private static JsonUtility jsonUtility;
    private static String exampleJsonMetric;
    private static Optional<JSONArray> jsonArray;

    @BeforeAll
    public static void setupAll() {
        System.out.println("Pre Testing Setup running.....");
        jsonUtility =   new JsonUtility();
        summaryMetaspaceUtil = new SummaryMetaspaceUtil(jsonUtility);
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
                "                \"name\": \"jfr.ThreadAllocationStatistics.allocated\",\n" +
                "                \"type\": \"gauge\",\n" +
                "                \"value\": 1.95738E7,\n" +
                "                \"timestamp\": 1619441624895,\n" +
                "                \"attributes\": {\n" +
                "                    \"thread.osName\": \"main\",\n" +
                "                    \"thread.name\": \"main\"\n" +
                "                }\n" +
                "            },\n{\n" +
                "                \"name\": \"jfr.MetaspaceSummary.metaspace.committed\",\n" +
                "                \"type\": \"gauge\",\n" +
                "                \"value\": 2.097152E7,\n" +
                "                \"timestamp\": 1619441634271,\n" +
                "                \"attributes\": {\n" +
                "                    \"when\": \"Before GC\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"jfr.MetaspaceSummary.metaspace.used\",\n" +
                "                \"type\": \"gauge\",\n" +
                "                \"value\": 2.0466688E7,\n" +
                "                \"timestamp\": 1619441634271,\n" +
                "                \"attributes\": {\n" +
                "                    \"when\": \"Before GC\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"jfr.MetaspaceSummary.metaspace.reserved\",\n" +
                "                \"type\": \"gauge\",\n" +
                "                \"value\": 1.092616192E9,\n" +
                "                \"timestamp\": 1619441634271,\n" +
                "                \"attributes\": {\n" +
                "                    \"when\": \"Before GC\"\n" +
                "                }\n" +
                "            }]\n" +
                "    }\n" +
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
    void getSummaryMetaspaceMetric() {
        List<SummaryMetaspace> summaryMetaspaceList = summaryMetaspaceUtil.getSummaryMetaspaceMetric(jsonArray.get());
        assertEquals(3, summaryMetaspaceList.size());
    }

    @Test
    void groupSummaryMetaspaceByTimestamp() {
    }

    @Test
    void getSummaryMetaspaceConsolidated() {
        List<SummaryMetaspace> summaryMetaspaceList = summaryMetaspaceUtil.getSummaryMetaspaceMetric(jsonArray.get());
        List<SummaryMetaspace> consolidatedList = summaryMetaspaceUtil.getSummaryMetaspaceConsolidated(summaryMetaspaceList);
        assertEquals(3,consolidatedList.size());
    }
}


