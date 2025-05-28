package com.Bridgelabz.DigitalSupplyChainTracker.service.Item;

import org.springframework.http.ResponseEntity;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.item.ItemCreateRequest;

public interface ItemServiceInterface {
	ResponseEntity<?> addItem(ItemCreateRequest item);
	ResponseEntity<?> getItems();
	ResponseEntity<?> getItem(int id);
}
