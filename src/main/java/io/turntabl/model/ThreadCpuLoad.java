package io.turntabl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadCpuLoad {
    private String name;
    @JsonProperty("timestamp")
    private Long startTime;
    private String type;
    private Double userValue;
    private Double systemValue;
    private HashMap<String, String> attributes;

    public ThreadCpuLoad(String name, Long startTime, String type, Double userValue, Double systemValue, HashMap<String, String> attributes) {
        this.name = name;
        this.startTime = startTime;
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

    public Long getStartTime() {
        return startTime;
    }

    public String getType(){
        return type;
    }

    public Double getUserValue() {
        return userValue;
    }

    public Double getSystemValue() {
        return systemValue;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserValue(Double userValue) {
        this.userValue = userValue;
    }

    public void setSystemValue(Double systemValue) {
        this.systemValue = systemValue;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "ThreadCpuLoad{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", type='" + type + '\'' +
                ", userValue=" + userValue +
                ", systemValue=" + systemValue +
                ", attributes=" + attributes +
                '}';
    }
}
