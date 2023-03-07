package be.kdg.prog6.thebakery.store.adapters.in.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SalesOrderDTO {

    @NotNull
    @NotEmpty
    @JsonSerialize
    private String customerUUID;

    @NotEmpty
    @NotNull
    @JsonSerialize
    private List<@Valid OrderItemDTO> orderItems;


    public SalesOrderDTO(String customerUUID, List<OrderItemDTO> orderItems) {
        this.customerUUID = customerUUID;
        this.orderItems = orderItems;
    }


    public String customerUUID() {
        return customerUUID;
    }

    public List<OrderItemDTO> orderItems() {
        return orderItems;
    }

    @Override
    public String toString() {
        return "SalesOrderDTO{" +
                "customerUUID='" + customerUUID + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}
