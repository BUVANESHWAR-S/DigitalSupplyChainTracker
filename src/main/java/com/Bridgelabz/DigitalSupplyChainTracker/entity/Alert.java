package com.Bridgelabz.DigitalSupplyChainTracker.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int alertid;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipmentId", nullable = false)
	private Shipment shipment;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AlertType type;
	
	@Column(nullable = false)
	private String message;
	
	@Column(name = "created_on", nullable = false)
	private LocalDateTime createdOn;
	
	@Column(nullable = false)
	private boolean resolved;
	
	public int getAlertid() {
		return alertid;
	}

	public void setAlertid(int alertid) {
		this.alertid = alertid;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
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

	public enum AlertType {
		Delay,
	    Damage
	}

	


}


