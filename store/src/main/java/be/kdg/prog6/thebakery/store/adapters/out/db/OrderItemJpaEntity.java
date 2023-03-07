package be.kdg.prog6.thebakery.store.adapters.out.db;

import be.kdg.prog6.thebakery.store.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(schema = "fruit_db", name = "order_item")
public class OrderItemJpaEntity {

    public OrderItemJpaEntity() {

    }

    public OrderItemJpaEntity(UUID orderItemUuid) {
        this.orderItemUuid = orderItemUuid;
    }

    @Id
    @Type(type = "uuid-char")
    private UUID orderItemUuid;

    @Column
    @Type(type = "uuid-char")
    private UUID salesOrderUuid;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Product product;

    @Column
    private int quantity;


    public UUID getOrderItemUuid() {
        return orderItemUuid;
    }

    public void setOrderItemUuid(UUID orderItemUuid) {
        this.orderItemUuid = orderItemUuid;
    }

    public UUID getSalesOrderUuid() {
        return salesOrderUuid;
    }

    public void setSalesOrderUuid(UUID salesOrderUuid) {
        this.salesOrderUuid = salesOrderUuid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
