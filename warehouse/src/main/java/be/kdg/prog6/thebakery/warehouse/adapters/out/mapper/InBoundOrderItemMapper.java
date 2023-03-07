package be.kdg.prog6.thebakery.warehouse.adapters.out.mapper;

import be.kdg.prog6.thebakery.warehouse.adapters.out.db.inbound.InBoundOrderItemJpaEntity;
import be.kdg.prog6.thebakery.warehouse.adapters.out.db.inbound.InBoundOrderJpaEntity;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrder;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrderItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface InBoundOrderItemMapper {

    InBoundOrderItemMapper INSTANCE = Mappers.getMapper(InBoundOrderItemMapper.class);


    default InBoundOrderItemJpaEntity domainToJpa(InBoundOrderItem orderItem) {
        InBoundOrderItemJpaEntity jpaEntity = new InBoundOrderItemJpaEntity();
        jpaEntity.setInBoundOrderUuid(InBoundOrderMapper.INSTANCE.map(orderItem.inBoundOrderUuid()));
        jpaEntity.setOrderItemUuid(this.map(orderItem.orderItemUuid()));
        jpaEntity.setStockItemUuid(orderItem.stockItemUuid().uuid());
        jpaEntity.setStatus(orderItem.status());
        jpaEntity.setAmount(orderItem.amount());
        return jpaEntity;
    }

    default InBoundOrderItem jpaToDomain(InBoundOrderItemJpaEntity inBoundOrderJpa) {
        return new InBoundOrderItem(
                this.map(inBoundOrderJpa.getOrderItemUuid()),
                InBoundOrderMapper.INSTANCE.map(inBoundOrderJpa.getInBoundOrderUuid()),
                inBoundOrderJpa.getStockItemUuid(),
                inBoundOrderJpa.getAmount(),
                inBoundOrderJpa.getStatus()
        );
    }


    default InBoundOrderItem.OrderItemUuid map(UUID uuid) {
        return new InBoundOrderItem.OrderItemUuid(uuid);
    }

    default UUID map(InBoundOrderItem.OrderItemUuid orderItemUuid) {
        return orderItemUuid.uuid();
    }


    default List<InBoundOrderItemJpaEntity> domainListToJpaList(List<InBoundOrderItem> orderItem) {
        return orderItem.stream().map(this::domainToJpa).collect(Collectors.toList());
    }

    default List<InBoundOrderItem> jpaListToDomainList(List<InBoundOrderItemJpaEntity> inBoundOrderJpa) {
        return inBoundOrderJpa.stream().map(this::jpaToDomain).collect(Collectors.toList());
    }

}
