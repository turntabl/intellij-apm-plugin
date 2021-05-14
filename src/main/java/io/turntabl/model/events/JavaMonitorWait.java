package io.turntabl.model.events;

import java.util.HashMap;

public class JavaMonitorWait {
    private String startTime;
    private String duration;
    private String endTime;
    private String eventThread;
    private String monitorClass;
    private String notifierThread;
    private String timeOut;
    private String timedOut;
    private String monitorAddress;
    private HashMap<String, String> attributes;


    public JavaMonitorWait(String startTime, String duration, String endTime, String eventThread, String monitorClass, String notifierThread, String timeOut, String timedOut, String monitorAddress, HashMap<String, String> attributes) {
        this.startTime = startTime;
        this.duration = duration;
        this.endTime = endTime;
        this.eventThread = eventThread;
        this.monitorClass = monitorClass;
        this.notifierThread = notifierThread;
        this.timeOut = timeOut;
        this.timedOut = timedOut;
        this.monitorAddress = monitorAddress;
        this.attributes = attributes;
    }

    public JavaMonitorWait() {
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEventThread() {
        return eventThread;
    }

    public void setEventThread(String eventThread) {
        this.eventThread = eventThread;
    }

    public String getMonitorClass() {
        return monitorClass;
    }

    public void setMonitorClass(String monitorClass) {
        this.monitorClass = monitorClass;
    }

    public String getNotifierThread() {
        return notifierThread;
    }

    public void setNotifierThread(String notifierThread) {
        this.notifierThread = notifierThread;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getTimedOut() {
        return timedOut;
    }

    public void setTimedOut(String timedOut) {
        this.timedOut = timedOut;
    }

    public String getMonitorAddress() {
        return monitorAddress;
    }

    public void setMonitorAddress(String monitorAddress) {
        this.monitorAddress = monitorAddress;
    }

    @Override
    public String toString() {
        return "JavaMonitorWait{" +
                "startTime='" + startTime + '\'' +
                ", duration='" + duration + '\'' +
                ", endTime='" + endTime + '\'' +
                ", eventThread='" + eventThread + '\'' +
                ", monitorClass='" + monitorClass + '\'' +
                ", notifierThread='" + notifierThread + '\'' +
                ", timeOut='" + timeOut + '\'' +
                ", timedOut='" + timedOut + '\'' +
                ", monitorAddress='" + monitorAddress + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}