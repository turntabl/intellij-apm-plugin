package Java.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.model.events.JavaMonitorWait;
import org.json.simple.JSONArray;

import java.util.List;
import java.util.stream.Collectors;

public class JavaMonitorWaitUtil {
    private final JsonUtility jsonUtility;
    ObjectMapper mapper = new ObjectMapper();

    public JavaMonitorWaitUtil(JsonUtility jsonUtil) {
        jsonUtility = jsonUtil;
    }

    public List<JavaMonitorWait> getJavaMonitorWaitList(JSONArray jsonString) {
        return jsonUtility.readEventsJson(jsonString)
                .filter(s -> s.get("eventType").toString().equalsIgnoreCase("JfrJavaMonitorWait"))
                .map(e -> {
                    JavaMonitorWait event = null;
                    try {
                        event = mapper.readValue(e.toJSONString(), JavaMonitorWait.class);
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                    return event;
                }).collect(Collectors.toList());
    }
}