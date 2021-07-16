package io.turntabl.model.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class JfrSocketRead {
    protected String name;
    @JsonProperty("timestamp")
    protected Long timestamp;
    protected String type;
    protected HashMap<String, Double> value;
    @JsonProperty("interval.ms")
    protected int interval;
    protected HashMap<String, String> attributes;

    public JfrSocketRead(String name, Long timestamp, String type, HashMap<String, Double> value, int interval, HashMap<String, String> attributes) {
        this.name = name;
        this.timestamp = timestamp;
        this.type = type;
        this.value = value;
        this.interval = interval;
        this.attributes = attributes;
    }

    public JfrSocketRead() {
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

    public HashMap<String, Double> getValue() {
        return value;
    }

    public void setValue(HashMap<String, Double> value) {
        this.value = value;
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
        return "JfrSocketRead{" +
                "name='" + name + '\'' +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}
