package com.Bridgelabz.DigitalSupplyChainTracker.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.checkPointRequest;

@Service
public interface CheckPointLogServiceInterface {
	
	public ResponseEntity<?> addCheckpoint(checkPointRequest request);
	public ResponseEntity<?> getByShipmentId(Integer id);
	
	

}
