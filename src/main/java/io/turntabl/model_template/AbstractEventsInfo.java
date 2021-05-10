package io.turntabl.model_template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractEventsInfo {
    private String eventType;
    private long timestamp;
    @JsonProperty("instrumentation.name")
    private String instrumentationName;
    @JsonProperty("host.hostname")
    private String hostName;
    @JsonProperty("collector.name")
    private String collectorName;
    @JsonProperty("instrumentation.provider")
    private String instrumentationProvider;

    public AbstractEventsInfo() {
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
