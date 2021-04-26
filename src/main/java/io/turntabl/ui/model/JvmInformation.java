package io.turntabl.ui.model;

public class JvmInformation {
    private Date timeStamp;
    private String jvmProperty;
    private String jvmPropertyValue;
    private String instrumentationName;
    private String hostName;
    private String collectorName;
    private String instrumentionProvider;


    public JvmInformation(Date timeStamp, String jvmProperty, String jvmPropertyValue, String instrumentationName, String hostName, String collectorName, String instrumentionProvider) {
        this.timeStamp = timeStamp;
        this.jvmProperty = jvmProperty;
        this.jvmPropertyValue = jvmPropertyValue;
        this.instrumentationName = instrumentationName;
        this.hostName = hostName;
        this.collectorName = collectorName;
        this.instrumentionProvider = instrumentionProvider
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

//    public void settimeStamp(String time) {
//        this.timeStamp = time;
//    }

    public String getJvmProperty() {

        return jvmProperty;
    }

//    public void setjvmProperty(String jvmproperty) {
//        this.jvmProperty = jvmproperty;
//    }

    public String getJvmPropertyValue() {

        return jvmPropertyValue;
    }

    //public void setTotal(String total) {
        this.total = total;
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

    public String getInstrumentionProvider() {
        return instrumentionProvider;
    }



}
