package com.Bridgelabz.DigitalSupplyChainTracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;  // For validation if needed
import java.time.LocalDate;

public class ShipmentRequest {

    @NotNull(message = "itemId cannot be null")
    private Integer itemId;  // Use Integer so null can be handled gracefully

    @NotNull(message = "fromLocation cannot be null")
    private String fromLocation;

    @NotNull(message = "toLocation cannot be null")
    private String toLocation;

    @NotNull(message = "expectedDelivery cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate expectedDelivery;

    // Getter and Setter for itemId
    public Integer getItemId() {
        return itemId;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    // Getter and Setter for fromLocation
    public String getFromLocation() {
        return fromLocation;
    }
    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    // Getter and Setter for toLocation
    public String getToLocation() {
        return toLocation;
    }
    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    // Getter and Setter for expectedDelivery
    public LocalDate getExpectedDelivery() {
        return expectedDelivery;
    }
    public void setExpectedDelivery(LocalDate expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }
}
