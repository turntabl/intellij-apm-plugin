package io.turntabl.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.model.events.CollapsedEventSample;
import io.turntabl.model.events.EventStackTrace;
import io.turntabl.model.events.JfrMethodSample;
import org.json.simple.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JfrMethodSampleUtil {
    private final JsonUtility jsonUtils;
    private ObjectMapper mapper = new ObjectMapper();


    public JfrMethodSampleUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public List<JfrMethodSample> getJfrMethodSample(String jsonString) {
        return jsonUtils.readEventsJson(jsonString)
                .filter(e -> e.get("eventType").toString().toLowerCase().startsWith("jfrmethodsample"))
                .map(j -> {
                    JfrMethodSample jfrMethodSample = null;
                    try {
                        jfrMethodSample = mapper.readValue(j.toJSONString(), JfrMethodSample.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return jfrMethodSample;
                }).collect(Collectors.toList());
    }

    public List<EventStackTrace> getStackTrace(String stackTrace) {
        JSONArray payLoad = jsonUtils.getStackPayload(stackTrace);
        List<EventStackTrace> traceList = null;

        if (payLoad != null) {
            traceList = jsonUtils.getStream(payLoad).map(s -> {
                EventStackTrace stackTracePayload = null;
                try {
                    stackTracePayload = mapper.readValue(s.toJSONString(), EventStackTrace.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return stackTracePayload;
            }).collect(Collectors.toList());
        }
        return traceList;
    }


    public List<String> writeEventStackToList(List<CollapsedEventSample> collapsedEventSampleList) {
        List<String> stack = new ArrayList<>();

        collapsedEventSampleList.forEach(s -> {
            String line = "";

            String threadName = s.getThreadName();
            List<EventStackTrace> eventStackTraces = s.getStackTraceList();

            line += threadName + ";";

            for (int i = eventStackTraces.size() - 1; i > -1; i--) {
                String desc = eventStackTraces.get(i).getDesc();
                int index = desc.indexOf("(");

                if (i == 0) {
                    line += desc.substring(0, index);
                } else {
                    line += desc.substring(0, index) + ";";
                }
            }
            line += " 1";
            stack.add(line);
        });
        return stack;
    }

    public List<String> writeEventStackToListWithoutThreadNames(List<CollapsedEventSample> collapsedEventSampleList) {
        List<String> stack = new ArrayList<>();

        collapsedEventSampleList.forEach(s -> {
            String line = "";

            List<EventStackTrace> eventStackTraces = s.getStackTraceList();

            for (int i = eventStackTraces.size() - 1; i > -1; i--) {
                String desc = eventStackTraces.get(i).getDesc();
                int index = desc.indexOf("(");

                if (i == 0) {
                    line += desc.substring(0, index);
                } else {
                    line += desc.substring(0, index) + ";";
                }
            }
            line += " 1";
            stack.add(line);
        });
        return stack;
    }
}
