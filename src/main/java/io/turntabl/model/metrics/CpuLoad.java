package io.turntabl.model.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CpuLoad {
    private String name;
    @JsonProperty("timestamp")
    private Long timestamp;
    private String type;
    private double jvmUserValue;
    private double jvmSystemValue;
    private double machineTotalValue;
    private HashMap<String, String> attributes;

    public CpuLoad() {
    }

    public CpuLoad(String name, Long timestamp, String type, Double jvmUserValue, Double jvmSystemValue, Double machineTotalValue, HashMap<String, String> attributes) {
        this.name = name;
        this.timestamp = timestamp;
        this.type = type;
        this.jvmUserValue = jvmUserValue;
        this.jvmSystemValue = jvmSystemValue;
        this.machineTotalValue = machineTotalValue;
        this.attributes = attributes;
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

    public double getJvmUserValue() {
        return jvmUserValue;
    }

    public void setJvmUserValue(double jvmUserValue) {
        this.jvmUserValue = jvmUserValue;
    }

    public double getJvmSystemValue() {
        return jvmSystemValue;
    }

    public void setJvmSystemValue(double jvmSystemValue) {
        this.jvmSystemValue = jvmSystemValue;
    }

    public double getMachineTotalValue() {
        return machineTotalValue;
    }

    public void setMachineTotalValue(double machineTotalValue) {
        this.machineTotalValue = machineTotalValue;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "CpuLoad{" +
                "timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", jvmUserValue=" + jvmUserValue +
                ", jvmSystemValue=" + jvmSystemValue +
                ", machineTotalValue=" + machineTotalValue +
                ", attributes=" + attributes +
                '}';
    }
}

