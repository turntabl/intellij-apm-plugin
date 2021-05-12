package io.turntabl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.model.events.JfrCompilation;

import java.util.List;
import java.util.stream.Collectors;

public class JfrCompilationEventUtil {
    private final JsonUtility jsonUtility;
    ObjectMapper mapper = new ObjectMapper();

    public JfrCompilationEventUtil(JsonUtility jsonUtil) {
        jsonUtility = jsonUtil;
    }

    public List<JfrCompilation> getJfrCompilationList(String jsonString) {
        return jsonUtility.readEventsJson(jsonString)
                .filter(s -> s.get("eventType").toString().equalsIgnoreCase("JfrCompilation"))
                .map(e -> {
                    JfrCompilation event = null;
                    try {
                        event = mapper.readValue(e.toJSONString(), JfrCompilation.class);
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                    return event;
                }).collect(Collectors.toList());
    }
}