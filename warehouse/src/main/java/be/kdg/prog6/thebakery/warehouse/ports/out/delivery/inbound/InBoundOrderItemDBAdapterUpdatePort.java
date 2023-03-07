package be.kdg.prog6.thebakery.warehouse.ports.out.delivery.inbound;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrderItem;

public interface InBoundOrderItemDBAdapterUpdatePort {

    void updateInBoundOrderItem(InBoundOrderItem inBoundOrderItem);

}
