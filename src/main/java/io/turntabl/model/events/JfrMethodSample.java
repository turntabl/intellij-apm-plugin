package io.turntabl.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.turntabl.model_template.AbstractEventsInfo;


@JsonIgnoreProperties(ignoreUnknown = true)
public class JfrMethodSample extends AbstractEventsInfo {
    @JsonProperty("thread.state")
    private String threadState;
    private String stackTrace;
    @JsonProperty("thread.name")
    private String threadName;

    public JfrMethodSample() {
        super();
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
                "eventType='" + super.getEventType() + '\'' +
                ", timestamp=" + super.getTimestamp() +
                ", threadState='" + threadState + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                ", instrumentationName='" + super.getInstrumentationName() + '\'' +
                ", hostName='" + super.getHostName() + '\'' +
                ", threadName='" + threadName + '\'' +
                ", collectorName='" + super.getCollectorName() + '\'' +
                ", instrumentationProvider='" + super.getInstrumentationProvider() + '\'' +
                '}';
    }
}
