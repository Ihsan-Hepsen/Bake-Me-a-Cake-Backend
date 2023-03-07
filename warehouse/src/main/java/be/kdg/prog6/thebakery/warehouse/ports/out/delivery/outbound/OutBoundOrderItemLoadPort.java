package be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrderItem;

import java.util.Optional;

public interface OutBoundOrderItemLoadPort {

    Optional<OutBoundOrderItem> loadOutBoundOrderItem(OutBoundOrderItem.OrderItemUuid uuid);
}
