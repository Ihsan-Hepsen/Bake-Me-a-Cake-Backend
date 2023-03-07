package be.kdg.prog6.thebakery.warehouse.core;

import be.kdg.prog6.thebakery.common.annotations.UseCase;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrder;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrderItem;
import be.kdg.prog6.thebakery.warehouse.ports.in.NewInBoundOrderCommand;
import be.kdg.prog6.thebakery.warehouse.ports.in.NewInBoundOrderUseCase;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.inbound.InBoundOrderUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@UseCase
public class DefaultNewInBoundOrderUseCase implements NewInBoundOrderUseCase {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final List<InBoundOrderUpdatePort> updatePorts;

    @Autowired
    public DefaultNewInBoundOrderUseCase(List<InBoundOrderUpdatePort> updatePorts) {
        this.updatePorts = updatePorts;
    }


    @Override
    public void generateInBoundOrder(NewInBoundOrderCommand orderCommand) {
        InBoundOrder inBoundOrder = new InBoundOrder();
        InBoundOrderItem orderItem = new InBoundOrderItem(inBoundOrder.inBoundOrderUuid(), orderCommand.stockItems().stockItemUuid().uuid(), orderCommand.amount());
        inBoundOrder.setOrderList(List.of(orderItem));
        inBoundOrder.setSupplierUuid(UUID.randomUUID());
        updatePorts.forEach(port -> port.updateInBoundOrder(inBoundOrder));
        log.info("New in-bound order generated: " + inBoundOrder.inBoundOrderUuid());
    }
}
