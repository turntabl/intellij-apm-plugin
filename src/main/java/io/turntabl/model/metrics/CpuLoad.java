package io.turntabl.model.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CpuLoad {
    private String name;
    @JsonProperty("timestamp")
    private Long startTime;
    private String type;
    private double jvmUserValue;
    private double jvmSystemValue;
    private double machineTotalValue;
    private HashMap<String, String> attributes;

    public CpuLoad() {
    }

    public CpuLoad(String name, Long startTime, String type, Double jvmUserValue, Double jvmSystemValue, Double machineTotalValue, HashMap<String, String> attributes) {
        this.name = name;
        this.startTime = startTime;
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

    public Long getStartTime() {
        return startTime;
    }

    public String getType() {
        return type;
    }

    public double getJvmUserValue() {
        return jvmUserValue;
    }

    public double getJvmSystemValue() {
        return jvmSystemValue;
    }

    public double getMachineTotalValue() {
        return machineTotalValue;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setJvmUserValue(double jvmUserValue) {
        this.jvmUserValue = jvmUserValue;
    }

    public void setJvmSystemValue(double jvmSystemValue) {
        this.jvmSystemValue = jvmSystemValue;
    }

    public void setMachineTotalValue(double machineTotalValue) {
        this.machineTotalValue = machineTotalValue;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "CpuLoad{" +
                "startTime=" + startTime +
                ", type='" + type + '\'' +
                ", jvmUserValue=" + jvmUserValue +
                ", jvmSystemValue=" + jvmSystemValue +
                ", machineTotalValue=" + machineTotalValue +
                ", attributes=" + attributes +
                '}';
    }
}

