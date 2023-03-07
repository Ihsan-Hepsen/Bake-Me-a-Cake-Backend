package be.kdg.prog6.thebakery.store.adapters.out.mapper;

import be.kdg.prog6.thebakery.store.adapters.out.db.SalesOrderJpaEntity;
import be.kdg.prog6.thebakery.store.domain.SalesOrder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface SalesOrderMapper {

    SalesOrderMapper INSTANCE = Mappers.getMapper(SalesOrderMapper.class);

    @Mappings({
            @Mapping(target = "orderUuid", expression = "java(salesOrder.orderUUID().uuid())"),
            @Mapping(target = "orderDate", expression = "java(salesOrder.orderPlacedAt())"),
            @Mapping(target = "price", expression = "java(salesOrder.orderTotal())"),
            @Mapping(target = "paymentMethod", expression = "java(salesOrder.paymentMethod())"),
            @Mapping(target = "customer", expression = "java(salesOrder.customer().uuid())")
    })
    SalesOrderJpaEntity domainToJpa(SalesOrder salesOrder);

    @InheritInverseConfiguration
    SalesOrder jpaToDomain(SalesOrderJpaEntity salesOrderJpa);
}
