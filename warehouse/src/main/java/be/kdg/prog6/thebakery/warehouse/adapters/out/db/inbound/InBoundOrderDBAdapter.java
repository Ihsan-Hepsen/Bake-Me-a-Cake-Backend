package be.kdg.prog6.thebakery.warehouse.adapters.out.db.inbound;

import be.kdg.prog6.thebakery.warehouse.adapters.out.mapper.InBoundOrderMapper;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrder;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.inbound.InBoundOrderLoadPort;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.inbound.InBoundOrderUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InBoundOrderDBAdapter implements InBoundOrderLoadPort, InBoundOrderUpdatePort {

    private final InBoundOrderRepository inBoundOrderRepository;
    private final InBoundOrderMapper orderMapper;
    private final Logger log = LoggerFactory.getLogger(InBoundOrderDBAdapter.class);


    @Autowired
    public InBoundOrderDBAdapter(InBoundOrderRepository inBoundOrderRepository, InBoundOrderMapper orderMapper) {
        this.inBoundOrderRepository = inBoundOrderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Optional<InBoundOrder> loadInBoundOrder(InBoundOrder.InBoundOrderUUID uuid) {
        Optional<InBoundOrderJpaEntity> inBoundOrderJpa = inBoundOrderRepository.findById(uuid.uuid());
        // could be done with : inBoundOrderJpa.map(orderMapper::jpaToDomain);
        if (inBoundOrderJpa.isPresent()) {
            return Optional.of(orderMapper.jpaToDomain(inBoundOrderJpa.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<InBoundOrder>> loadAllInBoundOrders() {
        List<InBoundOrderJpaEntity> inBoundOrderJpa = inBoundOrderRepository.findAll();
        if (inBoundOrderJpa.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(inBoundOrderJpa.stream().map(orderMapper::jpaToDomain).toList());
    }

    @Override
    public void updateInBoundOrder(InBoundOrder inBoundOrder) {
        log.info("Updating in-bound order: " + inBoundOrder.inBoundOrderUuid().uuid());
        inBoundOrderRepository.save(orderMapper.domainToJpa(inBoundOrder));
    }
}
