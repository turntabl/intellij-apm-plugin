<<<<<<< HEAD:src/main/java/io/turntabl/model/metrics/SummaryMetaspace.java
package io.turntabl.model.metrics;
=======
package io.turntabl.model;
>>>>>>> main:src/main/java/io/turntabl/model/SummaryMetaspace.java

import java.util.HashMap;

public class SummaryMetaspace {
    private String name;
    private String type;
    private Double committedValue;
    private Double usedValue;
    private Double reservedValue;
    private Long timestamp;
    private HashMap<String, String> attributes;

    public SummaryMetaspace() {
    }

    public SummaryMetaspace(String name, String type, Double committedValue, Double usedValue, Double reservedValue, Long timestamp, HashMap<String, String> attributes) {
        this.name = name;
        this.type = type;
        this.committedValue = committedValue;
        this.usedValue = usedValue;
        this.reservedValue = reservedValue;
        this.timestamp = timestamp;
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

    public Double getCommittedValue() {
        return committedValue;
    }

    public void setCommittedValue(Double committedValue) {
        this.committedValue = committedValue;
    }

    public Double getUsedValue() {
        return usedValue;
    }

    public void setUsedValue(Double usedValue) {
        this.usedValue = usedValue;
    }

    public Double getReservedValue() {
        return reservedValue;
    }

    public void setReservedValue(Double reservedValue) {
        this.reservedValue = reservedValue;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "SummaryMetaspace{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", committedValue=" + committedValue +
                ", usedValue=" + usedValue +
                ", reservedValue=" + reservedValue +
                ", timestamp=" + timestamp +
                ", attributes=" + attributes +
                '}';
    }
}
