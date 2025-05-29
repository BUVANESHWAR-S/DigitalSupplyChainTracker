package com.Bridgelabz.DigitalSupplyChainTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.AlertRequestDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.AlertResponseDTO;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Alert;
import com.Bridgelabz.DigitalSupplyChainTracker.service.Alert.AlertService;


@RestController
@RequestMapping("/api/alerts")
public class AlertController {
	@Autowired 
	AlertService alertservice;
	
	@GetMapping
	public List<AlertResponseDTO> getAllAlerts() {
		return alertservice.getAllAlerts();
	}
	
	@PostMapping
    public AlertResponseDTO createAlert(@RequestBody AlertRequestDTO request, @RequestParam Alert.AlertType type) {
        return alertservice.createAlert(request,type);
    }
	
	@PutMapping("/{alertid}/resolve")
	public AlertResponseDTO resolveAlert(@PathVariable int alertid) {
		return alertservice.resolveAlert(alertid);
	}
}