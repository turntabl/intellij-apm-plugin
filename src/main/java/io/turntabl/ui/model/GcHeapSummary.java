package io.turntabl.ui.model;

import java.util.Date;

public class GcHeapSummary {
    private Date startTime;
    private Double heapCommittedSize;
    private Double reservedSize;
    private Double heapUsed;

    // add type and attributes

    public GcHeapSummary(Date startTime, Double heapCommittedSize, Double reservedSize, Double heapUsed) {
        this.startTime = startTime;
        this.heapCommittedSize = heapCommittedSize;
        this.reservedSize = reservedSize;
        this.heapUsed = heapUsed;
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
}
