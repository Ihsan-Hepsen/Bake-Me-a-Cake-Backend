package be.kdg.prog6.thebakery.warehouse.adapters.out.db.inbound;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InBoundOrderRepository extends JpaRepository<InBoundOrderJpaEntity, UUID> {

}
