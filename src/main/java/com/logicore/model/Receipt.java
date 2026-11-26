package com.logicore.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;

// κλάση παραλάβής παραγγελίας

public class Receipt {

    // temporary, must be replaced by database
    private static int id_counter = 0;

    private int receiptID;
    private String receiptCode;

    // customer_id
    private Customer customer;

    // created_by
    private User creator;

    // reveived_by
    private User receiver;

    private ReceiptStatus.Status status;

    private ZonedDateTime announcedAt;
    private ZonedDateTime receivedAt;

    private ArrayList<Pallet> pallets;

    public int getReceiptId() {
        return receiptID;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    // constructor

    public Receipt(User creator, Customer customer) {
        this.receiptID = ++Receipt.id_counter;

        this.creator = creator;
        this.customer = customer;
        
        this.announcedAt = ZonedDateTime.now();
        this.status = ReceiptStatus.Status.ANNOUNCED;
    }

    // need Palette constructor
    public void addPallet(Pallet pallet) {
        pallets.add(pallet);
    }

    // παραλαβή παραγγελίας

    public void ReceiveReceipt() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public User getCreator() {
        return creator;
    }

    public User getReceiver() {
        return receiver;
    }

    public ZonedDateTime getAnnouncedAt() {
        return announcedAt;
    }

    public ZonedDateTime getReceivedAt() {
        return receivedAt;
    }

    public ArrayList<Pallet> getPallets() {
        return pallets;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setStatus(ReceiptStatus.Status status) {
        this.status = status;
    }

    public void setReceivedAt(ZonedDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public void setPallets(ArrayList<Pallet> pallets) {
        this.pallets = pallets;
    }

}
