package be.kdg.prog6.thebakery.factory.adapters.out.mapper;

import be.kdg.prog6.thebakery.factory.adapters.out.db.product.ProductJpaEntity;
import be.kdg.prog6.thebakery.factory.domain.order.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {RecipeMapper.class})
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "productUuid", expression = "java(product.productUuid().uuid())")
    @Mapping(target = "name", expression = "java(product.name())")
    ProductJpaEntity domainToJpa(Product product);

    @Mapping(target = "productUuid", expression = "java(new Product.ProductUuid(productJpa.getProductUuid()))")
    @InheritInverseConfiguration
    Product jpaToDomain(ProductJpaEntity productJpa);

}
