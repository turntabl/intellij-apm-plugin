package io.turntabl.ui.model;

import java.util.HashMap;

public class DataLoss {
    private String startTime;
    private String amount;
    private String total;
    private HashMap<String, String> attributes;

    public DataLoss(String startTime, String amount, String total, HashMap<String, String> attributes) {
        this.startTime = startTime;
        this.amount = amount;
        this.total = total;
        this.attributes = attributes;
    }

    public DataLoss() {
    }

    @Override
    public String toString() {
        return "DataLoss{" +
                "startTime='" + startTime + '\'' +
                ", amount='" + amount + '\'' +
                ", total='" + total + '\'' +
                ", attributes=" + attributes +
                '}';
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}