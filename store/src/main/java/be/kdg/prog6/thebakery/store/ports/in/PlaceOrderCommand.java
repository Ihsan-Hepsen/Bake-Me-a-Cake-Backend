package be.kdg.prog6.thebakery.store.ports.in;

import be.kdg.prog6.thebakery.store.domain.Customer;
import be.kdg.prog6.thebakery.store.domain.OrderItem;

import java.util.List;

public record PlaceOrderCommand(Customer.CustomerUUID customerUUID, List<OrderItem> orderItems) {

}
