package io.turntabl.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.turntabl.model.ObjectAllocationOutsideTLab;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectAllocationOutsideTLabUtil {
    private final JsonUtility jsonUtils;
    private ObjectMapper mapper = new ObjectMapper();

    public ObjectAllocationOutsideTLabUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public List<ObjectAllocationOutsideTLab> getObjectAllocationOutsideTLab(JSONArray metricsArray) {
        Stream<JSONObject> objectAllocationOutsideTLabMetric = jsonUtils.getStream(metricsArray);

        return objectAllocationOutsideTLabMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.objectallocationoutsidetlab.allocation"))
                .map(c -> {
                    ObjectAllocationOutsideTLab objectAllocationOutsideTLab = null;
                    try {
                        objectAllocationOutsideTLab = mapper.readValue(c.toJSONString(), ObjectAllocationOutsideTLab.class);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return objectAllocationOutsideTLab;
                }).collect(Collectors.toList());
    }
}



