package com.Bridgelabz.DigitalSupplyChainTracker.service.Alert;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.AlertRequestDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.AlertResponseDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Alert;

@Service
public interface AlertService {
	List<AlertResponseDTO> getAllAlerts();
    AlertResponseDTO createAlert(AlertRequestDTO request, Alert.AlertType type);
    AlertResponseDTO resolveAlert(int alertid);

}
