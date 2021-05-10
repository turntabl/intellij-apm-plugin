package io.turntabl.model_template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractEventsInfo {
    protected String eventType;
    protected long timestamp;
    @JsonProperty("instrumentation.name")
    protected String instrumentationName;
    @JsonProperty("host.hostname")
    protected String hostName;
    @JsonProperty("collector.name")
    protected String collectorName;
    @JsonProperty("instrumentation.provider")
    protected String instrumentationProvider;

    public AbstractEventsInfo() {
    }

    public AbstractEventsInfo(String eventType, long timestamp, String instrumentationName, String hostName, String collectorName, String instrumentationProvider) {
        this.eventType = eventType;
        this.timestamp = timestamp;
        this.instrumentationName = instrumentationName;
        this.hostName = hostName;
        this.collectorName = collectorName;
        this.instrumentationProvider = instrumentationProvider;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    @Override
    public String toString() {
        return "AbstractEventsInfo{" +
                "eventType='" + eventType + '\'' +
                ", timestamp=" + timestamp +
                ", instrumentationName='" + instrumentationName + '\'' +
                ", hostName='" + hostName + '\'' +
                ", collectorName='" + collectorName + '\'' +
                ", instrumentationProvider='" + instrumentationProvider + '\'' +
                '}';
    }
}
