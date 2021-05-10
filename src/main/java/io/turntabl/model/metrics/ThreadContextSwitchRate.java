<<<<<<< HEAD:src/main/java/io/turntabl/model/metrics/ThreadContextSwitchRate.java
package io.turntabl.model.metrics;
=======
package io.turntabl.model;
>>>>>>> main:src/main/java/io/turntabl/model/ThreadContextSwitchRate.java


import java.util.HashMap;

public class ThreadContextSwitchRate {
    private String name;
    private String type;
    private Double value;
    private Long timestamp;
    private HashMap<String, String> attributes;


    public ThreadContextSwitchRate(String name, String type, Double value, Long timestamp, HashMap<String, String> attributes) {
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

    public Long getTimestamp() {
        return timestamp;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }



}
