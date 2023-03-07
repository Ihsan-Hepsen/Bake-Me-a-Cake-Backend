package be.kdg.prog6.thebakery.store.adapters.out.db;

import be.kdg.prog6.thebakery.store.domain.OrderItem;
import be.kdg.prog6.thebakery.store.domain.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItemJpaEntity, UUID> {

    Optional<List<OrderItemJpaEntity>> getAllBySalesOrderUuid(UUID salesOrderUuid);
}
