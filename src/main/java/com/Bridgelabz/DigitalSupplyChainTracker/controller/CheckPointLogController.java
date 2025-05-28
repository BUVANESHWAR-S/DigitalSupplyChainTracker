package com.Bridgelabz.DigitalSupplyChainTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.checkPointDTO.checkPointRequest;
import com.Bridgelabz.DigitalSupplyChainTracker.service.checkpoint.CheckPointLogService;
import com.Bridgelabz.DigitalSupplyChainTracker.service.checkpoint.CheckPointLogServiceInterface;


@RestController
@RequestMapping("/api/checkpoints")
public class CheckPointLogController {

    @Autowired
    private CheckPointLogServiceInterface checkpointlogserviceinterface;

    @PostMapping
    public ResponseEntity<?> addCheckpoint(@RequestBody checkPointRequest request) {
        return new ResponseEntity<>(checkpointlogserviceinterface.addCheckpoint(request), HttpStatus.OK);
    }

    @GetMapping("/shipment/{id}")
    public ResponseEntity<?> getCheckpoints(@PathVariable Integer id) {
        return new ResponseEntity<>(checkpointlogserviceinterface.getByShipmentId(id), HttpStatus.OK);
    }
}
