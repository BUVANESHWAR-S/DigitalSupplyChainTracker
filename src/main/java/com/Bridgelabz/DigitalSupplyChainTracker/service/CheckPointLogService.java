package com.Bridgelabz.DigitalSupplyChainTracker.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.CheckPointResponse;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.checkPointRequest;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.CheckPointLog;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Shipment;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.CheckPointLogRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.ShipmentRepository;

@Service
public class CheckPointLogService {

    @Autowired
    private CheckPointLogRepository checkpointRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    public String addCheckpoint(checkPointRequest request) {
        // Fetch Shipment entity by ID
        Shipment shipment = shipmentRepository.findById(request.getShipmentId())
                .orElseThrow(() -> new RuntimeException("Shipment not found with ID: " + request.getShipmentId()));

        // Create new checkpoint log
        CheckPointLog log = new CheckPointLog();
        log.setShipment(shipment); // set actual Shipment object
        log.setLocation(request.getLocation());
        log.setStatus(request.getStatus());
        log.setTimeStamp(LocalDateTime.now());

        checkpointRepository.save(log);

        return "Checkpoint added for shipment " + request.getShipmentId();
    }

    public List<CheckPointResponse> getByShipment(Integer shipmentId) {
        List<CheckPointLog> list = checkpointRepository.findByShipment_Id(shipmentId);
        List<CheckPointResponse> result = new ArrayList<>();

        for (CheckPointLog log : list) {
            CheckPointResponse dto = new CheckPointResponse();
            dto.setCheckPointLogid(log.getCheckPointLogId());
            dto.setShipmentId(log.getShipment().getId()); // Extract ID from Shipment object
            dto.setLocation(log.getLocation());
            dto.setStatus(log.getStatus());
            dto.setTimestamp(log.getTimeStamp());
            result.add(dto);
        }

        return result;
    }
}
