package be.kdg.prog6.thebakery.warehouse.ports.out.delivery.inbound;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrderItem;

import java.util.Optional;

public interface InBoundOrderItemDBAdapterLoadPort {

    Optional<InBoundOrderItem> loadInBoundOrderItem(InBoundOrderItem.OrderItemUuid uuid);

}
