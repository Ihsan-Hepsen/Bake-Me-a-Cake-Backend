package be.kdg.prog6.thebakery.factory.domain.order;

import be.kdg.prog6.thebakery.factory.domain.recipe.Recipe;

import java.util.UUID;

public class Product {


    private ProductUuid productUuid;

    private String name;

    private Recipe recipe;

    public record ProductUuid(UUID uuid) {
    }



    public Product(ProductUuid productUuid, String name, Recipe recipe) {
        this.productUuid = productUuid;
        this.name = name;
        this.recipe = recipe;
    }

    public ProductUuid productUuid() {
        return productUuid;
    }

    public void setProductUuid(UUID productUuid) {
        this.productUuid = new ProductUuid(productUuid);
    }

    public Recipe recipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String name() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }
}

