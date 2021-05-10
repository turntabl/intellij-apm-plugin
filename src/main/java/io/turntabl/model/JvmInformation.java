package io.turntabl.model;

import java.util.Date;

public class JvmInformation {
    private Date timeStamp;
    private String jvmProperty;
    private String jvmPropertyValue;
    private String instrumentationName;
    private String hostName;
    private String collectorName;
    private String instrumentationProvider;


    public JvmInformation(Date timeStamp, String jvmProperty, String jvmPropertyValue, String instrumentationName, String hostName, String collectorName, String instrumentationProvider) {
        this.timeStamp = timeStamp;
        this.jvmProperty = jvmProperty;
        this.jvmPropertyValue = jvmPropertyValue;
        this.instrumentationName = instrumentationName;
        this.hostName = hostName;
        this.collectorName = collectorName;
        this.instrumentationProvider = instrumentationProvider;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getJvmProperty() {
        return jvmProperty;
    }

    public String getJvmPropertyValue() {
        return jvmPropertyValue;
    }

    public String getInstrumentationName() {
        return instrumentationName;
    }

    public String getHostName() {
        return hostName;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public String getInstrumentationProvider() {
        return instrumentationProvider;
    }
}
