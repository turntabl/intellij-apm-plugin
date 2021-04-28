package io.turntabl.ui.model;


import java.util.Date;
import java.util.HashMap;

public class GcHeapSummary {
    private Date startTime;
    private String type;
    private Double heapCommittedSize;
    private Double reservedSize;
    private Double heapUsed;
    private HashMap<String, String> attributes;

    public GcHeapSummary(Date startTime, String type, Double heapCommittedSize, Double reservedSize, Double heapUsed, HashMap<String, String> attributes) {
        this.startTime = startTime;
        this.type = type;
        this.heapCommittedSize = heapCommittedSize;
        this.reservedSize = reservedSize;
        this.heapUsed = heapUsed;
        this.attributes = attributes;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Double getHeapCommittedSize() {
        return heapCommittedSize;
    }

    public Double getReservedSize() {
        return reservedSize;
    }

    public Double getHeapUsed() {
        return heapUsed;
    }

    public String getType() {
        return type;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }
}
