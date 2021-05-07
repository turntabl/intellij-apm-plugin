package io.turntabl.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.ui.model.CpuLoad;
import io.turntabl.ui.model.ObjectAllocationInNewTLab;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectAllocationInNewTLabUtil {
    private final JsonUtility jsonUtils;
    private ObjectMapper mapper = new ObjectMapper();

    public ObjectAllocationInNewTLabUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public List<ObjectAllocationInNewTLab> getObjectAllocationInNewTLab(JSONArray metricsArray) {
        Stream<JSONObject> objectAllocationInNewTLabMetric = jsonUtils.getStream(metricsArray);

        return objectAllocationInNewTLabMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.objectallocationinnewtlab.allocation"))
                .map(c -> {
                    ObjectAllocationInNewTLab objectAllocationInNewTLab = null;
                    try {
                        objectAllocationInNewTLab = mapper.readValue(c.toJSONString(), ObjectAllocationInNewTLab.class);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return objectAllocationInNewTLab;
                }).collect(Collectors.toList());
    }
}



