package io.turntabl.utils;

import io.turntabl.model.metrics.JfrSocketReadBytesRead;
import io.turntabl.model.metrics.JfrSocketReadDuration;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JfrSocketReadUtilTest {
    JsonUtility jsonUtil = new JsonUtility();
    JfrSocketReadUtil jfrSocketReadUtil = new JfrSocketReadUtil(jsonUtil);

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
    void canGetJfrSocketReadBytesRead(){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<JfrSocketReadBytesRead> extractedList = jfrSocketReadUtil.getJfrSocketReadBytesRead(jsonArray.get());
        assertEquals(1, extractedList.size());
    }

    @Test
    void canGetJfrSocketReadDuration(){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<JfrSocketReadDuration> extractedList = jfrSocketReadUtil.getJfrSocketReadDuration(jsonArray.get());
        assertEquals(1, extractedList.size());
    }
}