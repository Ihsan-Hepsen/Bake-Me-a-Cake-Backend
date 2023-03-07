package be.kdg.prog6.thebakery.factory.adapters.out.db.bakingorder;

import be.kdg.prog6.thebakery.factory.domain.order.OrderStatus;
import be.kdg.prog6.thebakery.factory.domain.recipe.RecipeName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "fruit_db", name = "baking_order")
public class BakingOrderJpaEntity {

    public BakingOrderJpaEntity() {
    }

    public BakingOrderJpaEntity(UUID uuid) {
        this.bakingOrderUuid = uuid;
    }


    @Id
    @Type(type = "uuid-char")
    private UUID bakingOrderUuid;

    @Column
    @Type(type = "uuid-char")
    private UUID customerUuid;

    @Column
    @Type(type = "uuid-char")
    private UUID recipeUuid;

    @Column
    private LocalDateTime orderDate;

    @Column
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Column
    private int quantity;


    public UUID getBakingOrderUuid() {
        return bakingOrderUuid;
    }

    public void setBakingOrderUuid(UUID bakingOrderUuid) {
        this.bakingOrderUuid = bakingOrderUuid;
    }

    public UUID getCustomerUuid() {
        return customerUuid;
    }

    public void setCustomerUuid(UUID customerUuid) {
        this.customerUuid = customerUuid;
    }

    public UUID getRecipeUuid() {
        return recipeUuid;
    }

    public void setRecipeUuid(UUID recipeUuid) {
        this.recipeUuid = recipeUuid;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
