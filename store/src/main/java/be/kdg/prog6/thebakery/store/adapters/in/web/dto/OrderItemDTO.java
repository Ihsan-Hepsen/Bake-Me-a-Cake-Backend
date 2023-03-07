package be.kdg.prog6.thebakery.store.adapters.in.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderItemDTO {

    @NotNull
    @JsonSerialize
    private String productId;

    @Min(0)
    @Max(20)
    @JsonSerialize
    private int quantity;


    public OrderItemDTO(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }


    public String productId() {
        return productId;
    }

    public int quantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "productName='" + productId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
