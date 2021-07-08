package io.turntabl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.model.metrics.GcHeapSummary;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GcHeapSummaryUtil {
    private final JsonUtility jsonUtils;
    private ObjectMapper mapper = new ObjectMapper();

    public GcHeapSummaryUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public List<GcHeapSummary> getGcHeapSummary(JSONArray metricsArray) {
        Stream<JSONObject> gcHeapSummaryMetric = jsonUtils.getStream(metricsArray);


        return gcHeapSummaryMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.gcheapsummary"))
                .map(c -> {
                    GcHeapSummary gcHeapSummary = null;

                    try {
                        gcHeapSummary = mapper.readValue(c.toJSONString(), GcHeapSummary.class);

                        String name = gcHeapSummary.getName().toLowerCase();
                        double value = Double.parseDouble(c.get("value").toString());

                        if (name.endsWith("heapcommittedsize")) {
                            gcHeapSummary.setHeapCommittedSize(value);
                        } else if (name.endsWith("reservedsize")) {
                            gcHeapSummary.setReservedSize(value);
                        } else {
                            gcHeapSummary.setHeapUsed(value);
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return gcHeapSummary;
                }).collect(Collectors.toList());
    }

    public Map<Long, List<GcHeapSummary>> groupGcHeapSummaryByTimestamp(List<GcHeapSummary> gcHeapSummaryList) {
        return gcHeapSummaryList
                .stream()
                .collect(Collectors.groupingBy(GcHeapSummary::getTimestamp));
    }

    public List<GcHeapSummary> getGcHeapSummaryConsolidated(List<GcHeapSummary> list) {
        Map<Long, List<GcHeapSummary>> gcHeapSummaryMap = groupGcHeapSummaryByTimestamp(list);

        return gcHeapSummaryMap.entrySet().stream()
                .map(s -> {
                    GcHeapSummary gc1 = s.getValue().get(0);
                    GcHeapSummary gc2 = s.getValue().get(1);
                    GcHeapSummary gc3 = s.getValue().get(2);

                    if (gc2.getName().toLowerCase().endsWith("heapcommittedsize")) {
                        gc1.setHeapCommittedSize(gc2.getHeapCommittedSize());
                    } else if (gc2.getName().toLowerCase().endsWith("reservedsize")) {
                        gc1.setReservedSize(gc2.getReservedSize());
                    } else {
                        gc1.setHeapUsed(gc2.getHeapUsed());
                    }

                    if (gc3.getName().toLowerCase().endsWith("heapcommittedsize")) {
                        gc1.setHeapCommittedSize(gc3.getHeapCommittedSize());
                    } else if (gc3.getName().toLowerCase().endsWith("reservedsize")) {
                        gc1.setReservedSize(gc3.getReservedSize());
                    } else {
                        gc1.setHeapUsed(gc3.getHeapUsed());
                    }

                    return gc1;
                }).collect(Collectors.toList());
    }
}
