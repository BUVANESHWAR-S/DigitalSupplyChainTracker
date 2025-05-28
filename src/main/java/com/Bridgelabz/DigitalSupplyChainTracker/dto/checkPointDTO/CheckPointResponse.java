package com.Bridgelabz.DigitalSupplyChainTracker.dto.checkPointDTO;

import java.time.LocalDateTime;

import com.Bridgelabz.DigitalSupplyChainTracker.entity.CheckPointLog;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.CheckPointLog.CheckpointStatus;

public class CheckPointResponse {
	private int CheckPointLogid;
	private int shipmentId;
	private String location;
    private CheckpointStatus status;
    private LocalDateTime timestamp;
    
    
	public int getCheckPointLogid() {
		return CheckPointLogid;
	}
	public void setCheckPointLogid(int checkPointLogid) {
		CheckPointLogid = checkPointLogid;
	}
	public int getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public CheckpointStatus getStatus() {
		return status;
	}
	public void setStatus(CheckpointStatus status) {
		this.status = status;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
    
    
    
}
