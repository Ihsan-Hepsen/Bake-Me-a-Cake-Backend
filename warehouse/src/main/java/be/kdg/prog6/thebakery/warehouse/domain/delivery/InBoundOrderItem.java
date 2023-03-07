package be.kdg.prog6.thebakery.warehouse.domain.delivery;

import be.kdg.prog6.thebakery.common.annotations.Default;
import be.kdg.prog6.thebakery.warehouse.domain.stock.StockItem;

import java.util.UUID;

public class InBoundOrderItem{

    private final InBoundOrder.InBoundOrderUUID inBoundOrderUuid;

    private final InBoundOrderItem.OrderItemUuid orderItemUuid;

    private final StockItem.StockItemUuid stockItemUuid;

    private double amount;

    private OrderItemStatus status;


    public record OrderItemUuid(UUID uuid) {

    }



    public InBoundOrderItem(InBoundOrderItem.OrderItemUuid orderItemUuid, InBoundOrder.InBoundOrderUUID inboundOrderUuid, UUID stockItemUuid, double amount, OrderItemStatus status) {
        this.orderItemUuid = orderItemUuid;
        this.inBoundOrderUuid = inboundOrderUuid;
        this.stockItemUuid = new StockItem.StockItemUuid(stockItemUuid);
        this.amount = amount;
        this.status = status;
    }

    public InBoundOrderItem(InBoundOrder.InBoundOrderUUID inBoundOrderUuid, UUID stockItemUuid, double amount) {
        this.orderItemUuid = new InBoundOrderItem.OrderItemUuid(UUID.randomUUID());
        this.inBoundOrderUuid = inBoundOrderUuid;
        this.stockItemUuid = new StockItem.StockItemUuid(stockItemUuid);
        this.amount = amount;
        this.status = OrderItemStatus.PENDING;
    }


    public InBoundOrder.InBoundOrderUUID inBoundOrderUuid() {
        return inBoundOrderUuid;
    }

    public InBoundOrderItem.OrderItemUuid orderItemUuid() {
        return orderItemUuid;
    }

    public StockItem.StockItemUuid stockItemUuid() {
        return stockItemUuid;
    }

    public double amount() {
        return amount;
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
        status = OrderItemStatus.FULFILLED;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(OrderItemStatus status) {
        this.status = status;
    }

}
