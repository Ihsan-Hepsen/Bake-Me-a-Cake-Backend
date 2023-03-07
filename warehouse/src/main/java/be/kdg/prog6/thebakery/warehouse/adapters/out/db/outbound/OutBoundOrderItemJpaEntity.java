package be.kdg.prog6.thebakery.warehouse.adapters.out.db.outbound;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.OrderItemStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(schema = "fruit_db", name = "outbound_orderitem")
public class OutBoundOrderItemJpaEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID orderItemUuid;

    @Column
    @Type(type = "uuid-char")
    private UUID outBoundOrderUuid;

    @Column
    @Type(type = "uuid-char")
    private UUID stockItemUuid;

    @Column
    private double amount;

    @Enumerated(EnumType.STRING)
    private OrderItemStatus status;

    public OutBoundOrderItemJpaEntity() {

    }

    public OutBoundOrderItemJpaEntity(UUID orderItemUuid, UUID stockItemUuid, double amount) {
        this.orderItemUuid = orderItemUuid;
        this.stockItemUuid = stockItemUuid;
        this.amount = amount;
    }

    public UUID getOrderItemUuid() {
        return orderItemUuid;
    }

    public void setOrderItemUuid(UUID orderItemUuid) {
        this.orderItemUuid = orderItemUuid;
    }

    public UUID getStockItemUuid() {
        return stockItemUuid;
    }

    public void setStockItemUuid(UUID stockItemUuid) {
        this.stockItemUuid = stockItemUuid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UUID getOutBoundOrderUuid() {
        return outBoundOrderUuid;
    }

    public void setOutBoundOrderUuid(UUID outBoundOrderUuid) {
        this.outBoundOrderUuid = outBoundOrderUuid;
    }

    public OrderItemStatus getStatus() {
        return status;
    }

    public void setStatus(OrderItemStatus status) {
        this.status = status;
    }
}
