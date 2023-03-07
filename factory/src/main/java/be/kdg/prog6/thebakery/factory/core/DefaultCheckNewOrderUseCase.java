package be.kdg.prog6.thebakery.factory.core;

import be.kdg.prog6.thebakery.common.annotations.UseCase;
import be.kdg.prog6.thebakery.common.events.NewOrderPlacedEvent;
import be.kdg.prog6.thebakery.factory.domain.order.BakingOrder;
import be.kdg.prog6.thebakery.factory.domain.order.OrderStatus;
import be.kdg.prog6.thebakery.factory.domain.order.Product;
import be.kdg.prog6.thebakery.factory.exceptions.NoSuchProductException;
import be.kdg.prog6.thebakery.factory.ports.in.CheckNewOrderUseCase;
import be.kdg.prog6.thebakery.factory.ports.out.BakingOrderUpdatePort;
import be.kdg.prog6.thebakery.factory.ports.out.ProductLoadPort;
import be.kdg.prog6.thebakery.factory.ports.out.RecipeLoadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@UseCase
public class DefaultCheckNewOrderUseCase implements CheckNewOrderUseCase {

    private final List<BakingOrderUpdatePort> bakingOrderUpdatePorts;
    private final ProductLoadPort productLoadPort;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public DefaultCheckNewOrderUseCase(List<BakingOrderUpdatePort> bakingOrderUpdatePorts, ProductLoadPort productLoadPort) {
        this.bakingOrderUpdatePorts = bakingOrderUpdatePorts;
        this.productLoadPort = productLoadPort;
    }


    @Override
    @Transactional
    public void handle(NewOrderPlacedEvent newOrderPlacedEvent) {
        List<BakingOrder> bakingOrders = new ArrayList<>();
        for (UUID id : newOrderPlacedEvent.quantity().keySet()) {
            int quantity = newOrderPlacedEvent.quantity().get(id);
            Optional<Product> product = productLoadPort.loadProduct(new Product.ProductUuid(id));
            if (product.isEmpty()) {
                log.error("Product with id " + id + " not found");
                throw new NoSuchProductException("Product with id" + id + " not found");
            }
            bakingOrders.add(new BakingOrder(newOrderPlacedEvent.customer(),
                    OrderStatus.RECEIVED_NEW,
                    product.get().recipe().recipeUuid().uuid(),
                    quantity));
        }

        if (LocalDateTime.now().getHour() < 22) {
            log.info("Registering order.");
        } else {
            log.info("Order registered for the next day.");
        }

        bakingOrders.stream().forEach(bo -> bakingOrderUpdatePorts.stream()
                .forEach(bop -> bop.updateOrder(bo)));
    }
}
