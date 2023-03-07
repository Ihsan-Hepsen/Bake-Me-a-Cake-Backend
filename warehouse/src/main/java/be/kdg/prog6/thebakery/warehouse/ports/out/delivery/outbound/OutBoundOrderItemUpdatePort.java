package be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrderItem;

public interface OutBoundOrderItemUpdatePort {

    void updateOutBoundOrderItem(OutBoundOrderItem outBoundOrderItem);
}
