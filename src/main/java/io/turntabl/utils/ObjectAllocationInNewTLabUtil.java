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

                        String name = objectAllocationInNewTLab.getName().toLowerCase();
                        double value = Double.parseDouble(c.get("value").toString());
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return objectAllocationInNewTLab;
                }).collect(Collectors.toList());

        public Map<Long, List<ObjectAllocationInNewTLab>> groupObjectAllocationInNewTLabByTimestamp(List<ObjectAllocationInNewTLab> objectAllocationInNewTLabList) {
            return objectAllocationInNewTLabList
                    .stream()
                    .collect(Collectors.groupingBy(ObjectAllocationInNewTLab::getTimestamp));
        




//        public List<ObjectAllocationInNewTLab> getObjectAllocatedConsolidated(List<ObjectAllocationInNewTLab> list) {
//            Map<Long, List<ObjectAllocationInNewTLab>> objectAllocationMap = groupObjectAllocationInNewTLabByTimestamp(list);
//
//            return  objectAllocationMap.entrySet().stream()
//                    .map(s -> {
//                        ObjectAllocationInNewTLab objectAllo = s.getValue().get(0);
//
//                        return objectAllo;
//                    }).collect(Collectors.toList());
//        }