package com.Bridgelabz.DigitalSupplyChainTracker.dto;

import java.time.LocalDate;

public class ShipmentRequest {
    private Long itemId;
    private String fromLocation;
    private String toLocation;
    private LocalDate expectedDelivery;
    
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

    
}
