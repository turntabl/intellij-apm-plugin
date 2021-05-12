package io.turntabl.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.turntabl.model_template.AbstractEventsInfo;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JavaMonitorWait extends AbstractEventsInfo {
    private int duration;
    private String stackTrace;
    @JsonProperty("thread.name")
    private String threadName;
    @JsonProperty("class")
    private String className;

    public JavaMonitorWait() {
        super();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "JavaMonitorWait{" +
                "duration=" + duration +
                ", stackTrace='" + stackTrace + '\'' +
                ", threadName='" + threadName + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}