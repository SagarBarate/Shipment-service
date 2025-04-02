package com.tus.logistics.service;

import com.tus.logistics.repository.ShipmentRepository;

public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final NotificationService notificationService;

    public ShipmentService(ShipmentRepository shipmentRepository, NotificationService notificationService) {
        this.shipmentRepository = shipmentRepository;
        this.notificationService = notificationService;
    }

    public boolean dispatchShipment(Shipment shipment) {
        if (shipment == null) {
            throw new IllegalArgumentException("Shipment cannot be null");
        }
        if (shipment.getTrackingNumber() == null || shipment.getTrackingNumber().isEmpty()) {
            throw new IllegalArgumentException("Tracking number is invalid");
        }
        if (!shipment.hasValidAddress()) {
            return false;
        }
        if (shipment.isDispatched()) {
            return false;
        }

        shipment.setDispatched(true);
        shipmentRepository.save(shipment);
        notificationService.sendNotification(shipment.getCustomerEmail(), "Your shipment has been dispatched.");

        return true;
    }
}
