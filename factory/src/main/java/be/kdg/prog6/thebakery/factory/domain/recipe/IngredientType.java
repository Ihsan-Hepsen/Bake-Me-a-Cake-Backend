package be.kdg.prog6.thebakery.factory.domain.recipe;

public enum IngredientType {
    COCOA_POWDER("Cocoa Powder"),
    FLOUR("Flour"),
    SUGAR("Sugar"),
    MILK("Milk"),
    VANILLA("Vanilla"),
    CORN_FLOUR("Corn Flour"),
    CHOCOLATE_CHIPS("Chocolate Chips");


    private final String typeName;


    IngredientType(String typeName) {
        this.typeName = typeName;
    }


    public String typeName() {
        return typeName;
    }

}

