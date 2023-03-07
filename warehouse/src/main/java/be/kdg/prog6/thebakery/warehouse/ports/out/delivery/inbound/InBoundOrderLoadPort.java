package be.kdg.prog6.thebakery.warehouse.ports.out.delivery.inbound;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrder;

import java.util.List;
import java.util.Optional;

public interface InBoundOrderLoadPort {

    Optional<InBoundOrder> loadInBoundOrder(InBoundOrder.InBoundOrderUUID uuid);

    Optional<List<InBoundOrder>> loadAllInBoundOrders();

}
