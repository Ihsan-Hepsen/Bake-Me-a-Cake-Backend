package be.kdg.prog6.thebakery.factory.ports.out;

import be.kdg.prog6.thebakery.factory.domain.recipe.Ingredient;

public interface IngredientUpdatePort {

    void updateIngredient(Ingredient ingredient);
}
