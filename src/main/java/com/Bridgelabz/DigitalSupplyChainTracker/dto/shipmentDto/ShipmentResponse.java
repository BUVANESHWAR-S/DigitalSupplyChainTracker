package com.Bridgelabz.DigitalSupplyChainTracker.dto.shipmentDto;

import java.time.LocalDate;

public class ShipmentResponse {
    private int shipmentId;
    private String itemName;
    private String fromLocation;
    private String toLocation;
    private LocalDate expectedDelivery;
    private String currentStatus;
    private String assignedTransporterName;
	public int getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public LocalDate getExpectedDelivery() {
		return expectedDelivery;
	}
	public void setExpectedDelivery(LocalDate expectedDelivery) {
		this.expectedDelivery = expectedDelivery;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getAssignedTransporterName() {
		return assignedTransporterName;
	}
	public void setAssignedTransporterName(String assignedTransporterName) {
		this.assignedTransporterName = assignedTransporterName;
	}

    
}
