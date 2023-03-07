package be.kdg.prog6.thebakery.warehouse.adapters.in.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class InBoundOrderDTO {

    @JsonSerialize
    private String orderId;

    @JsonSerialize
    private String supplierId;

    @JsonSerialize
    private List<OrderItemDTO> orderList;

    @JsonSerialize
    private String orderDate;

    @JsonSerialize
    private String orderStatus;


    public InBoundOrderDTO(String orderId, String supplierId, List<OrderItemDTO> orderList, String orderDate, String orderStatus) {
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.orderList = orderList;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "InBoundOrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", orderList=" + orderList +
                ", orderDate=" + orderDate +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public List<OrderItemDTO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderItemDTO> orderList) {
        this.orderList = orderList;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
