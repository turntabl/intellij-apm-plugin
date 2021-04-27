package io.turntabl.ui.model;

public class ThreadAllocationStatistics {
    private String startTime;
    private String allocated;
    private String thread;

    public ThreadAllocationStatistics(String startTime, String allocated, String thread) {
        this.startTime = startTime;
        this.allocated = allocated;
        this.thread = thread;
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