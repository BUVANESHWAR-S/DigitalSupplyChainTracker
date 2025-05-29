package com.Bridgelabz.DigitalSupplyChainTracker.service.Item;

//import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.Exception.IdNotFoundException;
import com.Bridgelabz.DigitalSupplyChainTracker.Exception.InvalidRoleException;
import com.Bridgelabz.DigitalSupplyChainTracker.Exception.UserNotFoundException;
import com.Bridgelabz.DigitalSupplyChainTracker.Utility.Role;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.item.ItemCreateRequestDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.item.ItemDetailResponseDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.item.ItemSummaryResponseDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Item;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.ItemRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.UserRepository;

@Service
public class ItemServiceImplementation implements ItemService {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	UserRepository userRepository;

	public ResponseEntity<?> addItem(ItemCreateRequestDTO item) {
		
		//checks if user is available
	    User user = userRepository.findById(item.getSupplierId())
	            .orElseThrow(() -> new IdNotFoundException("Invalid ID"));
	    
	    //checks if user is supplier
	    if(!user.getRole().equals(Role.Supplier)) {
//	    	Object error = new IllegalArgumentException("User is not a Supplier");
	    	throw new InvalidRoleException("Invalid Role to access");
	    }
	    
	    //creating new item
	    Item newItem = new Item(item.getName(),item.getCategory(), user,LocalDateTime.now());
	    
	    
	    Item savedItem = itemRepository.save(newItem);
	    // Build response DTO
	    ItemDetailResponseDTO response = new ItemDetailResponseDTO(
	            savedItem.getId(),
	            savedItem.getName(),
	            savedItem.getCategory(),
	            savedItem.getCreatedDate(),
	            new ItemDetailResponseDTO.SupplierInfo(
	                    user.getId(),
	                    user.getName(),
	                    user.getEmail()
	            )
	    );

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	    
	}

	public ResponseEntity<?> getItems(int userId) {
		//checks if user is available
	    User user = userRepository.findById(userId)
	            .orElseThrow(() ->  new UserNotFoundException("User not found"));
	    
	    
	    //if user is not admin or not supplier 
	    if (!user.getRole().equals(Role.Admin) && !user.getRole().equals(Role.Supplier)) {
	    	throw new InvalidRoleException("Invalid Role to access");
	    }
		List<Item> items = itemRepository.findAll();
		List<ItemSummaryResponseDTO> response = items.stream().map((item) ->new ItemSummaryResponseDTO(item.getId(),item.getName(),item.getCategory())).toList();
		return new ResponseEntity<>(response,HttpStatus.OK);

	}
	
	public ResponseEntity<?> getItem(int id){
		Optional<Item> item = itemRepository.findById(id);
		ItemSummaryResponseDTO response = new ItemSummaryResponseDTO(item.get().getId(),item.get().getName(),item.get().getCategory());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}



}
