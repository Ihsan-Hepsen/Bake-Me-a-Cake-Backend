package be.kdg.prog6.thebakery.warehouse.domain.stock;

import be.kdg.prog6.thebakery.common.annotations.Default;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class StockItem {

    @JsonSerialize
    private final StockItemUuid stockItemUuid;

    @JsonSerialize
    private IngredientType ingredientType;

    @JsonSerialize
    private Unit unit;

    @JsonSerialize
    private Category category;

    @JsonSerialize
    private double amount;

    @JsonSerialize
    private LocalDate expirationDate;

    public record StockItemUuid(UUID uuid) {

    }

    @Default
    public StockItem(StockItemUuid stockItemUuid) {
        this.stockItemUuid = stockItemUuid;
        this.ingredientType = null;
        this.unit = null;
        this.category = null;
        this.amount = 0.0;
        this.expirationDate = null;
    }

    public StockItem(IngredientType ingredientType, Unit unit, Category category, double amount, LocalDate expirationDate) {
        this.stockItemUuid = new StockItemUuid(UUID.randomUUID());
        this.ingredientType = ingredientType;
        this.unit = unit;
        this.category = category;
        this.amount = amount;
        this.expirationDate = expirationDate;
    }


    public StockItemUuid stockItemUuid() {
        return stockItemUuid;
    }

    public IngredientType ingredientType() {
        return ingredientType;
    }

    public void setIngredientType(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    public Unit unit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Category category() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double amount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate expirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @JsonIgnore
    public boolean isOutOfStock() {
        return amount == 0;
    }

    public void increaseAmount(double amount) {
        this.amount += amount;
    }

    public void decreaseAmount(double amount) {
        this.amount -= amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockItem stockItem = (StockItem) o;
        return Objects.equals(stockItemUuid, stockItem.stockItemUuid) && ingredientType == stockItem.ingredientType && unit == stockItem.unit && category == stockItem.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockItemUuid, ingredientType, unit, category);
    }
}
