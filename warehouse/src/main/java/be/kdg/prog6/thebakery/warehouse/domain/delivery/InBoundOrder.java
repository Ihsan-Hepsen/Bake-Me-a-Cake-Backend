package be.kdg.prog6.thebakery.warehouse.domain.delivery;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InBoundOrder {

    private InBoundOrderUUID inBoundOrderUuid;
    private UUID supplierUuid;
    private List<InBoundOrderItem> orderList;
    private LocalDateTime orderDate;
    private OrderStatus status;


    public record InBoundOrderUUID(UUID uuid) {

    }


    public InBoundOrder() {
        this.inBoundOrderUuid = new InBoundOrderUUID(UUID.randomUUID());
        this.supplierUuid = null;
        this.orderDate = LocalDateTime.now();
        this.orderList = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }

    public InBoundOrder(InBoundOrderUUID orderUuid, UUID supplierUuid) {
        this.inBoundOrderUuid = orderUuid;
        this.supplierUuid = supplierUuid;
        this.orderDate = LocalDateTime.now();
        this.orderList = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }


    public InBoundOrderUUID inBoundOrderUuid() {
        return inBoundOrderUuid;
    }

    public void setInBoundOrderUuid(UUID inBoundOrderUuid) {
        this.inBoundOrderUuid = new InBoundOrderUUID(inBoundOrderUuid);
    }

    public List<InBoundOrderItem> orderList() {
        return this.orderList;
    }

    public void setOrderList(List<InBoundOrderItem> orderList) {
        this.orderList = orderList;
    }

    public void addInBoundItem(InBoundOrderItem stockItem) {
        orderList.add(stockItem);
    }

    public UUID supplierUuid() {
        return supplierUuid;
    }

    public void setSupplierUuid(UUID supplierUuid) {
        this.supplierUuid = supplierUuid;
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
        this.status = OrderStatus.FULFILLED;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean isFulfilled() {
        return this.status == OrderStatus.FULFILLED;
    }

    public boolean isPending() {
        return this.status == OrderStatus.PENDING;
    }

    public boolean hasNoStatus() {
        return this.status == null;
    }

}
