package be.kdg.prog6.thebakery.warehouse.adapters.out.db.outbound;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutBoundOrderRepository extends JpaRepository<OutBoundOrderJpaEntity, UUID> {
}
