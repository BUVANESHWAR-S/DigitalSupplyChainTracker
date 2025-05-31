package com.Bridgelabz.DigitalSupplyChainTracker.service.Alert;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.AlertRequestDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.AlertResponseDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Alert;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Item;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Shipment;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.AlertRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.ItemRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.ShipmentRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.service.Email.EmailService;

class AlertServiceImplTest {
	
	@InjectMocks
    private AlertServiceImpl alertService;

    @Mock
    private AlertRepository alertRepository;
    
    @Mock
    private ItemRepository itemRepository;
    
    @Mock
    private EmailService emailService;

    @Mock
    private ShipmentRepository shipmentRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
	void testGetAllAlerts() {
        Alert alert = new Alert();
        Shipment shipment = new Shipment();
        shipment.setShipmentId(1);
        alert.setAlertid(1);
        alert.setShipment(shipment);
        alert.setType(Alert.AlertType.Delay);
        alert.setMessage("Delayed 2 days");
        alert.setCreatedOn(LocalDateTime.now());
        alert.setResolved(false);

        when(alertRepository.findAll()).thenReturn(List.of(alert));

        List<AlertResponseDTO> responses = alertService.getAllAlerts();

        assertEquals(1, responses.size());
        assertEquals("Delayed 2 days", responses.get(0).getMessage());
        assertEquals(Alert.AlertType.Delay, responses.get(0).getType());
    }
	
	 @Test
	    void testCreateAlertWithEmailNotification() {
	        AlertRequestDTO request = new AlertRequestDTO();
	        request.setShipmentId(1);
	        request.setMessage("Shipment delayed");

	        User supplier = new User();
	        supplier.setEmail("Koushik080903@gmail.com");

	        Item item = new Item();
	        item.setSupplier(supplier);

	        Shipment shipment = new Shipment();
	        shipment.setShipmentId(1);
	        shipment.setItem(item);

	        Alert alert = new Alert();
	        alert.setAlertid(101);
	        alert.setShipment(shipment);
	        alert.setMessage("Shipment delayed");
	        alert.setType(Alert.AlertType.Delay);
	        alert.setCreatedOn(LocalDateTime.now());
	        alert.setResolved(false);

	        when(shipmentRepository.findById(1)).thenReturn(Optional.of(shipment));
	        when(alertRepository.save(any(Alert.class))).thenReturn(alert);

	        AlertResponseDTO response = alertService.createAlert(request, Alert.AlertType.Delay);

	        assertEquals(101, response.getId());
	        assertEquals("Shipment delayed", response.getMessage());
	        verify(emailService, times(1)).send(anyString(), anyString(), anyString());

	 }
	 
	 @Test
	    void testResolveAlertWithEmailNotification() {
	        User supplier = new User();
	        supplier.setEmail("supplier@example.com");

	        Item item = new Item();
	        item.setSupplier(supplier);

	        Shipment shipment = new Shipment();
	        shipment.setShipmentId(2);
	        shipment.setItem(item);

	        Alert alert = new Alert();
	        alert.setAlertid(202);
	        alert.setShipment(shipment);
	        alert.setType(Alert.AlertType.Damage);
	        alert.setMessage("Box damaged");
	        alert.setCreatedOn(LocalDateTime.now());
	        alert.setResolved(false);

	        when(alertRepository.findById(202)).thenReturn(Optional.of(alert));
	        when(alertRepository.save(any(Alert.class))).thenReturn(alert);

	        AlertResponseDTO resolved = alertService.resolveAlert(202);

	        assertTrue(resolved.isResolved());
	        verify(emailService, times(1)).send(anyString(), anyString(), anyString());
	        
	    }
}


