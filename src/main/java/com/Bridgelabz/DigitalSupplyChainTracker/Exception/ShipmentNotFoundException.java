package com.Bridgelabz.DigitalSupplyChainTracker.Exception;

public class ShipmentNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public ShipmentNotFoundException(String message) {
		super(message);
	}

}
