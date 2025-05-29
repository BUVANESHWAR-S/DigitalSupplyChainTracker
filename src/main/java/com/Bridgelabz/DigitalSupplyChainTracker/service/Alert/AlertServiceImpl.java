package com.Bridgelabz.DigitalSupplyChainTracker.service.Alert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.AlertRequestDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.AlertResponseDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Alert;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Item;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Shipment;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.AlertRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.ItemRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.ShipmentRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.service.Email.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {
	@Autowired
	AlertRepository alertrepository;
	@Autowired
	ShipmentRepository shipmentrepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	 @Autowired
	 EmailService emailService;
	
	@Override
    public List<AlertResponseDTO> getAllAlerts() {
        return alertrepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlertResponseDTO createAlert(AlertRequestDTO request, Alert.AlertType type) {
        Shipment shipment = shipmentrepository.findById(request.getShipmentId()).orElseThrow(() -> new RuntimeException("Shipment not found"));
 
        Alert alert = new Alert();
        alert.setShipment(shipment);
        alert.setType(type);
        alert.setMessage(request.getMessage());
        alert.setCreatedOn(LocalDateTime.now());
        alert.setResolved(false);
        Alert response = alertrepository.save(alert);
        
        User supplier =shipment.getItem().getSupplier();
        
        emailService.send(
                supplier.getEmail(),
                "New Alert: " + alert.getType(),
                "Alert message: " + alert.getMessage()
        );
        
        
        return toDTO(response);
    }

    @Override
    public AlertResponseDTO resolveAlert(int alertid) {
        Alert alert = alertrepository.findById(alertid).orElseThrow(() -> new RuntimeException("Alert not found"));
        alert.setResolved(true);
        Alert response = alertrepository.save(alert);
        User user = alert.getShipment().getItem().getSupplier();
        emailService.send(
                user.getEmail(),
                "New Alert: Issue Resolved" ,
                "Alert message: Shipment No: " + alert.getShipment().getShipmentId() + " will arrive soon!"
        );
        return toDTO(response);
    }

    private AlertResponseDTO toDTO(Alert alert) {
        AlertResponseDTO dto = new AlertResponseDTO();
        dto.setId(alert.getAlertid());
        dto.setShipmentId(alert.getShipment().getShipmentId());
        dto.setType(alert.getType());
        dto.setMessage(alert.getMessage());
        dto.setCreatedOn(alert.getCreatedOn());
        dto.setResolved(alert.isResolved());
        return dto;
    }
	

}

