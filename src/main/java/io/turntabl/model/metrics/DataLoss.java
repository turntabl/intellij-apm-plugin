<<<<<<< HEAD:src/main/java/io/turntabl/model/metrics/DataLoss.java
package io.turntabl.model.metrics;
=======
package io.turntabl.model;
>>>>>>> main:src/main/java/io/turntabl/model/DataLoss.java

import java.util.HashMap;

public class DataLoss {

    private long startTime;
    private double amount;
    private double total;
    private HashMap<String, String> attributes;
      
    public DataLoss(long startTime, double amount, double total, HashMap<String, String> attributes) {
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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}