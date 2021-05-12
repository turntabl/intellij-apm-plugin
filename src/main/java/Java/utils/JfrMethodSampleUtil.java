package Java.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.model.events.JfrMethodSample;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JfrMethodSampleUtil {
    private final JsonUtility jsonUtils;
    private ObjectMapper mapper = new ObjectMapper();

    public JfrMethodSampleUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public List<JfrMethodSample> getJfrMethodSample(String jsonString) {
        return jsonUtils.readEventsJson(jsonString)
                .filter(e -> e.get("eventType").toString().toLowerCase().startsWith("jfrmethodsample"))
                .map(j -> {
                    JfrMethodSample jfrMethodSample = null;

                    try {
                        jfrMethodSample = mapper.readValue(j.toJSONString(), JfrMethodSample.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    return jfrMethodSample;
                }).collect(Collectors.toList());
    }
}