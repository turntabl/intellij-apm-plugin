package io.turntabl.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadAllocationStatistics {
    private String name;
    private String type;
    private double value;
    @JsonProperty("timestamp")
    private BigInteger startTime;
    private Map<String, String> attributes;

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    public ThreadAllocationStatistics() {
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

    public BigInteger getStartTime() {
        return startTime;
    }

    public void setStartTime(BigInteger startTime) {
        this.startTime = startTime;
    }

    public ThreadAllocationStatistics(String name, String type, double value, BigInteger startTime, Map<String, String> attributes) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.startTime = startTime;
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
