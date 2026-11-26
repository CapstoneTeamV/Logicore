package com.logicore.model;

// κλάση για την κατάσταση παραλαβής
public class ReceiptStatus {
    private int statusID;
    private int code;
    private String labelGR;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLabelGR() {
        return labelGR;
    }

    public void setLabelGR(String labelGR) {
        this.labelGR = labelGR;
    }

    public enum Status {
        ANNOUNCED,
        RECEIVED,
        CANCELLED,
    }

    public int getStatusID() {
        return statusID;
    }

    public String getLabel() {
        return labelGR;
    }

    // αλλαγή κατάστασης

    public void ChangeStatus() {

    }

}
