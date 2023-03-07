package be.kdg.prog6.thebakery.warehouse.adapters.out.db.inbound;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.OrderStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "fruit_db", name = "inbound_order")
public class InBoundOrderJpaEntity {

    @Id
    @Column(name = "inbound_order_uuid", nullable = false)
    @Type(type = "uuid-char")
    private UUID inBoundOrderUuid;

    @Column(name = "supplier_uuid", nullable = false)
    @Type(type = "uuid-char")
    private UUID supplierUuid;

    @OneToMany(targetEntity = InBoundOrderItemJpaEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InBoundOrderItemJpaEntity> orderList;

    @Column
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    public UUID getInBoundOrderUuid() {
        return inBoundOrderUuid;
    }

    public void setInBoundOrderUuid(UUID inBoundOrderUuid) {
        this.inBoundOrderUuid = inBoundOrderUuid;
    }

    public UUID getSupplierUuid() {
        return supplierUuid;
    }

    public void setSupplierUuid(UUID supplierUuid) {
        this.supplierUuid = supplierUuid;
    }

    public List<InBoundOrderItemJpaEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<InBoundOrderItemJpaEntity> orderList) {
        this.orderList = orderList;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
