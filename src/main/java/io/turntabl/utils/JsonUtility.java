package io.turntabl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.ui.model.CpuLoad;
import io.turntabl.ui.model.SummaryMetaspace;
import io.turntabl.ui.model.ThreadAllocationStatistics;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonUtility {
    private final JSONParser jsonParser = new JSONParser();  
    private ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings("unchecked")
    public Optional<JSONArray> readMetricsJson(String jsonString) {
        JSONArray metricJSONArray = null;
        try {
            JSONArray parsedObject = (JSONArray) jsonParser.parse(jsonString);

            JSONObject jsonObject = (JSONObject) parsedObject.get(0);
            metricJSONArray = (JSONArray) jsonObject.get("metrics");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(metricJSONArray);

    }

    @SuppressWarnings("unchecked")
    public Stream<JSONObject> getStream(JSONArray array) {
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(array.iterator(), 0), false);
    }


    public List<SummaryMetaspace> getSummaryMetaspace(JSONArray metricsArray) {
        Stream<JSONObject> summaryMetaspaceMetric = getStream(metricsArray);

        Map<Long, List<SummaryMetaspace>> summaryMetaspaceMap = summaryMetaspaceMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.MetaspaceSummary"))
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
                }).collect(Collectors.groupingBy(SummaryMetaspace::getTimestamp));

        return summaryMetaspaceMap.entrySet().stream()
                .map(s -> {
                    SummaryMetaspace cpu = s.getValue().get(0);
                    SummaryMetaspace cpu2 = s.getValue().get(1);
                    SummaryMetaspace cpu3 = s.getValue().get(2);

                    if (cpu2.getName().toLowerCase().endsWith("metaspace.committed")) {
                        cpu.setCommittedValue(cpu2.getCommittedValue());
                    } else if (cpu2.getName().toLowerCase().endsWith("metaspace.used")) {
                        cpu.setUsedValue(cpu2.getUsedValue());
                    } else {
                        cpu.setReservedValue(cpu2.getReservedValue());
                    }

                    if (cpu3.getName().toLowerCase().endsWith("metaspace.committed")) {
                        cpu.setCommittedValue(cpu3.getCommittedValue());
                    } else if (cpu3.getName().toLowerCase().endsWith("metaspace.used")) {
                        cpu.setUsedValue(cpu3.getUsedValue());
                    } else {
                        cpu.setReservedValue(cpu3.getReservedValue());
                    }

                    return cpu;
                }).collect(Collectors.toList());
    }

    public String getTime(long timestamp) {
        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(date);
    }

}
