package be.kdg.prog6.thebakery.warehouse.domain.delivery;

import be.kdg.prog6.thebakery.common.annotations.Default;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OutBoundOrder {

    private OutBoundOrderUUID outBoundOrderUUID;
    private List<OutBoundOrderItem> orderList;
    private LocalDateTime orderDate;
    private OrderStatus status;
    public record OutBoundOrderUUID(UUID uuid) {

    }


    @Default
    public OutBoundOrder(UUID uuid) {
        this.outBoundOrderUUID = new OutBoundOrderUUID(uuid);
        this.orderList = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }

    public OutBoundOrder() {
        this.outBoundOrderUUID = new OutBoundOrderUUID(UUID.randomUUID());
        this.orderList = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }

    public OutBoundOrder(UUID uuid, List<OutBoundOrderItem> orderList, OrderStatus status, LocalDateTime orderDate) {
        this.outBoundOrderUUID = new OutBoundOrderUUID(uuid);
        this.orderList = orderList;
        this.orderDate = orderDate;
        this.status = status;
    }


    public OutBoundOrderUUID outBoundOrderUUID() {
        return outBoundOrderUUID;
    }

    public List<OutBoundOrderItem> orderList() {
        return orderList;
    }

    public void setOrderList(List<OutBoundOrderItem> orderList) {
        this.orderList = orderList;
    }

    public void addOutBoundItem(OutBoundOrderItem stockItem) {
        orderList.add(stockItem);
    }

    public void setOutBoundOrderUUID(UUID outBoundOrderUUID) {
        this.outBoundOrderUUID = new OutBoundOrderUUID(outBoundOrderUUID);
    }

    public LocalDateTime orderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus status() {
        return status;
    }

    public void fulfill() {
        status = OrderStatus.FULFILLED;
    }

    public boolean isFulfilled() {
        return status == OrderStatus.FULFILLED;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
