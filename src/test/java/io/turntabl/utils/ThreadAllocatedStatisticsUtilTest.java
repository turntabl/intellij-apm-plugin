package io.turntabl.utils;

import io.turntabl.model.metrics.ThreadAllocationStatistics;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadAllocatedStatisticsUtilTest {
     JsonUtility jsonUtil = new JsonUtility();
     ThreadAllocatedStatisticsUtil threadAllocatedStatisticsUtil = new ThreadAllocatedStatisticsUtil(jsonUtil);


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
            "                \"name\": \"jfr.ThreadAllocationStatistics.allocated\",\n" +
            "                \"type\": \"gauge\",\n" +
            "                \"value\": 1.95738E7,\n" +
            "                \"timestamp\": 1619441624895,\n" +
            "                \"attributes\": {\n" +
            "                    \"thread.osName\": \"main\",\n" +
            "                    \"thread.name\": \"main\"\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"jfr.SocketRead.bytesRead\",\n" +
            "                \"type\": \"summary\",\n" +
            "                \"value\": {\n" +
            "                    \"count\": 2,\n" +
            "                    \"sum\": 320.0,\n" +
            "                    \"min\": 160.0,\n" +
            "                    \"max\": 160.0\n" +
            "                },\n" +
            "                \"timestamp\": 1619441645442,\n" +
            "                \"interval.ms\": 46,\n" +
            "                \"attributes\": {\n" +
            "                    \"thread.name\": \"Thread-1\"\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"jfr.SocketRead.duration\",\n" +
            "                \"type\": \"summary\",\n" +
            "                \"value\": {\n" +
            "                    \"count\": 2,\n" +
            "                    \"sum\": 37.0,\n" +
            "                    \"min\": 14.0,\n" +
            "                    \"max\": 23.0\n" +
            "                },\n" +
            "                \"timestamp\": 1619441645442,\n" +
            "                \"interval.ms\": 46,\n" +
            "                \"attributes\": {\n" +
            "                    \"thread.name\": \"Thread-1\"\n" +
            "                }\n" +
            "            }" +
            "]\n" +
            "    }\n" +
            "]";

    @Test
     void getThreadAllocatedStatistics() {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<ThreadAllocationStatistics> threadAllocationStatisticsList = threadAllocatedStatisticsUtil.getThreadAllocatedStatistics(jsonArray.get());

        System.out.println(threadAllocationStatisticsList);
        assertEquals(1,threadAllocationStatisticsList.size());
    }
}