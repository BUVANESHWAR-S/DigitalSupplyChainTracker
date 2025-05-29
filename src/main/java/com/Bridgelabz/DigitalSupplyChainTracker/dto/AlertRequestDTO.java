package com.Bridgelabz.DigitalSupplyChainTracker.dto;
import lombok.Data;

@Data
public class AlertRequestDTO {
	private int shipmentId;
    private String message; 
    
    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

