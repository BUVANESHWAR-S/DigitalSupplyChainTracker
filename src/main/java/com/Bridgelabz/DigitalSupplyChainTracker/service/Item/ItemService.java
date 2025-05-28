package com.Bridgelabz.DigitalSupplyChainTracker.service.Item;

import org.springframework.http.ResponseEntity;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.item.ItemCreateRequestDTO;

public interface ItemService {
	ResponseEntity<?> addItem(ItemCreateRequestDTO item);
	ResponseEntity<?> getItems(int userId);
	ResponseEntity<?> getItem(int id);
}
