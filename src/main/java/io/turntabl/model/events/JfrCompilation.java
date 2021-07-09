package io.turntabl.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.turntabl.model_template.AbstractEventsInfo;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JfrCompilation extends AbstractEventsInfo {
    private Integer duration;
    @JsonProperty("thread.name")
    private String threadName;
    private String desc;
    private Boolean succeeded;

    public JfrCompilation() {
        super();
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(Boolean succeeded) {
        this.succeeded = succeeded;
    }

    @Override
    public String toString() {
        return "JfrCompilation{" +
                "duration=" + duration +
                ", threadName='" + threadName + '\'' +
                ", desc='" + desc + '\'' +
                ", succeeded=" + succeeded +
                '}';
    }
}
