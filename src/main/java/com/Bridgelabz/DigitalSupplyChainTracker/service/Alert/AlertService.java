package com.Bridgelabz.DigitalSupplyChainTracker.service.Alert;

import java.util.List;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.AlertRequestDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.AlertResponseDTO;

public interface AlertService {
	List<AlertResponseDTO> getAllAlerts();
    AlertResponseDTO createAlert(AlertRequestDTO request);
    AlertResponseDTO resolveAlert(int alertid);

}
