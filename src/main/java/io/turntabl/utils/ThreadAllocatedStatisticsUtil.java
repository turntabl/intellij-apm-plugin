package io.turntabl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.ui.model.ThreadAllocationStatistics;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadAllocatedStatisticsUtil {
    private final JsonUtility jsonUtils;
    private ObjectMapper mapper = new ObjectMapper();

    public ThreadAllocatedStatisticsUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public List<ThreadAllocationStatistics> getThreadAllocatedStatistics(JSONArray metricsArray) {
        Stream<JSONObject> threadAllocatedStatisticsMetric = jsonUtils.getStream(metricsArray);
        return threadAllocatedStatisticsMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.threadallocatedstatistics"))
                .map(c -> {
                    ThreadAllocationStatistics threadAllocationStatistics = null;
                    try {
                        threadAllocationStatistics = mapper.readValue(c.toJSONString(), ThreadAllocationStatistics.class);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return threadAllocationStatistics;
                }).collect(Collectors.toList());
    }


}
