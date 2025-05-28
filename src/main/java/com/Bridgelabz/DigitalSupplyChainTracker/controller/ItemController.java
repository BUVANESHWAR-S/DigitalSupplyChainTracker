package com.Bridgelabz.DigitalSupplyChainTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.item.ItemCreateRequestDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Item;
import com.Bridgelabz.DigitalSupplyChainTracker.service.Item.ItemServiceImplementation;

@RestController
public class ItemController {
	
	@Autowired
	ItemServiceImplementation itemService;
	
	@PostMapping("/api/items")
	public ResponseEntity<?> postItem(@RequestBody ItemCreateRequestDTO item) {
		return itemService.addItem(item);
	}
	
	@GetMapping("/api/items/{userId}")
	public ResponseEntity<?> getItems(@PathVariable int userId) {
		return itemService.getItems(userId);
	}
	
	@GetMapping("/api/item/{id}")
	public ResponseEntity<?> getItem(@PathVariable int id) {
		return itemService.getItem(id);
		
	}
}
