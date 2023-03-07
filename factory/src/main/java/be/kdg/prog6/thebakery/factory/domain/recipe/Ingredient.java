package be.kdg.prog6.thebakery.factory.domain.recipe;

import java.util.UUID;

public class Ingredient {

    private UUID ingredientUuid;
    private IngredientType type;
    private IngredientUnit unit;
    private double amount;

    public Ingredient(UUID ingredientUuid, IngredientType type, IngredientUnit unit, double amount) {
        this.ingredientUuid = ingredientUuid;
        this.type = type;
        this.unit = unit;
        this.amount = amount;
    }


    public UUID ingredientUuid() {
        return ingredientUuid;
    }

    public IngredientType type() {
        return type;
    }

    public void setType(IngredientType type) {
        this.type = type;
    }

    public IngredientUnit unit() {
        return unit;
    }

    public void setUnit(IngredientUnit unit) {
        this.unit = unit;
    }

    public double amount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setIngredientUuid(UUID ingredientUuid) {
        this.ingredientUuid = ingredientUuid;
    }
}