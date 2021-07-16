package io.turntabl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.model.metrics.JfrSocketReadBytesRead;
import io.turntabl.model.metrics.JfrSocketReadDuration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JfrSocketReadUtil {
    private final JsonUtility jsonUtils;
    private ObjectMapper mapper = new ObjectMapper();

    public JfrSocketReadUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public List<JfrSocketReadBytesRead> getJfrSocketReadBytesRead(JSONArray metricsArray) {
        Stream<JSONObject> bytesReadMetric = jsonUtils.getStream(metricsArray);

        return bytesReadMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.socketread.bytesread"))
                .map(c -> {
                    JfrSocketReadBytesRead jfrSocketReadBytesRead = null;

                    try {
                        jfrSocketReadBytesRead = mapper.readValue(c.toJSONString(), JfrSocketReadBytesRead.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return jfrSocketReadBytesRead;
                }).collect(Collectors.toList());
    }

    public List<JfrSocketReadDuration> getJfrSocketReadDuration(JSONArray metricsArray) {
        Stream<JSONObject> durationMetric = jsonUtils.getStream(metricsArray);

        return durationMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.socketread.duration"))
                .map(c -> {
                    JfrSocketReadDuration jfrSocketReadDuration = null;

                    try {
                        jfrSocketReadDuration = mapper.readValue(c.toJSONString(), JfrSocketReadDuration.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return jfrSocketReadDuration;
                }).collect(Collectors.toList());
    }


}
