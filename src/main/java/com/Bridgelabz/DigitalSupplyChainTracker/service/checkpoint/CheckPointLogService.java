package com.Bridgelabz.DigitalSupplyChainTracker.service.checkpoint;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.Exception.IdNotFoundException;
import com.Bridgelabz.DigitalSupplyChainTracker.Exception.InvalidIdException;
import com.Bridgelabz.DigitalSupplyChainTracker.Exception.InvalidRoleException;
import com.Bridgelabz.DigitalSupplyChainTracker.Exception.ShipmentNotFoundException;
import com.Bridgelabz.DigitalSupplyChainTracker.Utility.Role;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.checkPointDTO.CheckPointResponse;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.checkPointDTO.checkPointRequest;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.CheckPointLog;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Shipment;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.CheckPointLogRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.ShipmentRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.UserRepository;

@Service
public class CheckPointLogService implements CheckPointLogServiceInterface{

    @Autowired
    private CheckPointLogRepository checkpointRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;
    
	@Autowired
	UserRepository userRepository;

    public ResponseEntity<?> addCheckpoint(checkPointRequest request) {
    	
    	//checks if user is available
	    User user = userRepository.findById(request.getTransporterId())
	            .orElseThrow(() -> new IdNotFoundException("UserID not found!"));
	    
	    if(!user.getRole().equals(Role.Transporter)) {
	    	throw new InvalidRoleException("Invalid Role Access");
	    }
	    
        // Fetch Shipment entity by ID
        Shipment shipment = shipmentRepository.findById(request.getShipmentId())
                .orElseThrow(() -> new ShipmentNotFoundException("Shipment not found with ID: " + request.getShipmentId()));
        
        if(shipment.getAssignedTransporter().getId() != user.getId()) {
        	throw new InvalidIdException("Invalid Id");
        }

        // Create new checkpoint log
        CheckPointLog log = new CheckPointLog();
        log.setShipment(shipment); // set actual Shipment object
        log.setLocation(request.getLocation());
        log.setStatus(request.getStatus());
        log.setTimeStamp(LocalDateTime.now());

        checkpointRepository.save(log);

        return new ResponseEntity<>("Checkpoint added for shipment " + request.getShipmentId(), HttpStatus.OK);
    }
    
    public ResponseEntity<?> getByShipmentId(Integer shipmentId) {
        List<CheckPointLog> logs = checkpointRepository.findByShipmentShipmentId(shipmentId);
        List<CheckPointResponse> result = new ArrayList<>();
        	for(CheckPointLog log: logs) {
        		
        		CheckPointResponse dto = new CheckPointResponse();
        		dto.setCheckPointLogid(log.getCheckPointLogId());
        		dto.setShipmentId(log.getShipment().getShipmentId());
        		dto.setLocation(log.getLocation());
        		dto.setStatus(log.getStatus());
        		dto.setTimestamp(log.getTimeStamp());
        		result.add(dto);
        	}
           

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
