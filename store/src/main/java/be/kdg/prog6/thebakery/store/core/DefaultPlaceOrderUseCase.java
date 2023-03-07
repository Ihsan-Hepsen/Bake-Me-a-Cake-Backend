package be.kdg.prog6.thebakery.store.core;

import be.kdg.prog6.thebakery.common.annotations.UseCase;
import be.kdg.prog6.thebakery.store.domain.*;
import be.kdg.prog6.thebakery.store.ports.in.PlaceOrderCommand;
import be.kdg.prog6.thebakery.store.ports.in.PlaceOrderUseCase;
import be.kdg.prog6.thebakery.store.ports.out.OrderItemUpdatePort;
import be.kdg.prog6.thebakery.store.ports.out.SalesOrderUpdatePort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;

@UseCase
public class DefaultPlaceOrderUseCase implements PlaceOrderUseCase {

    private final List<SalesOrderUpdatePort> salesOrderUpdatePorts;
    private final List<OrderItemUpdatePort> orderItemUpdatePorts;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public DefaultPlaceOrderUseCase(List<SalesOrderUpdatePort> salesOrderUpdatePorts, List<OrderItemUpdatePort> orderItemUpdatePorts) {
        this.salesOrderUpdatePorts = salesOrderUpdatePorts;
        this.orderItemUpdatePorts = orderItemUpdatePorts;
    }

    @Override
    @Transactional
    public void placeOrder(PlaceOrderCommand command) {
        List<OrderItem> orderItems = command.orderItems();
        double price = orderItems.stream().mapToDouble(orderItem -> orderItem.quantity() * orderItem.product().getPrice()).sum();

        SalesOrder so = new SalesOrder();
        orderItems.forEach(oi -> oi.setSalesOrderUUID(so.orderUUID()));
        so.setPaymentMethod(PaymentMethod.CASH); // store doesn't accept credit card :)
        so.setPayerUuid(command.customerUUID());
        so.setOrderItems(orderItems);
        so.setPrice(price);

        log.info("Order placed by: " + command.customerUUID() + " for: " + orderItems);
        salesOrderUpdatePorts.stream().forEach(sup -> sup.updateSalesOrders(so));
        orderItems.stream()
                .forEach(oi -> orderItemUpdatePorts.stream()
                        .forEach(oiup -> oiup.updateOrderItem(oi)));
    }
}
