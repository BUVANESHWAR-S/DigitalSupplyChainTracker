package com.Bridgelabz.DigitalSupplyChainTracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Bridgelabz.DigitalSupplyChainTracker.entity.Alert;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.Shipment;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
//	List<Alert> findByResolved(boolean resolved);
//	List<Alert> findByShipment(Shipment shipment);
//	List<Alert> findByType(Alert.AlertType type);
}
