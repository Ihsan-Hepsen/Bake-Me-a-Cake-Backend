package be.kdg.prog6.thebakery.factory.adapters.out.db.recipe;

import be.kdg.prog6.thebakery.factory.adapters.out.mapper.RecipeMapper;
import be.kdg.prog6.thebakery.factory.domain.recipe.Recipe;
import be.kdg.prog6.thebakery.factory.domain.recipe.RecipeName;
import be.kdg.prog6.thebakery.factory.ports.out.RecipeLoadPort;
import be.kdg.prog6.thebakery.factory.ports.out.RecipeUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RecipeDBAdapter implements RecipeLoadPort, RecipeUpdatePort {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RecipeDBAdapter(RecipeRepository recipeRepository, RecipeMapper mapper) {
        this.recipeRepository = recipeRepository;
        this.mapper = mapper;
    }


    @Override
    public Optional<Recipe> loadRecipe(UUID recipeUuid) {
        RecipeJpaEntity recipeJpa = recipeRepository.findByRecipeUuid(recipeUuid);
        if (recipeJpa == null) {
            log.error("Recipe: " + recipeUuid + " DOES NOT exists.");
            return Optional.empty();
        }
        return Optional.of(mapper.jpaToDomain(recipeJpa));
    }

    @Override
    public Optional<Recipe> loadRecipe(RecipeName recipeName) {
        RecipeJpaEntity recipeJpa = recipeRepository.findByName(recipeName);
        if (recipeJpa == null) {
            log.error("Recipe: " + recipeName + " DOES NOT exists.");
            return Optional.empty();
        }
        return Optional.of(mapper.jpaToDomain(recipeJpa));
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipeRepository.save(mapper.domainToJpa(recipe));
    }
}
