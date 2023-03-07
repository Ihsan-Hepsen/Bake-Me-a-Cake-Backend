package be.kdg.prog6.thebakery.factory.adapters.out.db.product;

import be.kdg.prog6.thebakery.factory.adapters.out.mapper.ProductMapper;
import be.kdg.prog6.thebakery.factory.domain.order.Product;
import be.kdg.prog6.thebakery.factory.ports.out.ProductLoadPort;
import be.kdg.prog6.thebakery.factory.ports.out.ProductUploadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDBAdapter implements ProductLoadPort, ProductUploadPort {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProductDBAdapter(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public Optional<Product> loadProduct(Product.ProductUuid productUuid) {
        log.info("Loading product with uuid: " + productUuid);
        var productEntity = productRepository.findByProductUuid(productUuid.uuid());
        return productEntity.map(productMapper::jpaToDomain);
    }

    @Override
    public Optional<List<Product>> loadAllProducts() {
        List<ProductJpaEntity> productEntities = productRepository.findAll();
        return Optional.of(productEntities.stream().map(productMapper::jpaToDomain).toList());
    }

    @Override
    public void uploadProduct(Product product) {
        log.info("Uploading product: " + product);
        productRepository.save(productMapper.domainToJpa(product));
    }
}
