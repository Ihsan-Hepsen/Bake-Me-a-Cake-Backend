package be.kdg.prog6.thebakery.factory.adapters.out.db.bakingorder;

import be.kdg.prog6.thebakery.factory.adapters.out.mapper.BakingOrderMapper;
import be.kdg.prog6.thebakery.factory.domain.order.BakingOrder;
import be.kdg.prog6.thebakery.factory.ports.out.BakingOrderLoadPort;
import be.kdg.prog6.thebakery.factory.ports.out.BakingOrderUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BakingOrderDBAdapter implements BakingOrderLoadPort, BakingOrderUpdatePort {

    private final BakingOrderRepository repository;
    private final BakingOrderMapper mapper;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public BakingOrderDBAdapter(BakingOrderRepository repository, BakingOrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<BakingOrder> loadBakingOrder(BakingOrder.BakingOrderUUID orderUUID) {
        Optional<BakingOrderJpaEntity> bakingOrderJpa = repository.findById(orderUUID.uuid());
        return bakingOrderJpa.map(mapper::jpaToDomain);
    }

    @Override
    public Optional<List<BakingOrder>> loadAllBakingOrdersForTheDay() {
        Optional<List<BakingOrderJpaEntity>> orderJpaEntities = repository.findAllForTheDay();
        if (orderJpaEntities.isPresent()) {
            List<BakingOrder> bakingOrders = orderJpaEntities.get().stream()
                    .map(mapper::jpaToDomain).toList();
            log.info("Loaded baking orders: " + bakingOrders);
            return Optional.of(bakingOrders);
        }
        return Optional.empty();
    }

    @Override
    public void updateOrder(BakingOrder bakingOrder) {
        repository.save(mapper.domainToJpa(bakingOrder));
    }
}
