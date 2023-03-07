package be.kdg.prog6.thebakery.factory.ports.out;

import be.kdg.prog6.thebakery.factory.domain.recipe.Recipe;

public interface RecipeUpdatePort {

    void updateRecipe(Recipe recipe);
}
