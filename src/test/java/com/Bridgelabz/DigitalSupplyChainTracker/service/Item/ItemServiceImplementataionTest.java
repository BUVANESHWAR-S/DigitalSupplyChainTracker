package com.Bridgelabz.DigitalSupplyChainTracker.service.Item;



import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.item.ItemCreateRequestDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.item.ItemDetailResponseDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.item.ItemSummaryResponseDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Item;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;
//import com.Bridgelabz.DigitalSupplyChainTracker.service.Item.ItemServiceImplementation;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.ItemRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.UserRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.Utility.Role;

 class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ItemServiceImplementation itemService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Test cases below...
    @Test
    void testAddItem_Success() {
        // Arrange
    	ItemCreateRequestDTO request = new ItemCreateRequestDTO();
        request.setName("Test Item");
        request.setCategory("Electronics");
        request.setSupplierId(1);

        User supplier = new User();
        supplier.setId(1);
        supplier.setName("Supplier Name");
        supplier.setEmail("supplier@example.com");
        supplier.setRole(Role.Supplier);

        Item savedItem = new Item();
        savedItem.setId(1);
        savedItem.setName("Test Item");
        savedItem.setCategory("Electronics");
        savedItem.setSupplier(supplier);
        savedItem.setCreatedDate(LocalDateTime.now());

        when(userRepository.findById(1)).thenReturn(Optional.of(supplier));
        when(itemRepository.save(any(Item.class))).thenReturn(savedItem);

        // Act
        ResponseEntity<?> response = itemService.addItem(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        ItemDetailResponseDTO responseBody = (ItemDetailResponseDTO) response.getBody();
        assertNotNull(responseBody);
        assertEquals("Test Item", responseBody.getName());
        assertEquals("Electronics", responseBody.getCategory());
        assertEquals("Supplier Name", responseBody.getSupplier().getName());
    }
    
    @Test
    void testGetItem_Success() {
        // Arrange
        Item item = new Item();
        item.setId(1);
        item.setName("Test Item");
        item.setCategory("Electronics");

        when(itemRepository.findById(1)).thenReturn(Optional.of(item));

        // Act
        ResponseEntity<?> response = itemService.getItem(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ItemSummaryResponseDTO responseBody = (ItemSummaryResponseDTO) response.getBody();
        assertNotNull(responseBody);
        assertEquals(1, responseBody.getId());
        assertEquals("Test Item", responseBody.getName());
        assertEquals("Electronics", responseBody.getCategory());
    }

}
