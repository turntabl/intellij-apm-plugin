package io.turntabl.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.model.events.CollapsedEventSample;
import io.turntabl.model.events.EventStackTrace;
import io.turntabl.model.events.JfrMethodSample;
import org.json.simple.JSONArray;

import java.io.*;
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

    public void writeToFile(BufferedWriter writer, String text) {
        try {
            if (text.trim().equals("\n")) {
                writer.newLine();
            } else {
                writer.write(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeEventStackToFile(List<CollapsedEventSample> collapsedEventSampleList) throws IOException {

        File file = new File("C:/flamegraph/stackTraces.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        ) {
            collapsedEventSampleList.forEach(s -> {
                String threadName = s.getThreadName();
                List<EventStackTrace> eventStackTraces = s.getStackTraceList();

                if (file.length() > 0) {
                    writeToFile(writer, "\n");
                }

                writeToFile(writer, threadName + ";");

                for (int i = eventStackTraces.size() - 1; i > -1; i--){
                    String desc = eventStackTraces.get(i).getDesc();
                    int index = desc.indexOf("(");

                    if (i == 0){
                        writeToFile(writer, desc.substring(0, index));
                    } else {
                        writeToFile(writer, desc.substring(0, index) + ";");
                    }
                }
                writeToFile(writer, " 1");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeEventStackToFileWithoutThreadNames(collapsedEventSampleList);
    }

    public void writeEventStackToFileWithoutThreadNames(List<CollapsedEventSample> collapsedEventSampleList) throws IOException {

        File file = new File("C:/flamegraph/stackTracesNoThreadName.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        ) {
            collapsedEventSampleList.forEach(s -> {
                List<EventStackTrace> eventStackTraces = s.getStackTraceList();

                if (file.length() > 0) {
                    writeToFile(writer, "\n");
                }

                for (int i = eventStackTraces.size() - 1; i > -1; i--){
                    String desc = eventStackTraces.get(i).getDesc();
                    int index = desc.indexOf("(");

                    if (i == 0){
                        writeToFile(writer, desc.substring(0, index));
                    } else {
                        writeToFile(writer, desc.substring(0, index) + ";");
                    }
                }
                writeToFile(writer, " 1");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
