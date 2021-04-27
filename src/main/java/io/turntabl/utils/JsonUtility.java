package io.turntabl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.ui.model.CPULoad;
import io.turntabl.ui.model.ThreadAllocationStatistics;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonUtility {
    private JSONParser jsonParser = new JSONParser();
    private ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings("unchecked")
    public JSONArray readMetricsJson() {
        JSONArray metricJSONArray = null;
        try {
            String filepath = "src/main/resources/metrics.json";
            JSONArray parsedObject = (JSONArray) jsonParser.parse(new FileReader(filepath));

            JSONObject jsonObject = (JSONObject) parsedObject.get(0);
            metricJSONArray = (JSONArray) jsonObject.get("metrics");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return metricJSONArray;

    }

    @SuppressWarnings("unchecked")
    public Stream<JSONObject> getStream(JSONArray array) {
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(array.iterator(), 0), false);
    }

    public List<CPULoad> getCPULoad(JSONArray metricsArray) {
        Stream<JSONObject>cpuLoadMetric = getStream(metricsArray);

        return cpuLoadMetric
                .filter(m -> m.get("name").toString().startsWith("jfr.CPULoad"))
                .map(c -> {
                    CPULoad cpuLoad = null;
                    try {
                        cpuLoad = mapper.readValue(c.toJSONString(), CPULoad.class);

                        String name = cpuLoad.getName().toLowerCase();
                        double value = Double.parseDouble(c.get("value").toString());

                        if (name.endsWith("jvmuser")) {
                            cpuLoad.setJvmUserValue(value);
                        } else if (name.endsWith("jvmsystem")) {
                            cpuLoad.setJvmSytemValue(value);
                        } else {
                            cpuLoad.setMachineTotalValue(value);
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return cpuLoad;
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

    public static void main(String[] args) {
        JsonUtility util = new JsonUtility();
        JSONArray jsonArray = util.readMetricsJson();
        util.getCPULoad(jsonArray).forEach(System.out::println);
//        util.getThreadAllocationStatistics(jsonArray).forEach(System.out::println);
    }
}
