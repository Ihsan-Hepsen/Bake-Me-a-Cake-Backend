package be.kdg.prog6.thebakery.warehouse.adapters.in.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class OrderItemDTO {

    @JsonSerialize
    private String orderItemId;

    @JsonSerialize
    private String orderId;

    @JsonSerialize
    private String stockItemId;

    @JsonSerialize
    private String status;

    @JsonSerialize
    private double amount;


    public OrderItemDTO(String orderItemId, String orderId, String stockItemId, String status, double amount) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.stockItemId = stockItemId;
        this.status = status;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "orderItemId='" + orderItemId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", stockItemId='" + stockItemId + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                '}';
    }


    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStockItemId() {
        return stockItemId;
    }

    public void setStockItemId(String stockItemId) {
        this.stockItemId = stockItemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
