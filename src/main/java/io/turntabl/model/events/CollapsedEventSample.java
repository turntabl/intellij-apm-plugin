package io.turntabl.model.events;

import java.util.List;

public class CollapsedEventSample {
    private String threadName;
    private List<EventStackTrace> stackTraceList;


    public CollapsedEventSample(String threadName, List<EventStackTrace> stackTraceList) {
        this.threadName = threadName;
        this.stackTraceList = stackTraceList;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public List<EventStackTrace> getStackTraceList() {
        return stackTraceList;
    }

    public void setStackTraceList(List<EventStackTrace> stackTraceList) {
        this.stackTraceList = stackTraceList;
    }
}
