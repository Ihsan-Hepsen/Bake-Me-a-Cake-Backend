package be.kdg.prog6.thebakery.store.adapters.out.db;

import be.kdg.prog6.thebakery.store.adapters.out.mapper.OrderItemMapper;
import be.kdg.prog6.thebakery.store.adapters.out.mapper.SalesOrderMapper;
import be.kdg.prog6.thebakery.store.domain.*;
import be.kdg.prog6.thebakery.store.ports.out.SalesOrderLoadPort;
import be.kdg.prog6.thebakery.store.ports.out.SalesOrderUpdatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SalesOrderDBAdapter implements SalesOrderUpdatePort, SalesOrderLoadPort {

    private final SalesOrderRepository salesOrderRepository;
    private final OrderItemRepository orderItemRepository;
    private final SalesOrderMapper salesOrderMapper;
    private final OrderItemMapper orderItemMapper;

    @Autowired
    public SalesOrderDBAdapter(SalesOrderRepository salesOrderRepository, OrderItemRepository orderItemRepository, SalesOrderMapper salesOrderMapper, OrderItemMapper orderItemMapper) {
        this.salesOrderRepository = salesOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.salesOrderMapper = salesOrderMapper;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public void updateSalesOrders(SalesOrder order) {
        SalesOrderJpaEntity salesOrderEntity = salesOrderMapper.domainToJpa(order);
        OrderItemJpaEntity orderItemJpa = orderItemMapper.domainToJpa(order.orderContent().get(0));

        salesOrderEntity.setOrderItems(List.of(orderItemJpa));

//        orderItemJpa.setOrderItemUuid(or);
        orderItemRepository.save(orderItemJpa);
        salesOrderRepository.save(salesOrderEntity);
    }

    @Override
    public Optional<SalesOrder> loadSalesOrder(Customer.CustomerUUID customerUUID) {
        SalesOrderJpaEntity salesOrderEntity = salesOrderRepository.findByCustomer(customerUUID.uuid());
        if (salesOrderEntity == null) {
            return Optional.empty();
        }
        OrderItem orderItem = orderItemMapper.jpaToDomain(salesOrderEntity.getOrderItems().get(0));

        SalesOrder salesOrder = salesOrderMapper.jpaToDomain(salesOrderEntity);
        salesOrder.setOrderItems(List.of(orderItem));
        return Optional.of(salesOrder);
    }
}
