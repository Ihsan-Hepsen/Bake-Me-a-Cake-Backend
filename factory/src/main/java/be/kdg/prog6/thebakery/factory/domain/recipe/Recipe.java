package be.kdg.prog6.thebakery.factory.domain.recipe;

import java.util.List;
import java.util.UUID;

public class Recipe {

    private Recipe.RecipeUuid recipeUuid;
    private final RecipeName recipeName;
    private List<Ingredient> ingredients;


    public record RecipeUuid(UUID uuid) {
    }

    public Recipe(RecipeUuid recipeUuid, RecipeName recipeName, List<Ingredient> ingredients) {
        this.recipeUuid = recipeUuid;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
    }

    public RecipeUuid recipeUuid() {
        return recipeUuid;
    }

    public RecipeName recipeName() {
        return recipeName;
    }

    public List<Ingredient> ingredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setRecipeUuid(RecipeUuid recipeUuid) {
        this.recipeUuid = recipeUuid;
    }
}