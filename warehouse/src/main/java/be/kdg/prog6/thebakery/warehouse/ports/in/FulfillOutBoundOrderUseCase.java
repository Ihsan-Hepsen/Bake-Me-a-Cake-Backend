package be.kdg.prog6.thebakery.warehouse.ports.in;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;

public interface FulfillOutBoundOrderUseCase {

    void fulfillOutBoundOrder(FulfillOutboundOrderCommand command);

}
