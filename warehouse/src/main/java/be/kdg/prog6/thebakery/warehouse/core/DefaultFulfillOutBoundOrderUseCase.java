package be.kdg.prog6.thebakery.warehouse.core;

import be.kdg.prog6.thebakery.common.annotations.UseCase;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OrderItemStatus;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrderItem;
import be.kdg.prog6.thebakery.warehouse.domain.stock.StockItem;
import be.kdg.prog6.thebakery.warehouse.exceptions.NoSuchOutBoundOrderException;
import be.kdg.prog6.thebakery.warehouse.exceptions.OrderAlreadyFulfilledException;
import be.kdg.prog6.thebakery.warehouse.exceptions.StockItemCouldNotBeFoundException;
import be.kdg.prog6.thebakery.warehouse.ports.in.FulfillOutBoundOrderUseCase;
import be.kdg.prog6.thebakery.warehouse.ports.in.FulfillOutboundOrderCommand;
import be.kdg.prog6.thebakery.warehouse.ports.in.NewInBoundOrderCommand;
import be.kdg.prog6.thebakery.warehouse.ports.in.NewInBoundOrderUseCase;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound.OutBoundOrderLoadPort;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound.OutBoundOrderUpdatePort;
import be.kdg.prog6.thebakery.warehouse.ports.out.stock.StockItemLoadPort;
import be.kdg.prog6.thebakery.warehouse.ports.out.stock.StockItemUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UseCase
public class DefaultFulfillOutBoundOrderUseCase implements FulfillOutBoundOrderUseCase {

    private final StockItemLoadPort stockItemLoadPort;
    private final StockItemUpdatePort stockItemUpdatePort;
    private final OutBoundOrderLoadPort orderLoadPort;
    private final List<OutBoundOrderUpdatePort> orderUpdatePorts;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final NewInBoundOrderUseCase newInBoundOrderUseCase;

    @Autowired
    public DefaultFulfillOutBoundOrderUseCase(StockItemLoadPort stockItemLoadPort, StockItemUpdatePort stockItemUpdatePort, OutBoundOrderLoadPort ouuBoundOrderLoadPort,
                                              List<OutBoundOrderUpdatePort> orderUpdatePorts, NewInBoundOrderUseCase newInBoundOrderUseCase) {
        this.stockItemLoadPort = stockItemLoadPort;
        this.stockItemUpdatePort = stockItemUpdatePort;
        this.orderLoadPort = ouuBoundOrderLoadPort;
        this.orderUpdatePorts = orderUpdatePorts;
        this.newInBoundOrderUseCase = newInBoundOrderUseCase;
    }

    private void updateStockItem(StockItem stockItem, double amount) {
        stockItem.decreaseAmount(amount);
        stockItemUpdatePort.updateIngredient(stockItem);
    }

    @Override
    public void fulfillOutBoundOrder(FulfillOutboundOrderCommand command) {
        Optional<OutBoundOrder> order = orderLoadPort.loadOutBoundOrder(command.outBoundOrderUUID().uuid());

        if (order.isEmpty()) {
            log.error("OutBound Order: {} not found", command.outBoundOrderUUID().uuid());
            throw new NoSuchOutBoundOrderException("OutBound Order: " + command.outBoundOrderUUID().uuid() + " not found");
        } else if (order.get().isFulfilled()) {
            log.error("OutBound Order: {} is already fulfilled", command.outBoundOrderUUID().uuid());
            throw new OrderAlreadyFulfilledException("OutBound Order: " + command.outBoundOrderUUID().uuid() + " is already fulfilled");
        }
        order.get().orderList().forEach(orderItem -> {
            Optional<StockItem> stockItem = stockItemLoadPort.loadStockItem(orderItem.stockItemUuid());

            if (stockItem.isPresent()) {
                if (stockItem.get().amount() >= orderItem.amount()) {
                    log.info("Fulfilled the following ingredient: " + stockItem.get().ingredientType() +
                            " for OutBound Order: " + orderItem.stockItemUuid());
                    orderItem.fulfill();
                    updateStockItem(stockItem.get(), orderItem.amount());
                } else {
                    newInBoundOrderUseCase.generateInBoundOrder(new NewInBoundOrderCommand(stockItem.get(), orderItem.amount()));
                }
            } else {
                log.error("Stock item with uuid {} does not exist", stockItem);
                throw new StockItemCouldNotBeFoundException("Stock item with uuid " + stockItem + " does not exist");
            }
        });

        order.get().fulfill();
        orderUpdatePorts.forEach(orderUpdatePort -> orderUpdatePort.updateOutBorderOrder(order.get()));
    }
}
