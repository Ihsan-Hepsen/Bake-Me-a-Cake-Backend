package be.kdg.prog6.thebakery.warehouse.adapters.out.db.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockItemRepository extends JpaRepository<StockItemJpaEntity, UUID> {
}
