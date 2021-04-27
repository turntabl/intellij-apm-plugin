package io.turntabl.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CPULoad {
    private String name;
    private String type;
    private double jvmSytemValue;
    private double jvmUserValue;
    private double machineTotalValue;
    @JsonProperty("timestamp")
    private BigInteger startTime;
    private Map<String, String> attributes;


    public double getJvmUserValue() {
        return jvmUserValue;
    }

    public void setJvmUserValue(double jvmUserValue) {
        this.jvmUserValue = jvmUserValue;
    }

    public double getMachineTotalValue() {
        return machineTotalValue;
    }

    public void setMachineTotalValue(double machineTotalValue) {
        this.machineTotalValue = machineTotalValue;
    }

    public CPULoad() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getJvmSytemValue() {
        return jvmSytemValue;
    }

    public void setJvmSytemValue(double jvmSytemValue) {
        this.jvmSytemValue = jvmSytemValue;
    }

    public BigInteger getStartTime() {
        return startTime;
    }

    public void setStartTime(BigInteger startTime) {
        this.startTime = startTime;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public CPULoad(String name, String type, double jvmSytemValue, double jvmUserValue, double machineTotalValue, BigInteger startTime, Map<String, String> attributes) {
        this.name = name;
        this.type = type;
        this.jvmSytemValue = jvmSytemValue;
        this.jvmUserValue = jvmUserValue;
        this.machineTotalValue = machineTotalValue;
        this.startTime = startTime;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "CPULoad{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", jvmSytemValue=" + jvmSytemValue +
                ", jvmUserValue=" + jvmUserValue +
                ", machineTotalValue=" + machineTotalValue +
                ", startTime=" + startTime +
                ", attributes=" + attributes +
                '}';
    }
}
