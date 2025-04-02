package com.tus.logistics.service;

import com.tus.logistics.repository.ShipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShipmentServiceTest {


    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private ShipmentService shipmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testDispatchShipment_Success() {
        Shipment shipment = new Shipment(1, "TRACK123", "user@example.com", "123 Main Street");
        boolean result = shipmentService.dispatchShipment(shipment);
        assertTrue(result);
        assertTrue(shipment.isDispatched());
        verify(shipmentRepository, times(1)).save(shipment);
        verify(notificationService, times(1)).sendNotification("user@example.com", "Your shipment has been dispatched.");
    }



    @Test
    void testDispatchShipment_Failed_InvalidAddress() {
        Shipment shipment = new Shipment(2, "TRACK456", "user@example.com", "   "); // Invalid address
        boolean result = shipmentService.dispatchShipment(shipment);
        assertFalse(result);
        verify(shipmentRepository, never()).save(any());
        verify(notificationService, never()).sendNotification(any(), any());
    }

    @Test
    void testDispatchShipment_NullShipment() {
        assertThrows(IllegalArgumentException.class, () -> shipmentService.dispatchShipment(null));
    }


    @Test
    void testDispatchShipment_EmptyTrackingNumber() {
        Shipment shipment = new Shipment(3, "", "user@example.com", "456 Elm Street");
        assertThrows(IllegalArgumentException.class, () -> shipmentService.dispatchShipment(shipment));
    }

    @Test
    void testDispatchShipment_AlreadyDispatched() {
        Shipment shipment = new Shipment(4, "TRACK789", "user@example.com", "789 Oak Avenue");
        shipment.setDispatched(true);
        boolean result = shipmentService.dispatchShipment(shipment);
        assertFalse(result);
        verify(shipmentRepository, never()).save(any());
        verify(notificationService, never()).sendNotification(any(), any());
    }


    @Test
    void testDispatchShipment_VerifyRepositorySave() {
        Shipment shipment = new Shipment(5, "TRACK321", "user@example.com", "321 Pine Road");
        shipmentService.dispatchShipment(shipment);
        verify(shipmentRepository, times(1)).save(shipment);
    }


    @Test
    void testDispatchShipment_VerifyNotificationSent() {
        Shipment shipment = new Shipment(6, "TRACK654", "user@example.com", "654 Maple Blvd");
        shipmentService.dispatchShipment(shipment);
        verify(notificationService, times(1)).sendNotification("user@example.com", "Your shipment has been dispatched.");
    }

    @Test
    void testDispatchShipment_LongTrackingNumber() {
        String longTrackingNumber = "TRACK" + "1234567890".repeat(10); // very long string
        Shipment shipment = new Shipment(7, longTrackingNumber, "user@example.com", "987 Birch Lane");
        boolean result = shipmentService.dispatchShipment(shipment);
        assertTrue(result);
        verify(shipmentRepository, times(1)).save(shipment);
        verify(notificationService, times(1)).sendNotification("user@example.com", "Your shipment has been dispatched.");
    }

}
