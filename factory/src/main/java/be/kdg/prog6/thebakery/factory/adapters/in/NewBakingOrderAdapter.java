package be.kdg.prog6.thebakery.factory.adapters.in;

import be.kdg.prog6.thebakery.factory.ports.in.CheckNewOrderUseCase;
import be.kdg.prog6.thebakery.common.events.NewOrderPlacedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NewBakingOrderAdapter {

    private final CheckNewOrderUseCase checkNewOrderUseCase;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public NewBakingOrderAdapter(CheckNewOrderUseCase checkNewOrderUseCase) {
        this.checkNewOrderUseCase = checkNewOrderUseCase;
    }

    @EventListener
    public void receiveNewOrderEvent(NewOrderPlacedEvent event) {
        log.info("Handling new order event");
        checkNewOrderUseCase.handle(event);
    }
}
