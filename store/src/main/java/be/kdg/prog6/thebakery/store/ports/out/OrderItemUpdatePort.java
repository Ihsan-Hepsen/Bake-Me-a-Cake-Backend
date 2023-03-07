package be.kdg.prog6.thebakery.store.ports.out;

import be.kdg.prog6.thebakery.store.domain.OrderItem;

public interface OrderItemUpdatePort {

    void updateOrderItem(OrderItem orderItem);
}
