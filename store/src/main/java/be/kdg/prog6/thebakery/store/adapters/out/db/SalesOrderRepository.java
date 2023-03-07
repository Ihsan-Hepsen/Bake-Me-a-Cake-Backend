package be.kdg.prog6.thebakery.store.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SalesOrderRepository  extends JpaRepository<SalesOrderJpaEntity, UUID> {

    SalesOrderJpaEntity findByCustomer(UUID uuid);

}
