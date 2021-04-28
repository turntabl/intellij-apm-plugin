package io.turntabl.ui.model;

import java.util.Date;
import java.util.HashMap;

public class ThreadCpuLoad {
    private Date startTime;
    private String type;
    private Double userValue;
    private Double systemValue;
    private HashMap<String, String> attributes;

    public ThreadCpuLoad(Date startTime, String type, Double userValue, Double systemValue, HashMap<String, String> attributes) {
        this.startTime = startTime;
        this.type = type;
        this.userValue = userValue;
        this.systemValue = systemValue;
        this.attributes = attributes;
    }

    public Date getStartTime() {
        return startTime;
    }

    public String getType(){
        return type;
    }

    public Double getUserValue() {
        return userValue;
    }

    public Double getSystemValue() {
        return systemValue;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }
}
