package io.turntabl.ui.model;

import java.util.Date;

public class CpuLoadMachineTotal {
    private String type;
    private Double value;
    private Date timestamp;
    private Object attributes;

    public CpuLoadMachineTotal(String type, Double value, Date timestamp) {
        this.type = type;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public Double getValue() {
        return value;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
