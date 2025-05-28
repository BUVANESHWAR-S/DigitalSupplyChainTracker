package com.Bridgelabz.DigitalSupplyChainTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bridgelabz.DigitalSupplyChainTracker.entity.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
