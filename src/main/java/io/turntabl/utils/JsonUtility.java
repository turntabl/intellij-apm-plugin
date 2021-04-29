package io.turntabl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.ui.model.CpuLoad;
import io.turntabl.ui.model.ThreadAllocationStatistics;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonUtility {
    private JSONParser jsonParser = new JSONParser();
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

    public List<CpuLoad> getCPULoad(JSONArray metricsArray) {
        Stream<JSONObject> cpuLoadMetric = getStream(metricsArray);

        Map<Long, List<CpuLoad>> cpuLoadMap = cpuLoadMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.cpuload"))
                .map(c -> {
                    CpuLoad cpuLoad = null;
                    try {
                        cpuLoad = mapper.readValue(c.toJSONString(), CpuLoad.class);

                        String name = cpuLoad.getName().toLowerCase();
                        double value = Double.parseDouble(c.get("value").toString());

                        if (name.endsWith("jvmuser")) {
                            cpuLoad.setJvmUserValue(value);
                        } else if (name.endsWith("jvmsystem")) {
                            cpuLoad.setJvmSystemValue(value);
                        } else {
                            cpuLoad.setMachineTotalValue(value);
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return cpuLoad;
                }).collect(Collectors.groupingBy(CpuLoad::getStartTime));

        return cpuLoadMap.entrySet().stream()
                .map(s -> {
                    CpuLoad cpu = s.getValue().get(0);
                    CpuLoad cpu2 = s.getValue().get(1);
                    CpuLoad cpu3 = s.getValue().get(2);

                    if (cpu2.getName().toLowerCase().endsWith("jvmuser")) {
                        cpu.setJvmUserValue(cpu2.getJvmUserValue());
                    } else if (cpu2.getName().toLowerCase().endsWith("jvmsystem")) {
                        cpu.setJvmSystemValue(cpu2.getJvmSystemValue());
                    } else {
                        cpu.setMachineTotalValue(cpu2.getMachineTotalValue());
                    }

                    if (cpu3.getName().toLowerCase().endsWith("jvmuser")) {
                        cpu.setJvmUserValue(cpu3.getJvmUserValue());
                    } else if (cpu3.getName().toLowerCase().endsWith("jvmsystem")) {
                        cpu.setJvmSystemValue(cpu3.getJvmSystemValue());
                    } else {
                        cpu.setMachineTotalValue(cpu3.getMachineTotalValue());
                    }

                    return cpu;
                }).collect(Collectors.toList());
    }

    public List<ThreadAllocationStatistics> getThreadAllocationStatistics(JSONArray jsonArray) {
        Stream<JSONObject> threadAllocStatsStream = getStream(jsonArray);

        return threadAllocStatsStream
                .filter(t -> t.get("name").toString().startsWith("jfr.ThreadAllocationStatistics"))
                .map(c -> {
                    ThreadAllocationStatistics tas = null;
                    try {
                        tas = mapper.readValue(c.toJSONString(), ThreadAllocationStatistics.class);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return tas;
                }).collect(Collectors.toList());
    }

}
