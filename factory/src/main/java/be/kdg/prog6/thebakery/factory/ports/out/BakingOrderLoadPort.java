package be.kdg.prog6.thebakery.factory.ports.out;

import be.kdg.prog6.thebakery.factory.domain.order.BakingOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BakingOrderLoadPort {

    Optional<BakingOrder> loadBakingOrder(BakingOrder.BakingOrderUUID orderUUID);

    Optional<List<BakingOrder>> loadAllBakingOrdersForTheDay();
}
