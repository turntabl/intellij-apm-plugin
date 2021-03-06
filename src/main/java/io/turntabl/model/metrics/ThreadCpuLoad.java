package io.turntabl.model.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadCpuLoad {
    private String name;
    @JsonProperty("timestamp")
    private Long timestamp;
    private String type;
    private Double userValue;
    private Double systemValue;
    private HashMap<String, String> attributes;

    public ThreadCpuLoad(String name, Long timestamp, String type, Double userValue, Double systemValue, HashMap<String, String> attributes) {
        this.name = name;
        this.timestamp = timestamp;
        this.type = type;
        this.userValue = userValue;
        this.systemValue = systemValue;
        this.attributes = attributes;
    }

    public ThreadCpuLoad() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getUserValue() {
        return userValue;
    }

    public void setUserValue(Double userValue) {
        this.userValue = userValue;
    }

    public Double getSystemValue() {
        return systemValue;
    }

    public void setSystemValue(Double systemValue) {
        this.systemValue = systemValue;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "ThreadCpuLoad{" +
                "name='" + name + '\'' +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", userValue=" + userValue +
                ", systemValue=" + systemValue +
                ", attributes=" + attributes +
                '}';
    }
}
