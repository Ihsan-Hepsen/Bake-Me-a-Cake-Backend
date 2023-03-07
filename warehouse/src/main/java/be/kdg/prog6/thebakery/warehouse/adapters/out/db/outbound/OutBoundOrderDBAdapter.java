package be.kdg.prog6.thebakery.warehouse.adapters.out.db.outbound;

import be.kdg.prog6.thebakery.warehouse.adapters.out.mapper.OurBoundOrderMapper;
import be.kdg.prog6.thebakery.warehouse.adapters.out.mapper.OutBoundOrderItemMapper;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound.OutBoundOrderLoadPort;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound.OutBoundOrderUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OutBoundOrderDBAdapter implements OutBoundOrderUpdatePort, OutBoundOrderLoadPort {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final OurBoundOrderMapper mapper;
    private final OutBoundOrderItemMapper orderItemMapper;
    private final OutBoundOrderRepository outBoundOrderRepository;
    private final OutBoundOrderItemRepository outBoundOrderItemRepository;

    @Autowired
    public OutBoundOrderDBAdapter(OurBoundOrderMapper mapper, OutBoundOrderItemMapper orderItemMapper, OutBoundOrderRepository outBoundOrderRepository, OutBoundOrderItemRepository outBoundOrderItemRepository) {
        this.mapper = mapper;
        this.orderItemMapper = orderItemMapper;
        this.outBoundOrderRepository = outBoundOrderRepository;
        this.outBoundOrderItemRepository = outBoundOrderItemRepository;
    }

    @Override
    public Optional<OutBoundOrder> loadOutBoundOrder(UUID uuid) {
        Optional<OutBoundOrderJpaEntity> outBoundOrderJpa = outBoundOrderRepository.findById(uuid);
        if (outBoundOrderJpa.isPresent()) {
            return Optional.of(mapper.jpaToDomain(outBoundOrderJpa.get()));
        }
        log.warn("Failed to load out-bound order: " + uuid);
        return Optional.empty();
    }

    @Override
    public Optional<List<OutBoundOrder>> loadAllOutBoundOrders() {
        List<OutBoundOrderJpaEntity> orderJpas = outBoundOrderRepository.findAll();
        var list = orderJpas.stream().map(mapper::jpaToDomain).toList();
        return Optional.of(list);
    }

    @Override
    public void updateOutBorderOrder(OutBoundOrder outBoundOrder) {
        outBoundOrderRepository.save(mapper.domainToJpa(outBoundOrder));
    }
}
