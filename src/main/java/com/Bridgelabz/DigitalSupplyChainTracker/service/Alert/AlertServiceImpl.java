package com.Bridgelabz.DigitalSupplyChainTracker.service.Alert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.AlertRequestDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.AlertResponseDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Alert;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.AlertRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {
	@Autowired
	AlertRepository alertrepository;
	@Autowired
	ShipmentRepository shipmentrepository;
	
	@Override
    public List<AlertResponseDTO> getAllAlerts() {
        return alertrepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlertResponseDTO createAlert(AlertRequestDTO request) {
        Shipment shipment = shipmentrepository.findById(request.getShipmentId()).orElseThrow(() -> new RuntimeException("Shipment not found"));
 
        Alert alert = new Alert();
        alert.setShipmentId(shipment);
        alert.setType(request.getType());
        alert.setMessage(request.getMessage());
        alert.setCreatedOn(LocalDateTime.now());
        alert.setResolved(false);

        return toDTO(alertrepository.save(alert));
    }

    @Override
    public AlertResponseDTO resolveAlert(int alertid) {
        Alert alert = alertrepository.findById(alertid).orElseThrow(() -> new RuntimeException("Alert not found"));
        alert.setResolved(true);
        return toDTO(alertrepository.save(alert));
    }

    private AlertResponseDTO toDTO(Alert alert) {
        AlertResponseDTO dto = new AlertResponseDTO();
        dto.setId(alert.getAlertid());
        dto.setShipmentId(alert.getShipmentId().getId());
        dto.setType(alert.getType());
        dto.setMessage(alert.getMessage());
        dto.setCreatedOn(alert.getCreatedOn());
        dto.setResolved(alert.isResolved());
        return dto;
    }
	

}

