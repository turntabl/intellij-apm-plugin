package io.turntabl.ui.model;

public class DataLoss {
    private String startTime;
    private String amount;
    private String total;

    public DataLoss(String startTime, String amount, String total) {
        this.startTime = startTime;
        this.amount = amount;
        this.total = total;
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
