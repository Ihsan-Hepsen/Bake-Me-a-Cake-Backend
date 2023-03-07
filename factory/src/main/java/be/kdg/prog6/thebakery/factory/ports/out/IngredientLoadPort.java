package be.kdg.prog6.thebakery.factory.ports.out;

import be.kdg.prog6.thebakery.factory.domain.recipe.Ingredient;

import java.util.Optional;
import java.util.UUID;

public interface IngredientLoadPort {

    Optional<Ingredient> loadIngredient(UUID uuid);
}
