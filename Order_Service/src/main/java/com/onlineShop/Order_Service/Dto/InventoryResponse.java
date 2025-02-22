package com.onlineShop.Order_Service.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {

    private String countryCode;
    private boolean isInStock;

    public InventoryResponse(String countryCode) {
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }

    public static class Builder {
        private String countryCode;

        public Builder someField(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public InventoryResponse build() {
            return new InventoryResponse(countryCode);
        }
    }

    // Static method to obtain a builder instance
    public static Builder builder() {
        return new Builder();
    }

}
