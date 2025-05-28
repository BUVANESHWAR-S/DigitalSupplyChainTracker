package com.Bridgelabz.DigitalSupplyChainTracker.service.Shipment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.ShipmentRequest;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.ShipmentResponse;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Shipment;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.ShipmentRepository;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired private ShipmentRepository shipmentRepo;
    @Autowired private ItemRepository itemRepo;
    @Autowired private UserRepository userRepo;

    @Override
    public Shipment createShipment(ShipmentRequest dto) {
        Item item = itemRepo.findById(dto.getItemId()).orElseThrow(() -> new RuntimeException("Item not found"));

        Shipment shipment = new Shipment();
        shipment.setItem(item);
        shipment.setFromLocation(dto.getFromLocation());
        shipment.setToLocation(dto.getToLocation());
        shipment.setExpectedDelivery(dto.getExpectedDelivery());
        shipment.setCurrentStatus(Shipment.CurrentStatus.CREATED);

        return shipmentRepo.save(shipment);
    }

    @Override
    public void assignTransporter(Long shipmentId, Long transporterId) {
        Shipment shipment = shipmentRepo.findById(shipmentId).orElseThrow(() -> new RuntimeException("Shipment not found"));
        User transporter = userRepo.findById(transporterId).orElseThrow(() -> new RuntimeException("Transporter not found"));

        shipment.setAssignedTransporter(transporter);
        shipment.setCurrentStatus(Shipment.CurrentStatus.IN_TRANSIT);
        shipmentRepo.save(shipment);
    }

    @Override
    public void updateShipmentStatus(Long id, Shipment.CurrentStatus status) {
        Shipment shipment = shipmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Shipment not found"));
        shipment.setCurrentStatus(status);
        shipmentRepo.save(shipment);
    }

    @Override
    public ShipmentResponse getShipmentById(Long id) {
        Shipment shipment = shipmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Shipment not found"));
        return mapToResponseDto(shipment);
    }

    @Override
    public List<ShipmentResponse> getAllShipments() {
        return shipmentRepo.findAll().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    private ShipmentResponse mapToResponseDto(Shipment s) {
        ShipmentResponse dto = new ShipmentResponse();
        dto.setShipmentId(s.getShipmentId());
        dto.setItemName(s.getItem().getName());
        dto.setFromLocation(s.getFromLocation());
        dto.setToLocation(s.getToLocation());
        dto.setExpectedDelivery(s.getExpectedDelivery());
        dto.setCurrentStatus(s.getCurrentStatus().name());
        dto.setAssignedTransporterName(
            s.getAssignedTransporter() != null ? s.getAssignedTransporter().getName() : "Not Assigned"
        );
        return dto;
    }
}
