package io.turntabl.model.metrics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GcHeapSummary {
    private String name;
    @JsonProperty("timestamp")
    private Long timestamp;
    private String type;
    private Double heapCommittedSize;
    private Double reservedSize;
    private Double heapUsed;
    private HashMap<String, String> attributes;

    public GcHeapSummary(String name, Long timestamp, String type, Double heapCommittedSize, Double reservedSize, Double heapUsed, HashMap<String, String> attributes) {
        this.name = name;
        this.timestamp = timestamp;
        this.type = type;
        this.heapCommittedSize = heapCommittedSize;
        this.reservedSize = reservedSize;
        this.heapUsed = heapUsed;
        this.attributes = attributes;
    }

    public GcHeapSummary() {
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

    public Double getHeapCommittedSize() {
        return heapCommittedSize;
    }

    public void setHeapCommittedSize(Double heapCommittedSize) {
        this.heapCommittedSize = heapCommittedSize;
    }

    public Double getReservedSize() {
        return reservedSize;
    }

    public void setReservedSize(Double reservedSize) {
        this.reservedSize = reservedSize;
    }

    public Double getHeapUsed() {
        return heapUsed;
    }

    public void setHeapUsed(Double heapUsed) {
        this.heapUsed = heapUsed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "GcHeapSummary{" +
                "name='" + name + '\'' +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", heapCommittedSize=" + heapCommittedSize +
                ", reservedSize=" + reservedSize +
                ", heapUsed=" + heapUsed +
                ", attributes=" + attributes +
                '}';
    }
}
