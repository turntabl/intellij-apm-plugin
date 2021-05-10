package io.turntabl.model.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.turntabl.model_template.AbstractEventsInfo;

public class JfrMethodSample extends AbstractEventsInfo {
    @JsonProperty("thread.state")
    private String threadState;
    private String stackTrace;
    @JsonProperty("thread.name")
    private String threadName;

    public JfrMethodSample() {
    }

    public JfrMethodSample(String eventType, long timestamp, String threadState, String stackTrace, String instrumentationName, String hostName, String threadName, String collectorName, String instrumentationProvider) {
        super(eventType, timestamp, instrumentationName, hostName, collectorName, instrumentationProvider);
        this.threadState = threadState;
        this.stackTrace = stackTrace;
        this.threadName = threadName;
    }

    public String getThreadState() {
        return threadState;
    }

    public void setThreadState(String threadState) {
        this.threadState = threadState;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public String toString() {
        return "JfrMethodSample{" +
                "eventType='" + eventType + '\'' +
                ", timestamp=" + timestamp +
                ", threadState='" + threadState + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                ", instrumentationName='" + instrumentationName + '\'' +
                ", hostName='" + hostName + '\'' +
                ", threadName='" + threadName + '\'' +
                ", collectorName='" + collectorName + '\'' +
                ", instrumentationProvider='" + instrumentationProvider + '\'' +
                '}';
    }
}
