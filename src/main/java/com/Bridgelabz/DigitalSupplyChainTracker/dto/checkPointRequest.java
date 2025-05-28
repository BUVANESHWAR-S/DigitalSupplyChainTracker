package com.Bridgelabz.DigitalSupplyChainTracker.dto;

import com.Bridgelabz.DigitalSupplyChainTracker.entity.CheckPointLog.CheckpointStatus;

public class checkPointRequest {
	private int shipmentId;
	private String location;
	private CheckpointStatus status;
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
	
	
	
}
