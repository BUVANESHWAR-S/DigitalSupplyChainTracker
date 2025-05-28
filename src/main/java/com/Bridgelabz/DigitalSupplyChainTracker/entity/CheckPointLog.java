package com.Bridgelabz.DigitalSupplyChainTracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CheckPointLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checkPointLogId;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    private String location;

    @Enumerated(EnumType.STRING)
    private CheckpointStatus status;

    private LocalDateTime timeStamp;

    public enum CheckpointStatus {
        Received, In_Transit, Damanged, Delivered;
    }

    // Getters and Setters
    public int getCheckPointLogId() {
        return checkPointLogId;
    }

    public void setCheckPointLogId(int checkPointLogId) {
        this.checkPointLogId = checkPointLogId;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
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

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

	public CheckPointLog(int checkPointLogId, Shipment shipment, String location, CheckpointStatus status,
			LocalDateTime timeStamp) {
		this.checkPointLogId = checkPointLogId;
		this.shipment = shipment;
		this.location = location;
		this.status = status;
		this.timeStamp = timeStamp;
	}
    public CheckPointLog() {
    	
    }
    
}
