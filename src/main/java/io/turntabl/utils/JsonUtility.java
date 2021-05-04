package io.turntabl.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonUtility {
    private JSONParser jsonParser = new JSONParser();

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

//    public List<ThreadAllocationStatistics> getThreadAllocationStatistics(JSONArray jsonArray) {
//        Stream<JSONObject> threadAllocStatsStream = getStream(jsonArray);
//
//        return threadAllocStatsStream
//                .filter(t -> t.get("name").toString().startsWith("jfr.ThreadAllocationStatistics"))
//                .map(c -> {
//                    ThreadAllocationStatistics tas = null;
//                    try {
//                        tas = mapper.readValue(c.toJSONString(), ThreadAllocationStatistics.class);
//
//                    } catch (JsonProcessingException e) {
//                        e.printStackTrace();
//                    }
//                    return tas;
//                }).collect(Collectors.toList());
//    }

}
