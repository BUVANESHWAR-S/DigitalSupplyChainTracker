package com.Bridgelabz.DigitalSupplyChainTracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.Bridgelabz.DigitalSupplyChainTracker.service.Shipment.ShipmentService;

public class ShipmentController {
	private ShipmentService shipmentService;

    @PostMapping
    public ResponseEntity<ShipmentResponse> createShipment(@RequestBody ShipmentRequest request) {
        Shipment shipment = shipmentService.createShipment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(shipmentService.getShipmentById(shipment.getShipmentId()));
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<String> assignTransporter(@PathVariable Long id, @RequestParam Long transporterId) {
        shipmentService.assignTransporter(id, transporterId);
        return ResponseEntity.ok("Transporter assigned successfully");
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam Shipment.CurrentStatus status) {
        shipmentService.updateShipmentStatus(id, status);
        return ResponseEntity.ok("Status updated successfully");
    }

    @GetMapping
    public ResponseEntity<List<ShipmentResponseDTO>> getAllShipments() {
        return ResponseEntity.ok(shipmentService.getAllShipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponseDTO> getShipment(@PathVariable Long id) {
        return ResponseEntity.ok(shipmentService.getShipmentById(id));
    }
}
