package io.turntabl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.model.metrics.ThreadCpuLoad;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadCpuLoadUtil {
    private final JsonUtility jsonUtils;
    private ObjectMapper mapper = new ObjectMapper();

    public ThreadCpuLoadUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public List<ThreadCpuLoad> getThreadCpuLoad(JSONArray metricsArray) {
        Stream<JSONObject> threadCpuLoadMetric = jsonUtils.getStream(metricsArray);
        return threadCpuLoadMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.threadcpuload"))
                .map(c -> {
                    ThreadCpuLoad threadCpuLoad = null;
                    try {
                        threadCpuLoad = mapper.readValue(c.toJSONString(), ThreadCpuLoad.class);

                        String name = threadCpuLoad.getName().toLowerCase();
                        double value = Double.parseDouble(c.get("value").toString());

                        if (name.endsWith("user")) {
                            threadCpuLoad.setUserValue(value);
                        } else {
                            threadCpuLoad.setSystemValue(value);
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return threadCpuLoad;
                }).collect(Collectors.toList());
    }

    public Map<Long, List<ThreadCpuLoad>> groupThreadCpuLoadByTimestamp(List<ThreadCpuLoad> threadCpuLoadList) {
        return threadCpuLoadList
                .stream()
                .collect(Collectors.groupingBy(ThreadCpuLoad::getTimestamp));
    }

    public List<ThreadCpuLoad> getThreadCpuLoadConsolidated(List<ThreadCpuLoad> list) {
        Map<Long, List<ThreadCpuLoad>> threadCpuLoadMap = groupThreadCpuLoadByTimestamp(list);

        return threadCpuLoadMap.values().stream()
                .map(threadCpuLoadList -> {
                    ThreadCpuLoad threadCpuLoad = threadCpuLoadList.get(0);
                    ThreadCpuLoad threadCpuLoad2 = threadCpuLoadList.get(1);


                    if (threadCpuLoad2.getName().toLowerCase().endsWith("user")) {
                        threadCpuLoad.setUserValue(threadCpuLoad2.getUserValue());
                    } else {
                        threadCpuLoad.setSystemValue(threadCpuLoad2.getSystemValue());
                    }


                    return threadCpuLoad;
                }).collect(Collectors.toList());
    }

    public String formatDate(Long timestamp) {
        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("ss");
        return formatter.format(date);
    }




}
