package com.Bridgelabz.DigitalSupplyChainTracker.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public User getSupplier() {
		return supplier;
	}

	public void setSupplier(User supplier) {
		this.supplier = supplier;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Item(String name, String category, User supplier, LocalDateTime createdDate) {
	
		this.name = name;
		this.category = category;
		this.supplier = supplier;
		this.createdDate = createdDate;
	}

	public Item() {
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private User supplier;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", category=" + category + ", supplier=" + supplier
				+ ", createdDate=" + createdDate + "]";
	}
    
    
    
    
}

