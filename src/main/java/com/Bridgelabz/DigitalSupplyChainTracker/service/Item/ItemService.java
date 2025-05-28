package com.Bridgelabz.DigitalSupplyChainTracker.service.Item;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.item.ItemCreateRequest;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.item.ItemDetailResponse;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.item.ItemSummaryResponse;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Item;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.ItemRepo;

@Service
public class ItemService implements ItemServiceInterface {
	
	@Autowired
	ItemRepo itemRepository;
	
	@Autowired
	UserRepository userRepository;

	public ResponseEntity<?> addItem(ItemCreateRequest item) {
		// TODO Auto-generated method stub
		
		//checks if user is available
	    User user = userRepository.findById(item.getSupplierId())
	            .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
	    
	    //checks if user is supplier
	    if(!user.getRole().equals("Supplier")) {
	    	throw new IllegalArgumentException("User is not a Supplier");
	    }
	    
	    //creating new item
	    Item newItem = new Item(user.getId(),item.getName(),item.getCategory(), user,LocalDateTime.now());
	    
	    
	    Item savedItem = itemRepository.save(newItem);
	    // Build response DTO
	    ItemDetailResponse response = new ItemDetailResponse(
	            savedItem.getId(),
	            savedItem.getName(),
	            savedItem.getCategory(),
	            savedItem.getCreatedDate(),
	            new ItemDetailResponse.SupplierInfo(
	                    user.getId(),
	                    user.getName(),
	                    user.getEmail()
	            )
	    );

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	    
	}

	public ResponseEntity<?> getItems() {
		// TODO Auto-generated method stub
		List<Item> items = itemRepository.findAll();
		return ResponseEntity.ok(items);

	}
	
	public ResponseEntity<?> getItem(int id){
		
		Optional<Item> item = itemRepository.findById(id);
		ItemSummaryResponse response = new ItemSummaryResponse(item.get().getId(),item.get().getName(),item.get().getCategory());
		return new ResponseEntity(response,HttpStatus.OK);
	}



}
