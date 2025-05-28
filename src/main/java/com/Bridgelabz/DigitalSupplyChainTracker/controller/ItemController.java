package com.Bridgelabz.DigitalSupplyChainTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.item.ItemCreateRequest;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Item;
import com.Bridgelabz.DigitalSupplyChainTracker.service.Item.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@PostMapping("/item")
	public ResponseEntity<?> postItem(@RequestBody ItemCreateRequest item) {
		System.out.println(item.getSupplierId());
		return ResponseEntity.ok(item);
	}
	
	@GetMapping("/api/items")
	public ResponseEntity<?> getItems() {
		return itemService.getItems();
	}
	
	@GetMapping("/item/{id}")
	public ResponseEntity<?> getItem(@PathVariable int id) {
		return itemService.getItem(id);
		
	}
}
