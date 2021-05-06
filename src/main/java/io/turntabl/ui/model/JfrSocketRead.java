package io.turntabl.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JfrSocketRead {
    protected String name;
    @JsonProperty("timestamp")
    protected Long startTime;
    protected String type;
    protected HashMap<String, Double> value;
    @JsonProperty("interval.ms")
    protected int interval;
    protected HashMap<String, String> attributes;

    public JfrSocketRead(String name, Long startTime, String type, HashMap<String, Double> value, int interval, HashMap<String, String> attributes) {
        this.name = name;
        this.startTime = startTime;
        this.type = type;
        this.value = value;
        this.interval = interval;
        this.attributes = attributes;
    }

    public JfrSocketRead() {
    }

    public String getName(){
        return name;
    }

    public Long getStartTime() {
        return startTime;
    }

    public String getType() {
        return type;
    }

    public HashMap<String, Double> getValue() {
        return value;
    }

    public int getInterval() {
        return interval;
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

    public void setValue(HashMap<String, Double> value) {
        this.value = value;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "JfrSocketRead{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}
