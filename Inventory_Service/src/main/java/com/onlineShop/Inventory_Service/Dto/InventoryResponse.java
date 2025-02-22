package com.onlineShop.Inventory_Service.Dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InventoryResponse {

    private String countryCode;
    private boolean isInStock;

    public InventoryResponse(String countryCode, boolean isInStock) {
        this.countryCode = countryCode;
        this.isInStock = isInStock;
    }

    public InventoryResponse(String countryCode) {
    }

    public InventoryResponse(boolean inStock) {
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }

    public static class Builder {
        private String countryCode;

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public InventoryResponse build() {
            return new InventoryResponse(countryCode);
        }

        public InventoryResponse isInStock(boolean inStock) {
            return new InventoryResponse(inStock);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
