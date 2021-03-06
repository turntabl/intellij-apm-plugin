package io.turntabl.model.metrics;

import java.util.HashMap;

public class ThreadContextSwitchRate {
    private String name;
    private String type;
    private Double value;
    private Long timestamp;
    private HashMap<String, String> attributes;

    public ThreadContextSwitchRate() {
    }

    public ThreadContextSwitchRate(String name, String type, Double value, Long timestamp, HashMap<String, String> attributes) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.timestamp = timestamp;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }
}
