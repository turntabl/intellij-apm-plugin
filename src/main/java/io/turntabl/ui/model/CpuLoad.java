package io.turntabl.ui.model;

import java.util.Date;

public class CpuLoad {
    private Date startTime;
    private Double jvmUserValue;
    private Double jvmSystemValue;
    private Double machineTotalValue;
    private Object attributes;

    public CpuLoad(Date startTime, Double jvmUserValue, Double jvmSystemValue, Double machineTotalValue) {
        this.startTime = startTime;
        this.jvmUserValue = jvmUserValue;
        this.jvmSystemValue = jvmSystemValue;
        this.machineTotalValue = machineTotalValue;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Double getJvmUserValue() {
        return jvmUserValue;
    }

    public Double getJvmSystemValue() {
        return jvmSystemValue;
    }

    public Double getMachineTotalValue() {
        return machineTotalValue;
    }

    public Object getAttributes() {
        return attributes;
    }
}
