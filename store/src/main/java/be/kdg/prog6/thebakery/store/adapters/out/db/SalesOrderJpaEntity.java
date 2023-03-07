package be.kdg.prog6.thebakery.store.adapters.out.db;

import be.kdg.prog6.thebakery.store.domain.OrderItem;
import be.kdg.prog6.thebakery.store.domain.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(schema = "fruit_db", name = "sales_order")
public class SalesOrderJpaEntity {

    public SalesOrderJpaEntity() {

    }

    public SalesOrderJpaEntity(UUID orderUuid) {
        this.orderUuid = orderUuid;
    }

    @Id
    @Type(type = "uuid-char")
    private UUID orderUuid;

    @Column(precision = 2)
    private double price;

    @Column
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToMany(targetEntity = OrderItemJpaEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItemJpaEntity> orderItems;

    @Column
    private LocalDateTime orderDate;



    @Type(type = "uuid-char")
    private UUID customer;


    public UUID getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(UUID orderUuid) {
        this.orderUuid = orderUuid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<OrderItemJpaEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemJpaEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public UUID getCustomer() {
        return customer;
    }

    public void setCustomer(UUID customer) {
        this.customer = customer;
    }
}
