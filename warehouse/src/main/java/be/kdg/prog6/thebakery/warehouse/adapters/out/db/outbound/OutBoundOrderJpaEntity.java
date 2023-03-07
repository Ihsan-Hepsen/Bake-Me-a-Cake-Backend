package be.kdg.prog6.thebakery.warehouse.adapters.out.db.outbound;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.OrderStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(schema = "fruit_db", name = "outbound_order")
public class OutBoundOrderJpaEntity {

    public OutBoundOrderJpaEntity() {

    }

    public OutBoundOrderJpaEntity(UUID uuid) {
        this.orderUuid = uuid;
    }


    @Id
    @Type(type = "uuid-char")
    private UUID orderUuid;


    @OneToMany(targetEntity = OutBoundOrderItemJpaEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<OutBoundOrderItemJpaEntity> orderList;

    @Column
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    public UUID getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(UUID orderUuid) {
        this.orderUuid = orderUuid;
    }

    public List<OutBoundOrderItemJpaEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OutBoundOrderItemJpaEntity> orderList) {
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
