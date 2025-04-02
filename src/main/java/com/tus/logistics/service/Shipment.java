package com.tus.logistics.service;

public class Shipment {
    private final int id;
    private final String trackingNumber;
    private final String customerEmail;
    private final String address;
    private boolean dispatched;
    private boolean delivered;

    public Shipment(int id, String trackingNumber, String customerEmail, String address) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.customerEmail = customerEmail;
        this.address = address;
        this.dispatched = false;
        this.delivered =false;

    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public boolean isDispatched() {
        return dispatched;
    }

    public void setDispatched(boolean dispatched) {
        this.dispatched = dispatched;
    }

    public boolean hasValidAddress() {
        return address != null && !address.trim().isEmpty();
    }
}
