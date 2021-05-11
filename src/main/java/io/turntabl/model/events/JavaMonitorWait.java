package io.turntabl.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.turntabl.model_template.AbstractEventsInfo;


@JsonIgnoreProperties(ignoreUnknown = true)
public class JavaMonitorWait extends AbstractEventsInfo {
    private String duration;
    private String stackTrace;
    private String threadName;
    private String className;



    public JavaMonitorWait() {
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

    public JavaMonitorWait(String duration, String stackTrace, String threadName, String className) {
        this.duration = duration;
        this.stackTrace = stackTrace;
        this.threadName = threadName;
        this.className = className;

    }

    @Override
    public String toString() {
        return "JavaMonitorWait{" +
                ", duration='" + duration + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                ", threadName='" + threadName + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}