package be.kdg.prog6.thebakery.store.ports.out;

import be.kdg.prog6.thebakery.store.domain.OrderItem;
import be.kdg.prog6.thebakery.store.domain.SalesOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderItemLoadPort {

    Optional<OrderItem> loadOrderItem(UUID uuid);

    Optional<List<OrderItem>> loadAllOrderItemsBySalesOrderUuid(SalesOrder.SalesOrderUUID uuid);
}
