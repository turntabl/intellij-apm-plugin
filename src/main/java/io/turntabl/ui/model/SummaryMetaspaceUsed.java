package io.turntabl.ui.model;

import java.sql.Timestamp;
import java.util.HashMap;

public class SummaryMetaspaceUsed {
    private String name;
    private String type;
    private Double value;
    private Timestamp timestamp;
    private HashMap<String, String> attributes;

    public SummaryMetaspaceUsed(String name, String type, Double value, Timestamp timestamp, HashMap<String, String> attributes) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.timestamp = timestamp;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Double getValue() {
        return value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }
}
