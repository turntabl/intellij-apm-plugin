package io.turntabl.ui.model;

import java.sql.Time;
import java.util.Date;

public class JfrCompilation {

    private Date timestamp;
    private Integer duration;
    private String instrumentationName;
    private String hostHostname;
    private String threadName;
    private String collectorName;
    private String desc;
    private Boolean succeeded;
    private String instrumentationProvider;

    public JfrCompilation(Date timestamp, Integer duration, String instrumentationName, String hostHostname, String threadName, String collectorName, String desc, Boolean succeeded, String instrumentationProvider) {
        this.timestamp = timestamp;
        this.duration = duration;
        this.instrumentationName = instrumentationName;
        this.hostHostname = hostHostname;
        this.threadName = threadName;
        this.collectorName = collectorName;
        this.desc = desc;
        this.succeeded = succeeded;
        this.instrumentationProvider = instrumentationProvider;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getInstrumentationName() {
        return instrumentationName;
    }

    public String getHostHostname() {
        return hostHostname;
    }

    public String getThreadName() {
        return threadName;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public String getDesc() {
        return desc;
    }

    public Boolean getSucceeded() {
        return succeeded;
    }

    public String getInstrumentationProvider() {
        return instrumentationProvider;
    }
}
