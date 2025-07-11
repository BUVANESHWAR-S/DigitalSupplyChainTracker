package com.Bridgelabz.DigitalSupplyChainTracker.service.Shipment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.Exception.IdNotFoundException;
import com.Bridgelabz.DigitalSupplyChainTracker.Exception.InvalidRoleException;
import com.Bridgelabz.DigitalSupplyChainTracker.Exception.InvalidStatusException;
import com.Bridgelabz.DigitalSupplyChainTracker.Utility.Role;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.shipmentDto.ShipmentRequest;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.shipmentDto.ShipmentResponse;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Item;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Shipment;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.ItemRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.ShipmentRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.UserRepository;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired private ShipmentRepository shipmentRepo;
    @Autowired private ItemRepository itemRepo;
    @Autowired private UserRepository userRepo;

    @Override
    public Shipment createShipment(ShipmentRequest dto) {
        // Logging values received in the DTO
//        System.out.println("Item ID: " + dto.getItemId());
//        System.out.println("From Location: " + dto.getFromLocation());
//        System.out.println("To Location: " + dto.getToLocation());
//        System.out.println("Expected Delivery: " + dto.getExpectedDelivery());

        // Fetch Item entity by ID, or throw exception if not found
        Item item = itemRepo.findById(dto.getItemId())
            .orElseThrow(() -> new IdNotFoundException("Invalid ItemID: " + dto.getItemId()));

        // Create new Shipment and set properties from DTO
        Shipment shipment = new Shipment();
        shipment.setItem(item);
        shipment.setFromLocation(dto.getFromLocation());
        shipment.setToLocation(dto.getToLocation());
        shipment.setExpectedDelivery(dto.getExpectedDelivery());
        shipment.setCurrentStatus(Shipment.CurrentStatus.Created);

        // Save Shipment entity and return
        return shipmentRepo.save(shipment);
    }


    public void assignTransporter(int shipmentId, int transporterId) {
    	 Shipment shipment = shipmentRepo.findById(shipmentId)
                 .orElseThrow(() -> new IdNotFoundException("Invalid ShipmentID"));

         User transporter = userRepo.findById(transporterId)
                 .orElseThrow(() -> new IdNotFoundException("Invalid TransporterID"));

         //  Check if the user is actually a TRANSPORTER
         if (transporter.getRole() != Role.Transporter) {
             throw new InvalidRoleException("User is not a transporter");
         }

         shipment.setAssignedTransporter(transporter);
         shipment.setCurrentStatus(Shipment.CurrentStatus.In_transit);
         shipmentRepo.save(shipment);

    }


    @Override
    public void updateShipmentStatus(int id, Shipment.CurrentStatus status) {

        Shipment shipment = shipmentRepo.findById(id).orElseThrow(() ->  new IdNotFoundException("Invalid ShipmentID"));
        if (shipment.getCurrentStatus() == Shipment.CurrentStatus.Delivered) {
            throw new InvalidStatusException("Cannot change status after delivery");
        }

        shipment.setCurrentStatus(status);
        shipmentRepo.save(shipment);
    }


    @Override
    public ShipmentResponse getShipmentById(int id) {
        Shipment shipment = shipmentRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Invalid ShipmentID"));
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
