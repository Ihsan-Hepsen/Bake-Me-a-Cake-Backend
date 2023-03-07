package be.kdg.prog6.thebakery.factory.ports.out;

import be.kdg.prog6.thebakery.factory.domain.order.BakingOrder;

public interface BakingOrderUpdatePort {

    void updateOrder(BakingOrder bakingOrder);
}
