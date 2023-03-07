package be.kdg.prog6.thebakery.factory.adapters.out.db.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredientRepository extends JpaRepository<IngredientJpaEntity, UUID> {
}
