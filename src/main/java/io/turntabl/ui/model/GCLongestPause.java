package io.turntabl.ui.model;

import java.util.HashMap;

public class GCLongestPause {
    private String name;
    private String type;
    private HashMap<String, String> values;
    private Long timestamp;
    private Long interval;
    private HashMap<String, String> attributes;

    public GCLongestPause() {
    }

    public GCLongestPause(String name, String type, HashMap<String, String> values, Long timestamp, Long interval, HashMap<String, String> attributes) {
        this.name = name;
        this.type = type;
        this.values = values;
        this.timestamp = timestamp;
        this.interval = interval;
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

    public HashMap<String, String> getValues() {
        return values;
    }

    public void setValues(HashMap<String, String> values) {
        this.values = values;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "GCLongestPause{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", values=" + values +
                ", timestamp=" + timestamp +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}
