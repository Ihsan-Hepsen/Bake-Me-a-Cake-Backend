package be.kdg.prog6.thebakery.factory.adapters.out.mapper;

import be.kdg.prog6.thebakery.factory.adapters.out.db.bakingorder.BakingOrderJpaEntity;
import be.kdg.prog6.thebakery.factory.domain.order.BakingOrder;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BakingOrderMapper {

    BakingOrderMapper INSTANCE = Mappers.getMapper(BakingOrderMapper.class);

    default BakingOrderJpaEntity domainToJpa(BakingOrder bakingOrder) {
        BakingOrderJpaEntity jpaEntity = new BakingOrderJpaEntity();
        jpaEntity.setBakingOrderUuid(bakingOrder.bakingOrderUUID().uuid());
        jpaEntity.setCustomerUuid(bakingOrder.customerUuid());
        jpaEntity.setRecipeUuid(bakingOrder.recipeUuid().uuid());
        jpaEntity.setOrderDate(bakingOrder.receivedOn());
        jpaEntity.setStatus(bakingOrder.status());
        jpaEntity.setQuantity(bakingOrder.quantity());
        return jpaEntity;
    }

    default BakingOrder jpaToDomain(BakingOrderJpaEntity bakingOrderJpa) {
        BakingOrder order = new BakingOrder(this.map(bakingOrderJpa.getBakingOrderUuid()));
        order.setCustomerUuid(bakingOrderJpa.getCustomerUuid());
        order.setRecipeUuid(bakingOrderJpa.getRecipeUuid());
        order.setReceivedOn(bakingOrderJpa.getOrderDate());
        order.setStatus(bakingOrderJpa.getStatus());
        order.setQuantity(bakingOrderJpa.getQuantity());
        return order;
    }

    default BakingOrder.BakingOrderUUID map(UUID uuid) {
        return new BakingOrder.BakingOrderUUID(uuid);
    }

    default UUID map(BakingOrder.BakingOrderUUID uuid) {
        return uuid.uuid();
    }

}
