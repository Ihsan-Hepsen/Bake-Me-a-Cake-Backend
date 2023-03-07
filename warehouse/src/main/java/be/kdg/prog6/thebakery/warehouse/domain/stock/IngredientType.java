package be.kdg.prog6.thebakery.warehouse.domain.stock;

public enum IngredientType {
    COCOA_POWDER("Cocoa Powder"),
    FLOUR("Flour"),
    SUGAR("Sugar"),
    MILK("Milk"),
    VANILLA("Vanilla"),
    CORN_FLOUR("Corn Flour"),
    CHOCOLATE_CHIPS("Chocolate Chips");


    private final String type;


    IngredientType(String type) {
        this.type = type;
    }


    public String type() {
        return type;
    }
}
