package io.turntabl.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonUtility {
    private final JSONParser jsonParser = new JSONParser();

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

    public String getTime(long timestamp) {
        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(date);
    }

}
