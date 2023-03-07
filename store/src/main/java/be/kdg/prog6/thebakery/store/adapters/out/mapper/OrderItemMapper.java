package be.kdg.prog6.thebakery.store.adapters.out.mapper;

import be.kdg.prog6.thebakery.store.adapters.out.db.OrderItemJpaEntity;
import be.kdg.prog6.thebakery.store.domain.OrderItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    SalesOrderMapper INSTANCE = Mappers.getMapper(SalesOrderMapper.class);

    @Mappings({
            @Mapping(target = "orderItemUuid", expression = "java(orderItem.orderItemUuid())"),
            @Mapping(target = "salesOrderUuid", expression = "java(orderItem.salesOrderUUID().uuid())"),
            @Mapping(target = "product", expression = "java(orderItem.product())"),
            @Mapping(target = "quantity", expression = "java(orderItem.quantity())")
    })
    OrderItemJpaEntity domainToJpa(OrderItem orderItem);

    @InheritInverseConfiguration
    OrderItem jpaToDomain(OrderItemJpaEntity orderItemJpa);

    List<OrderItemJpaEntity> domainListToJpaList(List<OrderItem> orderItems);

    List<OrderItem> jpaListToDomainList(List<OrderItemJpaEntity> orderItemJpaEntities);
}
