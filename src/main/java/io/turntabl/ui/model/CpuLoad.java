package io.turntabl.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CpuLoad {
    private String name;
    @JsonProperty("timestamp")
    private Long startTime;
    private String type;
    private Double jvmUserValue;
    private Double jvmSystemValue;
    private Double machineTotalValue;
    private HashMap<String, String> attributes;

    public CpuLoad(){}

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

    public Double getJvmUserValue() {
        return jvmUserValue;
    }

    public Double getJvmSystemValue() {
        return jvmSystemValue;
    }

    public Double getMachineTotalValue() {
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

    public void setJvmUserValue(Double jvmUserValue) {
        this.jvmUserValue = jvmUserValue;
    }

    public void setJvmSystemValue(Double jvmSystemValue) {
        this.jvmSystemValue = jvmSystemValue;
    }

    public void setMachineTotalValue(Double machineTotalValue) {
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