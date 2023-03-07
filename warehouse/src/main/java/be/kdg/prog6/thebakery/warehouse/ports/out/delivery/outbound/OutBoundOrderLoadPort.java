package be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OutBoundOrderLoadPort {

    Optional<OutBoundOrder> loadOutBoundOrder(UUID uuid);

    Optional<List<OutBoundOrder>> loadAllOutBoundOrders();
}
