package be.kdg.prog6.thebakery.factory.adapters.out.mapper;

import be.kdg.prog6.thebakery.factory.adapters.out.db.ingredient.IngredientJpaEntity;
import be.kdg.prog6.thebakery.factory.domain.recipe.Ingredient;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    @Mappings({
            @Mapping(target = "ingredientUuid", expression = "java(ingredient.ingredientUuid())"),
            @Mapping(target = "type", expression = "java(ingredient.type())"),
            @Mapping(target = "unit", expression = "java(ingredient.unit())"),
            @Mapping(target = "amount", expression = "java(ingredient.amount())")
    })
    IngredientJpaEntity domainToJpa(Ingredient ingredient);

    @InheritInverseConfiguration
    Ingredient jpaToDomain(IngredientJpaEntity ingredientJpa);
}
