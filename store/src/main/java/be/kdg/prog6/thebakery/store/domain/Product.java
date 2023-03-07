package be.kdg.prog6.thebakery.store.domain;

import java.util.UUID;

public enum Product {
    DONUT_REGULAR(UUID.fromString("6b026613-450e-4fda-8ea1-7c403e0e9211"), "Donut", .99),
    DONUT_CHOCOLATE(UUID.fromString("99d4b900-5eb1-4c03-bb7f-5146db0c1b44"), "Chocolate Donut", 1.25),
    DONUT_JELLY(UUID.fromString("7d0d7626-78be-459a-b4dd-9a48701088dd"), "Jelly Filled Donut", 1.35),
    BAGEL_REGULAR(UUID.fromString("4a083423-f368-4b1f-bcb0-704580e3c911"), "Bagel", .99),
    BAGEL_BLUEBERRY(UUID.fromString("77cc2a68-d6b1-4546-802d-842fdb4ca584"), "Blueberry Bagel", 1.75),
    BAGEL_CHOCOLATE(UUID.fromString("f162cc82-f559-4aa5-a8ad-38948497d1a5"), "Chocolate Bagel", 1.35);


    private final UUID productUuid;
    private final String productName;
    private final double price;

    Product(UUID productUuid, String productName, double price) {
        this.productUuid = productUuid;
        this.productName = productName;
        this.price = price;
    }


    public static Product fromUuid(UUID productUuid) {
        for (Product p : Product.values()) {
            if (p.productUuid.equals(productUuid)) {
                return p;
            }
        }
        return null;
    }


    public UUID productUuid() {
        return productUuid;
    }

    public String productName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }
}
