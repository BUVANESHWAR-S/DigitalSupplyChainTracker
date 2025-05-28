package com.Bridgelabz.DigitalSupplyChainTracker.dto.item;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemSummaryResponse {
	
	private Integer id;
    private String name;
    private String category;
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	
	public ItemSummaryResponse(Integer id, String name, String category) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
	}
}
