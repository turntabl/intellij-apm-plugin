package io.turntabl.ui.model;

import java.util.HashMap;

public class ThreadAllocationStatistics {
    private String startTime;
    private String allocated;
    private String thread;
    private HashMap<String, String> attributes;

    public ThreadAllocationStatistics(String startTime, String allocated, String thread, HashMap<String, String> attributes) {
        this.startTime = startTime;
        this.allocated = allocated;
        this.thread = thread;
        this.attributes = attributes;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getAllocated() {
        return allocated;
    }

    public void setAllocated(String allocated) {
        this.allocated = allocated;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }
}