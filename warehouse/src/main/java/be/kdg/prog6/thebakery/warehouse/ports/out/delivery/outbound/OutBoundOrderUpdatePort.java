package be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;

public interface OutBoundOrderUpdatePort {

    void updateOutBorderOrder(OutBoundOrder outBoundOrder);
}
