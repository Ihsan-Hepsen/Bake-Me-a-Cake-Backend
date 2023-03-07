package be.kdg.prog6.thebakery.factory.adapters.out.db.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, UUID> {

    Optional<ProductJpaEntity> findByProductUuid(UUID productUuid);
}
