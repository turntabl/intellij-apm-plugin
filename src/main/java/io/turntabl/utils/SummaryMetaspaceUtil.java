package io.turntabl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.model.metrics.SummaryMetaspace;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SummaryMetaspaceUtil {

    private final JsonUtility jsonUtils;
    private ObjectMapper mapper = new ObjectMapper();

    public SummaryMetaspaceUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }


    public List<SummaryMetaspace> getSummaryMetaspaceMetric(JSONArray metricsArray) {
        Stream<JSONObject> summaryMetaspaceMetric = jsonUtils.getStream(metricsArray);
        return summaryMetaspaceMetric
                .filter(m -> m.get("name").toString().startsWith("jfr.MetaspaceSummary"))
                .map(c -> {
                    SummaryMetaspace summaryMetaspace = null;
                    try {
                        summaryMetaspace = mapper.readValue(c.toJSONString(), SummaryMetaspace.class);

                        String name = summaryMetaspace.getName().toLowerCase();
                        double value = Double.parseDouble(c.get("value").toString());

                        if (name.endsWith("metaspace.committed")) {
                            summaryMetaspace.setCommittedValue(value);
                        } else if (name.endsWith("metaspace.used")) {
                            summaryMetaspace.setUsedValue(value);
                        } else {
                            summaryMetaspace.setReservedValue(value);
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return summaryMetaspace;
                }).collect(Collectors.toList());

    }

    public Map<Long, List<SummaryMetaspace>> groupSummaryMetaspaceByTimestamp(List<SummaryMetaspace> summaryMetaspaceList) {
        return summaryMetaspaceList
                .stream()
                .collect(Collectors.groupingBy(SummaryMetaspace::getTimestamp));
    }

    public List<SummaryMetaspace> getSummaryMetaspaceConsolidated(List<SummaryMetaspace> list) {
        Map<Long, List<SummaryMetaspace>> summaryMetaspaceMap = groupSummaryMetaspaceByTimestamp(list);

        return summaryMetaspaceMap.entrySet().stream()
                .map(s -> {
                    SummaryMetaspace cpu = s.getValue().get(0);
                    SummaryMetaspace cpu2 = s.getValue().get(1);
                    SummaryMetaspace cpu3 = s.getValue().get(2);

                    if (cpu2.getName().toLowerCase().endsWith("committed")) {
                        cpu.setCommittedValue(cpu2.getCommittedValue());
                    } else if (cpu2.getName().toLowerCase().endsWith("used")) {
                        cpu.setUsedValue(cpu2.getUsedValue());
                    } else {
                        cpu.setReservedValue(cpu2.getReservedValue());
                    }

                    if (cpu3.getName().toLowerCase().endsWith("committed")) {
                        cpu.setCommittedValue(cpu3.getCommittedValue());
                    } else if (cpu3.getName().toLowerCase().endsWith("used")) {
                        cpu.setUsedValue(cpu3.getUsedValue());
                    } else {
                        cpu.setReservedValue(cpu3.getReservedValue());
                    }

                    return cpu;
                }).collect(Collectors.toList());
    }

}
