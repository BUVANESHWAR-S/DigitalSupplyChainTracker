package com.Bridgelabz.DigitalSupplyChainTracker.service.checkpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.checkPointDTO.checkPointRequest;

@Service
public interface CheckPointLogServiceInterface {
	
	public ResponseEntity<?> addCheckpoint(checkPointRequest request);
	public ResponseEntity<?> getByShipmentId(Integer shipmentId);
	
	

}
