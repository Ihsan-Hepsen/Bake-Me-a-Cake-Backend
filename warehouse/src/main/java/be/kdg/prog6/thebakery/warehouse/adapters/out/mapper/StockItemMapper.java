package be.kdg.prog6.thebakery.warehouse.adapters.out.mapper;

import be.kdg.prog6.thebakery.warehouse.adapters.out.db.stock.StockItemJpaEntity;
import be.kdg.prog6.thebakery.warehouse.domain.stock.StockItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface StockItemMapper {

    StockItemMapper INSTANCE = Mappers.getMapper(StockItemMapper.class);

    @Mappings({
            @Mapping(target = "stockItemUuid", expression = "java(stockItem.stockItemUuid().uuid())"),
            @Mapping(target = "ingredientType", expression = "java(stockItem.ingredientType())"),
            @Mapping(target = "unit", expression = "java(stockItem.unit())"),
            @Mapping(target = "category", expression = "java(stockItem.category())"),
            @Mapping(target = "amount", expression = "java(stockItem.amount())"),
            @Mapping(target = "expirationDate", expression = "java(stockItem.expirationDate())"),
    })
    StockItemJpaEntity domainToJpa(StockItem stockItem);

    @InheritInverseConfiguration
    StockItem jpaToDomain(StockItemJpaEntity stockItemJpa);


    default StockItem.StockItemUuid map(UUID uuid) {
        return new StockItem.StockItemUuid(uuid);
    }

    default UUID map(StockItem.StockItemUuid stockItemUuid) {
        return stockItemUuid.uuid();
    }

}
