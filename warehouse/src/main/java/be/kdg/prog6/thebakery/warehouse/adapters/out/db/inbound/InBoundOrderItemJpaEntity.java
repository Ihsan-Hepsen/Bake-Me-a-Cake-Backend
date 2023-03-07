package be.kdg.prog6.thebakery.warehouse.adapters.out.db.inbound;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.OrderItemStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(schema = "fruit_db", name = "inbound_orderitem")
public class InBoundOrderItemJpaEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID orderItemUuid;

    @Column
    @Type(type = "uuid-char")
    private UUID inBoundOrderUuid;

    @Column
    @Type(type = "uuid-char")
    private UUID stockItemUuid;

    @Column
    private double amount;

    @Enumerated(EnumType.STRING)
    private OrderItemStatus status;



    public UUID getOrderItemUuid() {
        return orderItemUuid;
    }

    public void setOrderItemUuid(UUID orderItemUuid) {
        this.orderItemUuid = orderItemUuid;
    }

    public UUID getInBoundOrderUuid() {
        return inBoundOrderUuid;
    }

    public void setInBoundOrderUuid(UUID inBoundOrderUuid) {
        this.inBoundOrderUuid = inBoundOrderUuid;
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

    public OrderItemStatus getStatus() {
        return status;
    }

    public void setStatus(OrderItemStatus status) {
        this.status = status;
    }

}
