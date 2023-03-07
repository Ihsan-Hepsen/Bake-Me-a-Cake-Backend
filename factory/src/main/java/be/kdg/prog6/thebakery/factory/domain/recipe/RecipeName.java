package be.kdg.prog6.thebakery.factory.domain.recipe;

public enum RecipeName {
    DONUT_REGULAR("Donut"),
    DONUT_CHOCOLATE("Chocolate Donut"),
    DONUT_JELLY("Jelly Filled Donut"),
    BAGEL_REGULAR("Bagel"),
    BAGEL_BLUEBERRY("Blueberry Bagel"),
    BAGEL_CHOCOLATE("Chocolate Bagel");

    private final String name;

    RecipeName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
