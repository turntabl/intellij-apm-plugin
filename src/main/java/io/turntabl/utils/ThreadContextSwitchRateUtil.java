package io.turntabl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.turntabl.model.metrics.ThreadContextSwitchRate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadContextSwitchRateUtil {

    private final JsonUtility jsonUtils;
    private ObjectMapper mapper = new ObjectMapper();

    public ThreadContextSwitchRateUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public List<ThreadContextSwitchRate> getThreadContextSwitchRate(JSONArray metricsArray) {
        Stream<JSONObject> threadContextSwitchRateMetric = jsonUtils.getStream(metricsArray);
        return threadContextSwitchRateMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.ThreadContextSwitchRate"))
                .map(c -> {
                    ThreadContextSwitchRate threadContextSwitchRate = null;
                    try {
                        threadContextSwitchRate = mapper.readValue(c.toJSONString(), ThreadContextSwitchRate.class);

                        String name = threadContextSwitchRate.getName().toLowerCase();
                        double value = Double.parseDouble(c.get("value").toString());
                        threadContextSwitchRate.setValue(value);
//                        if (name.endsWith("user")) {
//                            threadContextSwitchRate.setUserValue(value);
//                        } else {
//                            threadContextSwitchRate.setSystemValue(value);
//                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return threadContextSwitchRate;
                }).collect(Collectors.toList());
    }

    public Map<Long, List<ThreadContextSwitchRate>> groupThreadContextSwitchRateByTimestamp(List<ThreadContextSwitchRate> threadContextSwitchRateList) {
        return threadContextSwitchRateList
                .stream()
                .collect(Collectors.groupingBy(ThreadContextSwitchRate::getTimestamp));
    }

    public List<ThreadContextSwitchRate> getThreadContextSwitchRateConsolidated(List<ThreadContextSwitchRate> list) {
        Map<Long, List<ThreadContextSwitchRate>> threadContextSwitchRateMap = groupThreadContextSwitchRateByTimestamp(list);

        return threadContextSwitchRateMap.values().stream()
                .map(threadContextSwitchRateList -> {
                    ThreadContextSwitchRate threadContextSwitchRate = threadContextSwitchRateList.get(0);
                    ThreadContextSwitchRate threadContextSwitchRate2 = threadContextSwitchRateList.get(1);


                    threadContextSwitchRate.setValue(threadContextSwitchRate2.getValue());

//                    if (threadContextSwitchRate2.getName().toLowerCase().endsWith("Rate")) {
//                        threadContextSwitchRate.setValue(threadContextSwitchRate2.getValue());
//                    } else {
//                        threadContextSwitchRate.setValue(threadContextSwitchRate2.getValue());
//                    }


                    return threadContextSwitchRate;
                }).collect(Collectors.toList());
    }

    public String formatDate(Long timestamp) {
        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("ss");
        return formatter.format(date);
    }




}
