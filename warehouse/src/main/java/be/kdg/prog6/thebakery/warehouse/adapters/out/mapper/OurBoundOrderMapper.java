package be.kdg.prog6.thebakery.warehouse.adapters.out.mapper;

import be.kdg.prog6.thebakery.warehouse.adapters.out.db.outbound.OutBoundOrderJpaEntity;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {OutBoundOrderItemMapper.class})
public interface OurBoundOrderMapper {

    OurBoundOrderMapper INSTANCE = Mappers.getMapper(OurBoundOrderMapper.class);

    default OutBoundOrderJpaEntity domainToJpa(OutBoundOrder outBoundOrder) {
        OutBoundOrderJpaEntity jpa = new OutBoundOrderJpaEntity();
        jpa.setOrderUuid(outBoundOrder.outBoundOrderUUID().uuid());
        jpa.setOrderList(OutBoundOrderItemMapper.INSTANCE.domainListToJpaList(outBoundOrder.orderList()));
        jpa.setOrderDate(outBoundOrder.orderDate());
        jpa.setStatus(outBoundOrder.status());
        return jpa;
    }

    default OutBoundOrder jpaToDomain(OutBoundOrderJpaEntity outBoundOrderJpa) {
        OutBoundOrder domain = new OutBoundOrder(outBoundOrderJpa.getOrderUuid());
        domain.setOrderList(OutBoundOrderItemMapper.INSTANCE.jpaListToDomainList(outBoundOrderJpa.getOrderList()));
        domain.setOrderDate(outBoundOrderJpa.getOrderDate());
        domain.setStatus(outBoundOrderJpa.getStatus());
        return domain;
    }


    default OutBoundOrder.OutBoundOrderUUID map(UUID uuid) {
        return new OutBoundOrder.OutBoundOrderUUID(uuid);
    }


}
