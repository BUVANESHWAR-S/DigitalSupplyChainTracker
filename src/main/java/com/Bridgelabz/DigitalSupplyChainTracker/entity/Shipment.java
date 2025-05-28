package com.Bridgelabz.DigitalSupplyChainTracker.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Shipment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE )
	private long shipmentId;
	@ManyToOne
	@JoinColumn(name = "itemid")
	private Item itemid;
	private String fromLocation;
	private String toLocation;
	private LocalDate expectedDelivery;
	@Enumerated(EnumType.STRING)
	private CurrentStatus currentStatus;
	
	@ManyToOne
    @JoinColumn(name = "assigned_transporter_id")
    private User assignedTransporter;

	public enum CurrentStatus {
		CREATED, 
		IN_TRANSIT,
		DELIVERED,
		DELAYED
	}

	public long getShipmentid() {
		return shipmentId;
	}

	public void setShipmentid(long shipmentid) {
		this.shipmentId = shipmentid;
	}

	public Item getItemid() {
		return itemid;
	}

	public void setItemid(Item itemid) {
		this.itemid = itemid;
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

	public CurrentStatus getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(CurrentStatus currentStatus) {
		this.currentStatus = currentStatus;
	}

	public User getAssignedTransporter() {
		return assignedTransporter;
	}

	public void setAssignedTransporter(User assignedTransporter) {
		this.assignedTransporter = assignedTransporter;
	}
	
}
