package com.Bridgelabz.DigitalSupplyChainTracker.service.Shipment;

import java.util.List;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.ShipmentRequest;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.ShipmentResponse;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Shipment;

public interface ShipmentService {
	Shipment createShipment(ShipmentRequest dto);
    void assignTransporter(Long shipmentId, Long transporterId);
    void updateShipmentStatus(Long id, Shipment.CurrentStatus status);
    ShipmentResponse getShipmentById(Long id);
    List<ShipmentResponse> getAllShipments();
}
