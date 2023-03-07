package be.kdg.prog6.thebakery.factory.ports.out;

import be.kdg.prog6.thebakery.factory.domain.order.Product;

public interface ProductUploadPort {

    void uploadProduct(Product product);

}
