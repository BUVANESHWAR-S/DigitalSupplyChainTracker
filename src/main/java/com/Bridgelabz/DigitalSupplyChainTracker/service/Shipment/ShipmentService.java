package com.Bridgelabz.DigitalSupplyChainTracker.service.Shipment;

import java.util.List;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.ShipmentRequest;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.ShipmentResponse;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Shipment;

public interface ShipmentService {
	Shipment createShipment(ShipmentRequest dto);
    void assignTransporter(int shipmentId, int transporterId);
    void updateShipmentStatus(int id, Shipment.CurrentStatus status);
    ShipmentResponse getShipmentById(int i);
    List<ShipmentResponse> getAllShipments();
}
