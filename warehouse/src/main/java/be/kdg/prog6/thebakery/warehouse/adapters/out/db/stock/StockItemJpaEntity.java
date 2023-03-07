package be.kdg.prog6.thebakery.warehouse.adapters.out.db.stock;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;
import be.kdg.prog6.thebakery.warehouse.domain.stock.Category;
import be.kdg.prog6.thebakery.warehouse.domain.stock.IngredientType;
import be.kdg.prog6.thebakery.warehouse.domain.stock.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(schema = "fruit_db", name = "stock")
public class StockItemJpaEntity {

    public StockItemJpaEntity() {

    }

    public StockItemJpaEntity(UUID uuid) {
        this.stockItemUuid = uuid;
    }


    @Id
    @Type(type = "uuid-char")
    private UUID stockItemUuid;

    @Column
    @Enumerated(value = EnumType.STRING)
    private IngredientType ingredientType;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Unit unit;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column
    private double amount;

    @Column
    private LocalDate expirationDate;



    public UUID getStockItemUuid() {
        return stockItemUuid;
    }

    public void setStockItemUuid(UUID stockItemUuid) {
        this.stockItemUuid = stockItemUuid;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
