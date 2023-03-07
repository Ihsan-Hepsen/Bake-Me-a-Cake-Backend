package be.kdg.prog6.thebakery.factory.adapters.out.mapper;

import be.kdg.prog6.thebakery.factory.adapters.out.db.recipe.RecipeJpaEntity;
import be.kdg.prog6.thebakery.factory.domain.recipe.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {IngredientMapper.class})
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    @Mappings({
            @Mapping(target = "recipeUuid", expression = "java(recipe.recipeUuid().uuid())"),
            @Mapping(target = "name", expression = "java(recipe.recipeName())")
    })
    RecipeJpaEntity domainToJpa(Recipe recipe);

    @Mapping(target = "recipeUuid", expression = "java(new Recipe.RecipeUuid(recipeJpa.getRecipeUuid()))")
    @Mapping(target = "recipeName", expression = "java(recipeJpa.getName())")
    Recipe jpaToDomain(RecipeJpaEntity recipeJpa);
}
