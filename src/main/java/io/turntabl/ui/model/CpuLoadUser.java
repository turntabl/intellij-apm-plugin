package io.turntabl.ui.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.net.ntp.TimeStamp;


import java.sql.Timestamp;
import java.util.Map;

public class CpuLoadUser {
    private String name;
    private String type;
    private Double value;
    private Timestamp timestamp;
    private String attributes;
    //private Map<String, String> attributes;
    //@JsonProperty("data") final Map<String, String> attributes


    public CpuLoadUser(String name, String type, Double value, Timestamp timestamp, String attributes) {
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

    public String getAttributes() {
        return attributes;
    }

    /*    public Map<String, String> getAttributes() {
        return attributes;
    }*/


}
