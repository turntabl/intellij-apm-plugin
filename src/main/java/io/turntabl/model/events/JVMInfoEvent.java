package io.turntabl.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.turntabl.model_template.AbstractEventsInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JVMInfoEvent extends AbstractEventsInfo {
    private String jvmProperty;
    private String jvmPropertyValue;
    private String jvmArguments;

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

    public String getJvmArguments() {
        return jvmArguments;
    }

    public void setJvmArguments(String jvmArguments) {
        this.jvmArguments = jvmArguments;
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