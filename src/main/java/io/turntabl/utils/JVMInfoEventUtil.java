package io.turntabl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.model.JVMInfoEvent;

import java.util.List;
import java.util.stream.Collectors;

public class JVMInfoEventUtil {
    private final JsonUtility jsonUtility;
    ObjectMapper mapper = new ObjectMapper();

    public JVMInfoEventUtil(JsonUtility jsonUtil) {
        jsonUtility = jsonUtil;
    }

    public List<JVMInfoEvent> getJVMInfoList(String jsonString) {
        return jsonUtility.readEventsJson(jsonString)
                .filter(s -> s.get("eventType").toString().equalsIgnoreCase("JfrJVMInformation"))
                .map(e -> {
                    JVMInfoEvent event = null;
                    try {
                        event = mapper.readValue(e.toJSONString(), JVMInfoEvent.class);
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                    return event;
                }).collect(Collectors.toList());
    }
}
