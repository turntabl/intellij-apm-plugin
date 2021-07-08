package io.turntabl.model.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class GarbageCollection {
    protected String name;
    protected String type;
    protected HashMap<String, Double> value;
    protected Long timestamp;
    @JsonProperty("interval.ms")
    protected int interval;
    protected HashMap<String, String> attributes;


    public GarbageCollection() {
    }

    public GarbageCollection(String name, String type, HashMap<String, Double> value, Long timestamp, int interval, HashMap<String, String> attributes) {
        this.name = name;
        this.type = type;
        this.value = value;
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

    public HashMap<String, Double> getValue() {
        return value;
    }

    public void setValue(HashMap<String, Double> value) {
        this.value = value;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
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
        return "GarbageCollection{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}
