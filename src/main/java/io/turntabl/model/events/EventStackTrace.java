package io.turntabl.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventStackTrace {
    @JsonProperty("line")
    private String line;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("bytecodeIndex")
    private String bytecodeIndex;

    public EventStackTrace(){}

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBytecodeIndex() {
        return bytecodeIndex;
    }

    public void setBytecodeIndex(String bytecodeIndex) {
        this.bytecodeIndex = bytecodeIndex;
    }

    @Override
    public String toString() {
        return "EventStackTrace{" +
                ", line='" + line + '\'' +
                "desc='" + desc + '\'' +
                ", bytecodeIndex='" + bytecodeIndex + '\'' +
                '}';
    }
}
