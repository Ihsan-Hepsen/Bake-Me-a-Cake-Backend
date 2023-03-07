package be.kdg.prog6.thebakery.warehouse.domain.delivery;

import be.kdg.prog6.thebakery.common.annotations.Default;
import be.kdg.prog6.thebakery.warehouse.domain.stock.StockItem;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.UUID;

public class OutBoundOrderItem {

    @JsonSerialize
    private final OrderItemUuid orderItemUuid;

    @JsonSerialize
    private OutBoundOrder.OutBoundOrderUUID outBoundOrderUuid;

    @JsonSerialize
    private StockItem.StockItemUuid stockItemUuid;

    @JsonSerialize
    private OrderItemStatus status;

    @JsonSerialize
    private double amount;

    @Default
    public OutBoundOrderItem(OrderItemUuid orderItemUuid, UUID outBoundOrderUuid, UUID stockItemUuid, double amount, OrderItemStatus status) {
        this.orderItemUuid = orderItemUuid;
        this.outBoundOrderUuid = new OutBoundOrder.OutBoundOrderUUID(outBoundOrderUuid);
        this.stockItemUuid = new StockItem.StockItemUuid(stockItemUuid);
        this.amount = amount;
        this.status = status;
    }

    public OutBoundOrderItem(UUID outBoundOrderUuid, UUID stockItemUuid, double amount) {
        this.orderItemUuid = new OrderItemUuid(UUID.randomUUID());
        this.outBoundOrderUuid = new OutBoundOrder.OutBoundOrderUUID(outBoundOrderUuid);
        this.stockItemUuid = new StockItem.StockItemUuid(stockItemUuid);
        this.amount = amount;
        this.status = OrderItemStatus.PENDING;
    }


    public record OrderItemUuid(UUID uuid) {

    }

    public double amount() {
        return amount;
    }

    public OrderItemUuid orderItemUuid() {
        return orderItemUuid;
    }

    public StockItem.StockItemUuid stockItemUuid() {
        return stockItemUuid;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(OrderItemStatus status) {
        this.status = status;
    }

    public OrderItemStatus status() {
        return status;
    }

    public boolean isPending() {
        return status == OrderItemStatus.PENDING;
    }

    public boolean isFulfilled() {
        return status == OrderItemStatus.FULFILLED;
    }

    public void fulfill() {
        this.status = OrderItemStatus.FULFILLED;
    }

    public void setStockItemUuid(UUID stockItemUuid) {
        this.stockItemUuid = new StockItem.StockItemUuid(stockItemUuid);
    }

    public OutBoundOrder.OutBoundOrderUUID outBoundOrderUuid() {
        return outBoundOrderUuid;
    }

    public void setOutBoundOrderUuid(UUID outBoundOrderUuid) {
        this.outBoundOrderUuid = new OutBoundOrder.OutBoundOrderUUID(outBoundOrderUuid);
    }
}
