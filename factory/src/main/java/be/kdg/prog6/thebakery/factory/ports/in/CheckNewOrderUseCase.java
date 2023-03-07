package be.kdg.prog6.thebakery.factory.ports.in;

import be.kdg.prog6.thebakery.common.events.NewOrderPlacedEvent;
import javax.transaction.Transactional;

public interface CheckNewOrderUseCase {

    @Transactional
    void handle(NewOrderPlacedEvent newOrderPlacedEvent);
}
