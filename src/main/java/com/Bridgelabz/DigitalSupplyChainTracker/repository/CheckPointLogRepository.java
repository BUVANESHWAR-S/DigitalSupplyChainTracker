package com.Bridgelabz.DigitalSupplyChainTracker.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Bridgelabz.DigitalSupplyChainTracker.entity.CheckPointLog;



@Repository
public interface CheckPointLogRepository extends JpaRepository<CheckPointLog, Integer> {

    // Custom query method to find all logs by shipmentId
//    List<CheckPointLog> findByShipmentId(Integer shipmentId);
	List<CheckPointLog> findByShipmentShipmentId(Integer shipmentId);


}


