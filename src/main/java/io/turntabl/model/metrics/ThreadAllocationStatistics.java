package io.turntabl.model.metrics;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadAllocationStatistics {
    private String name;
    private String type;
    private double value;
    @JsonProperty("timestamp")
    private long startTime;
    private Map<String, String> attributes;

    public ThreadAllocationStatistics() {
    }

    public ThreadAllocationStatistics(String name, String type, double value, long startTime, Map<String, String> attributes) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.startTime = startTime;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "ThreadAllocationStatistics{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", startTime=" + startTime +
                ", attributes=" + attributes +
                '}';
    }
}