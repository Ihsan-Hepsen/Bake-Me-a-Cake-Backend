package be.kdg.prog6.thebakery.factory.adapters.out.db.recipe;

import be.kdg.prog6.thebakery.factory.adapters.out.db.ingredient.IngredientJpaEntity;
import be.kdg.prog6.thebakery.factory.domain.recipe.RecipeName;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "fruit_db", name = "recipe")
public class RecipeJpaEntity {

    public RecipeJpaEntity() {

    }

    public RecipeJpaEntity(UUID recipeUuid) {
        this.recipeUuid = recipeUuid;
    }


    @Id
    @Type(type = "uuid-char")
    private UUID recipeUuid;

    @Column
    @Enumerated(value = EnumType.STRING)
    private RecipeName name;

    @OneToMany(targetEntity = IngredientJpaEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<IngredientJpaEntity> ingredients;



    public UUID getRecipeUuid() {
        return recipeUuid;
    }

    public void setRecipeUuid(UUID recipeUuid) {
        this.recipeUuid = recipeUuid;
    }

    public RecipeName getName() {
        return name;
    }

    public void setName(RecipeName name) {
        this.name = name;
    }

    public List<IngredientJpaEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientJpaEntity> ingredients) {
        this.ingredients = ingredients;
    }
}
