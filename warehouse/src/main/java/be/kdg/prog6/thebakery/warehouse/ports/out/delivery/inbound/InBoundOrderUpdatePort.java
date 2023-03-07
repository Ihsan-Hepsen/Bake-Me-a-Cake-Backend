package be.kdg.prog6.thebakery.warehouse.ports.out.delivery.inbound;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrder;

public interface InBoundOrderUpdatePort {

    void updateInBoundOrder(InBoundOrder inBoundOrder);

}
