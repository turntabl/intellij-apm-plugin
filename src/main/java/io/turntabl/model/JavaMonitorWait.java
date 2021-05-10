package io.turntabl.model;


import java.util.HashMap;

public class JavaMonitorWait {
    private String eventType;
    private Long timeStamp;
    private String duration;
    private String stackTrace;
    private String instrumentationName;
    private String hostName;
    private String threadName;
    private String className;
    private String collectorName;
    private String instrumentationProvider;


    public JavaMonitorWait() {
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getInstrumentationName() {
        return instrumentationName;
    }

    public void setInstrumentationName(String instrumentationName) {
        this.instrumentationName = instrumentationName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }

    public String getInstrumentationProvider() {
        return instrumentationProvider;
    }

    public void setInstrumentationProvider(String instrumentationProvider) {
        this.instrumentationProvider = instrumentationProvider;
    }

    public JavaMonitorWait(String eventType, Long timeStamp, String duration, String stackTrace, String instrumentationName, String hostName, String threadName, String className, String collectorName, String instrumentationProvider) {
        this.eventType = eventType;
        this.timeStamp = timeStamp;
        this.duration = duration;
        this.stackTrace = stackTrace;
        this.instrumentationName = instrumentationName;
        this.hostName = hostName;
        this.threadName = threadName;
        this.className = className;
        this.collectorName = collectorName;
        this.instrumentationProvider = instrumentationProvider;



    }

    @Override
    public String toString() {
        return "JavaMonitorWait{" +
                "eventType='" + eventType + '\'' +
                ", timeStamp=" + timeStamp +
                ", duration='" + duration + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                ", instrumentationName='" + instrumentationName + '\'' +
                ", hostName='" + hostName + '\'' +
                ", threadName='" + threadName + '\'' +
                ", className='" + className + '\'' +
                ", collectorName='" + collectorName + '\'' +
                ", instrumentationProvider='" + instrumentationProvider + '\'' +
                '}';
    }
}