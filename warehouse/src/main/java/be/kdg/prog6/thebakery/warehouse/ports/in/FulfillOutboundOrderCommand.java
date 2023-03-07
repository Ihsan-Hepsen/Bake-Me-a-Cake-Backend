package be.kdg.prog6.thebakery.warehouse.ports.in;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;

public record FulfillOutboundOrderCommand(OutBoundOrder.OutBoundOrderUUID outBoundOrderUUID) {
}
