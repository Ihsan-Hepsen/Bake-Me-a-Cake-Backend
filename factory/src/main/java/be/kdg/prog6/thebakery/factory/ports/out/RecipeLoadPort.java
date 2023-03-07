package be.kdg.prog6.thebakery.factory.ports.out;

import be.kdg.prog6.thebakery.factory.domain.recipe.Recipe;
import be.kdg.prog6.thebakery.factory.domain.recipe.RecipeName;

import java.util.Optional;
import java.util.UUID;

public interface RecipeLoadPort {

    Optional<Recipe> loadRecipe(UUID recipeUuid);

    Optional<Recipe> loadRecipe(RecipeName recipeName);
}
