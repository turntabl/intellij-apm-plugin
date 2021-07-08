package io.turntabl.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.turntabl.model_template.AbstractEventsInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JVMInfoEvent extends AbstractEventsInfo {
    private String jvmProperty;
    private String jvmPropertyValue;
    private String jvmVersion;
    private String jvmArguments;
    private String jvmStartTime;


    public JVMInfoEvent() {
        super();
    }

    public String getJvmProperty() {
        return jvmProperty;
    }

    public void setJvmProperty(String jvmProperty) {
        this.jvmProperty = jvmProperty;
    }

    public String getJvmPropertyValue() {
        return jvmPropertyValue;
    }

    public void setJvmPropertyValue(String jvmPropertyValue) {
        this.jvmPropertyValue = jvmPropertyValue;
    }

    public String getJvmVersion() {
        return jvmVersion;
    }

    public void setJvmVersion(String jvmVersion) {
        this.jvmVersion = jvmVersion;
    }

    public String getJvmArguments() {
        return jvmArguments;
    }

    public void setJvmArguments(String jvmArguments) {
        this.jvmArguments = jvmArguments;
    }

    public String getJvmStartTime() {
        return jvmStartTime;
    }

    public void setJvmStartTime(String jvmStartTime) {
        this.jvmStartTime = jvmStartTime;
    }

    @Override
    public String toString() {
        return "JVMInfoEvent{" +
                "jvmProperty='" + jvmProperty + '\'' +
                ", jvmPropertyValue='" + jvmPropertyValue + '\'' +
                ", jvmArguments='" + jvmArguments + '\'' +
                '}';
    }
}
