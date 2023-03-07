package be.kdg.prog6.thebakery.warehouse.adapters.out.db.outbound;

import be.kdg.prog6.thebakery.warehouse.adapters.out.mapper.OutBoundOrderItemMapper;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrderItem;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound.OutBoundOrderItemLoadPort;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound.OutBoundOrderItemUpdatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OutBoundOrderItemDBAdapter implements OutBoundOrderItemLoadPort, OutBoundOrderItemUpdatePort {

    private final OutBoundOrderItemRepository repository;
    private final OutBoundOrderItemMapper mapper;

    @Autowired
    public OutBoundOrderItemDBAdapter(OutBoundOrderItemRepository repository, OutBoundOrderItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public void updateOutBoundOrderItem(OutBoundOrderItem outBoundOrderItem) {
        repository.save(mapper.domainToJpa(outBoundOrderItem));
    }

    @Override
    public Optional<OutBoundOrderItem> loadOutBoundOrderItem(OutBoundOrderItem.OrderItemUuid uuid) {
        Optional<OutBoundOrderItemJpaEntity> orderItemJpa = repository.findById(uuid.uuid());
        return orderItemJpa.map(mapper::jpaToDomain);
    }
}
