package com.Bridgelabz.DigitalSupplyChainTracker.dto.item;

import java.time.LocalDateTime;

public class ItemDetailResponseDTO {
    private Integer id;
    private String name;
    private String category;
    private LocalDateTime createdDate;
    private SupplierInfo supplier;

    // Default constructor
    public ItemDetailResponseDTO() {}

    // Parameterized constructor
    public ItemDetailResponseDTO(Integer id, String name, String category, LocalDateTime createdDate, SupplierInfo supplier) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.createdDate = createdDate;
        this.supplier = supplier;
    }

    // Getters and setters
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public SupplierInfo getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierInfo supplier) {
        this.supplier = supplier;
    }

    // Nested static class
    public static class SupplierInfo {
        private Integer id;
        private String name;
        private String email;

        // Default constructor
        public SupplierInfo() {}

        // Parameterized constructor
        public SupplierInfo(Integer id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        // Getters and setters
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
