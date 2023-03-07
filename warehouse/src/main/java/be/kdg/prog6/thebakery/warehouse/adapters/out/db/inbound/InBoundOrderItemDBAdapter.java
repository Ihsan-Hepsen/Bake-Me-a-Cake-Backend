package be.kdg.prog6.thebakery.warehouse.adapters.out.db.inbound;

import be.kdg.prog6.thebakery.warehouse.adapters.out.mapper.InBoundOrderItemMapper;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrderItem;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.inbound.InBoundOrderItemDBAdapterLoadPort;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.inbound.InBoundOrderItemDBAdapterUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InBoundOrderItemDBAdapter implements InBoundOrderItemDBAdapterLoadPort, InBoundOrderItemDBAdapterUpdatePort {

    private final InBoundOrderItemRepository inBoundOrderItemRepository;
    private final InBoundOrderItemMapper orderItemMapper;
    private final Logger log = LoggerFactory.getLogger(InBoundOrderDBAdapter.class);

    @Autowired
    public InBoundOrderItemDBAdapter(InBoundOrderItemRepository inBoundOrderItemRepository, InBoundOrderItemMapper orderItemMapper) {
        this.inBoundOrderItemRepository = inBoundOrderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public Optional<InBoundOrderItem> loadInBoundOrderItem(InBoundOrderItem.OrderItemUuid uuid) {
        Optional<InBoundOrderItemJpaEntity> inBoundOrderItemJpa = inBoundOrderItemRepository.findById(uuid.uuid());
        return inBoundOrderItemJpa.map(orderItemMapper::jpaToDomain);
    }

    @Override
    public void updateInBoundOrderItem(InBoundOrderItem inBoundOrderItem) {
        log.info("Updating in-bound order item: " + inBoundOrderItem.orderItemUuid().uuid());
        inBoundOrderItemRepository.save(orderItemMapper.domainToJpa(inBoundOrderItem));
    }
}
