package be.kdg.prog6.thebakery.factory.ports.out;

import be.kdg.prog6.thebakery.factory.domain.order.Product;

import java.util.List;
import java.util.Optional;

public interface ProductLoadPort {

    Optional<Product> loadProduct(Product.ProductUuid productUuid);

    Optional<List<Product>> loadAllProducts();
}
