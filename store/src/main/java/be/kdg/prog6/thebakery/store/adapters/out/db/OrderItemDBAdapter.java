package be.kdg.prog6.thebakery.store.adapters.out.db;

import be.kdg.prog6.thebakery.store.adapters.out.mapper.OrderItemMapper;
import be.kdg.prog6.thebakery.store.domain.OrderItem;
import be.kdg.prog6.thebakery.store.domain.SalesOrder;
import be.kdg.prog6.thebakery.store.ports.out.OrderItemLoadPort;
import be.kdg.prog6.thebakery.store.ports.out.OrderItemUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class OrderItemDBAdapter implements OrderItemLoadPort, OrderItemUpdatePort {

    private final OrderItemRepository repository;
    private final OrderItemMapper mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public OrderItemDBAdapter(OrderItemRepository repository, OrderItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Optional<OrderItem> loadOrderItem(UUID uuid) {
        log.info("Attempting to load Order Item: " + uuid + " from DB.");
        Optional<OrderItemJpaEntity> orderItemJpa = repository.findById(uuid);
        if (orderItemJpa.isPresent()) {
            log.info("Order Item load is successful.");
            return Optional.of(mapper.jpaToDomain(orderItemJpa.get()));
        }
        log.warn("NO ORDER ITEM FOR THE GIVEN UUID!");
        return Optional.empty();
    }

    @Override
    public Optional<List<OrderItem>> loadAllOrderItemsBySalesOrderUuid(SalesOrder.SalesOrderUUID uuid) {
        log.info("Attempting to load all Order Items from Sales Order: " + uuid + " from DB.");
        Optional<List<OrderItemJpaEntity>> orderItemJpaEntities = repository.getAllBySalesOrderUuid(uuid.uuid());
        if (orderItemJpaEntities.isPresent()) {
            log.info("All Order Items are loaded successfully.");
            return Optional.of(mapper.jpaListToDomainList(orderItemJpaEntities.get()));
        }
        log.warn("NO ORDER ITEM FOR THE GIVEN SALES ORDER UUID!");
        return Optional.empty();
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        OrderItemJpaEntity orderItemJpa = mapper.domainToJpa(orderItem);
        repository.save(orderItemJpa);
    }
}
