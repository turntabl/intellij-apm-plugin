package io.turntabl.ui.model;

public class DataLoss {
    private long startTime;
    private double amount;
    private double total;

    public DataLoss(long startTime, double amount, double total) {
        this.startTime = startTime;
        this.amount = amount;
        this.total = total;
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
