package be.kdg.prog6.thebakery.factory.adapters.out.db.ingredient;

import be.kdg.prog6.thebakery.factory.adapters.out.mapper.IngredientMapper;
import be.kdg.prog6.thebakery.factory.domain.recipe.Ingredient;
import be.kdg.prog6.thebakery.factory.ports.out.IngredientLoadPort;
import be.kdg.prog6.thebakery.factory.ports.out.IngredientUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class IngredientDBAdapter implements IngredientLoadPort, IngredientUpdatePort {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper mapper;

    @Autowired
    public IngredientDBAdapter(IngredientRepository ingredientRepository, IngredientMapper mapper) {
        this.ingredientRepository = ingredientRepository;
        this.mapper = mapper;
    }


    @Override
    public Optional<Ingredient> loadIngredient(UUID uuid) {
        log.info("Loading ingredient with uuid: " + uuid);
        Optional<IngredientJpaEntity> ingredientEntity = ingredientRepository.findById(uuid);
        if (ingredientEntity.isEmpty()) {
            log.error("Ingredient with uuid: " + uuid + " not found");
            return Optional.empty();
        }
        return Optional.of(mapper.jpaToDomain(ingredientEntity.get()));
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        log.info("Updating ingredient: " + ingredient);
        ingredientRepository.save(mapper.domainToJpa(ingredient));
    }
}
