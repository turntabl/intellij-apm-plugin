package io.turntabl.ui.model;

import java.util.Date;
import java.util.HashMap;

public class CpuLoad {
    private Date startTime;
    private String type;
    private Double jvmUserValue;
    private Double jvmSystemValue;
    private Double machineTotalValue;
    private HashMap<String, String> attributes;

    public CpuLoad(Date startTime, String type, Double jvmUserValue, Double jvmSystemValue, Double machineTotalValue, HashMap<String, String> attributes) {
        this.startTime = startTime;
        this.type = type;
        this.jvmUserValue = jvmUserValue;
        this.jvmSystemValue = jvmSystemValue;
        this.machineTotalValue = machineTotalValue;
        this.attributes = attributes;
    }

    public Date getStartTime() {
        return startTime;
    }

    public String getType(){
        return type;
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
