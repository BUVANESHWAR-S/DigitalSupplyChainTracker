package com.Bridgelabz.DigitalSupplyChainTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bridgelabz.DigitalSupplyChainTracker.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
