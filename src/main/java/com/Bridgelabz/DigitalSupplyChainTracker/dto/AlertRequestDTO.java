package com.Bridgelabz.DigitalSupplyChainTracker.dto;

import com.Bridgelabz.DigitalSupplyChainTracker.entity.Alert.AlertType;

import lombok.Data;

@Data
public class AlertRequestDTO {
	private int shipmentId;
    private AlertType type;
    private String message; 
    
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
}

