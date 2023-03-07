package be.kdg.prog6.thebakery.warehouse.adapters.out.mapper;

import be.kdg.prog6.thebakery.warehouse.adapters.out.db.inbound.InBoundOrderJpaEntity;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {InBoundOrderItemMapper.class})
public interface InBoundOrderMapper {

    InBoundOrderMapper INSTANCE = Mappers.getMapper(InBoundOrderMapper.class);


    default InBoundOrderJpaEntity domainToJpa(InBoundOrder inBoundOrder) {
        InBoundOrderJpaEntity inBoundOrderJpa = new InBoundOrderJpaEntity();
        inBoundOrderJpa.setInBoundOrderUuid(inBoundOrder.inBoundOrderUuid().uuid());
        inBoundOrderJpa.setSupplierUuid(inBoundOrder.supplierUuid());
        inBoundOrderJpa.setOrderDate(inBoundOrder.orderDate());
        inBoundOrderJpa.setStatus(inBoundOrder.status());
        inBoundOrderJpa.setOrderList(InBoundOrderItemMapper.INSTANCE.domainListToJpaList(inBoundOrder.orderList()));
        return inBoundOrderJpa;
    }

    @InheritInverseConfiguration
    InBoundOrder jpaToDomain(InBoundOrderJpaEntity inBoundOrderJpa);


    default InBoundOrder.InBoundOrderUUID map(UUID uuid) {
        return new InBoundOrder.InBoundOrderUUID(uuid);
    }

    default UUID map(InBoundOrder.InBoundOrderUUID inBoundOrderUUID) {
        return inBoundOrderUUID.uuid();
    }

}
