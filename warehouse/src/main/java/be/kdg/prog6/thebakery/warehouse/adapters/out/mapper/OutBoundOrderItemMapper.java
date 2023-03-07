package be.kdg.prog6.thebakery.warehouse.adapters.out.mapper;

import be.kdg.prog6.thebakery.warehouse.adapters.out.db.outbound.OutBoundOrderItemJpaEntity;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrderItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OutBoundOrderItemMapper {

    OutBoundOrderItemMapper INSTANCE = Mappers.getMapper(OutBoundOrderItemMapper.class);

    @Mapping(target = "orderItemUuid", expression = "java(outBoundOrderItem.orderItemUuid().uuid())")
    @Mapping(target = "outBoundOrderUuid", expression = "java(outBoundOrderItem.outBoundOrderUuid().uuid())")
    @Mapping(target = "stockItemUuid", expression = "java(outBoundOrderItem.stockItemUuid().uuid())")
    @Mapping(target = "amount", expression = "java(outBoundOrderItem.amount())")
    @Mapping(target = "status", expression = "java(outBoundOrderItem.status())")
    OutBoundOrderItemJpaEntity domainToJpa(OutBoundOrderItem outBoundOrderItem);

    @InheritInverseConfiguration
    OutBoundOrderItem jpaToDomain(OutBoundOrderItemJpaEntity outBoundOrderItemJpa);

    default UUID map(OutBoundOrderItem.OrderItemUuid value) {
        return value.uuid();
    }

    default OutBoundOrderItem.OrderItemUuid map(UUID value) {
        return new OutBoundOrderItem.OrderItemUuid(value);
    }

    default List<OutBoundOrderItemJpaEntity> domainListToJpaList(List<OutBoundOrderItem> outBoundOrderItems) {
        return outBoundOrderItems.stream()
                .map(oi -> {
                    OutBoundOrderItemJpaEntity oij = new OutBoundOrderItemJpaEntity();
                    oij.setAmount(oi.amount());
                    oij.setOrderItemUuid(oi.orderItemUuid().uuid());
                    oij.setOutBoundOrderUuid(oi.outBoundOrderUuid().uuid());
                    oij.setStockItemUuid(oi.stockItemUuid().uuid());
                    oij.setStatus(oi.status());
                    return oij;
                })
                .collect(Collectors.toList());
    }

    List<OutBoundOrderItem> jpaListToDomainList(List<OutBoundOrderItemJpaEntity> outBoundOrderItemJpaEntities);
}
