package be.kdg.prog6.thebakery.factory.adapters.out.db.recipe;

import be.kdg.prog6.thebakery.factory.domain.recipe.RecipeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RecipeRepository extends JpaRepository<RecipeJpaEntity, UUID> {

    // get recipe with ingredients
    @Query("SELECT r FROM RecipeJpaEntity r LEFT JOIN FETCH r.ingredients WHERE r.name = ?1")
    RecipeJpaEntity findByName(RecipeName name);

    RecipeJpaEntity findByRecipeUuid(UUID uuid);

}
