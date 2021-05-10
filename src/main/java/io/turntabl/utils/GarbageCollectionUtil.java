package io.turntabl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.model.metrics.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GarbageCollectionUtil {
    private final JsonUtility jsonUtils;
    private ObjectMapper mapper = new ObjectMapper();

    public GarbageCollectionUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }


    public List<GCMinorDuration> getGCMinorDuration(JSONArray metricsArray){
        Stream<JSONObject> gcMinorDurationMetric = jsonUtils.getStream(metricsArray);

        return gcMinorDurationMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.garbagecollection.minorduration"))
                .map(c -> {
                    GCMinorDuration gcMinorDuration = null;

                    try {
                        gcMinorDuration = mapper.readValue(c.toJSONString(), GCMinorDuration.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return gcMinorDuration;
                }).collect(Collectors.toList());
    }

    public List<GCMajorDuration> getGCMajorDuration(JSONArray metricsArray){
        Stream<JSONObject> gcMajorDurationMetric = jsonUtils.getStream(metricsArray);

        return gcMajorDurationMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.garbagecollection.majorduration"))
                .map(c -> {
                    GCMajorDuration gcMajorDuration = null;

                    try {
                        gcMajorDuration = mapper.readValue(c.toJSONString(), GCMajorDuration.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return gcMajorDuration;
                }).collect(Collectors.toList());
    }

    public List<G1GarbageCollectionDuration> getG1GarbageCollectionDuration(JSONArray metricsArray){
        Stream<JSONObject> g1GCDurationMetric = jsonUtils.getStream(metricsArray);

        return g1GCDurationMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.g1garbagecollection.duration"))
                .map(c -> {
                    G1GarbageCollectionDuration g1GCDuration = null;

                    try {
                        g1GCDuration = mapper.readValue(c.toJSONString(), G1GarbageCollectionDuration.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return g1GCDuration;
                }).collect(Collectors.toList());
    }

    public List<GCDuration> getGCDuration(JSONArray metricsArray){
        Stream<JSONObject> gcDurationMetric = jsonUtils.getStream(metricsArray);

        return gcDurationMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.garbagecollection.duration"))
                .map(c -> {
                    GCDuration gcDuration = null;

                    try {
                        gcDuration = mapper.readValue(c.toJSONString(), GCDuration.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return gcDuration;
                }).collect(Collectors.toList());
    }

    public List<GCLongestPause> getGCLongestPause(JSONArray metricsArray){
        Stream<JSONObject> gcLongestPauseMetric = jsonUtils.getStream(metricsArray);

        return gcLongestPauseMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.garbagecollection.longestpause"))
                .map(c -> {
                    GCLongestPause gcLongestPause = null;

                    try {
                        gcLongestPause = mapper.readValue(c.toJSONString(), GCLongestPause.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return gcLongestPause;
                }).collect(Collectors.toList());
    }

}
