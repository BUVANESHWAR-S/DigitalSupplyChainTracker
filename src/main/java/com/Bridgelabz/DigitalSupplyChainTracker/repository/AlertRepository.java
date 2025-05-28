package com.Bridgelabz.DigitalSupplyChainTracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Bridgelabz.DigitalSupplyChainTracker.entity.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
	List<Alert> findbyResolved(boolean resolved);
	List<Alert> findbyShipmentId(Integer shipmentId);
	List<Alert> findByType(Alert.AlertType type);
}
