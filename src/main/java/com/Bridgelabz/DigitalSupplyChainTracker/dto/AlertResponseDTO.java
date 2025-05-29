package com.Bridgelabz.DigitalSupplyChainTracker.dto;

import java.time.LocalDateTime;

import com.Bridgelabz.DigitalSupplyChainTracker.entity.Alert.AlertType;

import lombok.Data;

@Data
public class AlertResponseDTO {
	private int alertid;
    private int shipmentId;
    private AlertType type;
    private String message;
    private LocalDateTime createdOn;
    private boolean resolved;
    	
		
		public int getId() {
	        return alertid;
	    }

	    public void setId(int alertid) {
	        this.alertid = alertid;
	    }

	    public int getShipmentId() {
	        return shipmentId;
	    }

	    public void setShipmentId(int shipmentId) {
	        this.shipmentId = shipmentId;
	    }

	    public AlertType getType() {
	        return type;
	    }

	    public void setType(AlertType type) {
	        this.type = type;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public LocalDateTime getCreatedOn() {
	        return createdOn;
	    }

	    public void setCreatedOn(LocalDateTime createdOn) {
	        this.createdOn = createdOn;
	    }

	    public boolean isResolved() {
	        return resolved;
	    }

	    public void setResolved(boolean resolved) {
	        this.resolved = resolved;
	    }

}
