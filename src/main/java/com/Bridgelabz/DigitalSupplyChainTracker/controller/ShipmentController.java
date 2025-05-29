package com.Bridgelabz.DigitalSupplyChainTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.shipmentDto.ShipmentRequest;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.shipmentDto.ShipmentResponse;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Shipment;
import com.Bridgelabz.DigitalSupplyChainTracker.service.Shipment.ShipmentService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ResponseEntity<ShipmentResponse> createShipment(@RequestBody @Valid ShipmentRequest request) {
        Shipment shipment = shipmentService.createShipment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(shipmentService.getShipmentById(shipment.getShipmentId()));
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<String> assignTransporter(@PathVariable int id, @RequestParam int transporterId) {
        shipmentService.assignTransporter(id, transporterId);
        return ResponseEntity.ok("Transporter assigned successfully");
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable int id, @RequestParam Shipment.CurrentStatus status) {
        shipmentService.updateShipmentStatus(id, status);
        return ResponseEntity.ok("Status updated successfully");
    }

    @GetMapping
    public ResponseEntity<List<ShipmentResponse>> getAllShipments() {
        return ResponseEntity.ok(shipmentService.getAllShipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponse> getShipment(@PathVariable int id) {
        return ResponseEntity.ok(shipmentService.getShipmentById(id));
    }
}
