package be.kdg.prog6.thebakery.store.domain;

import be.kdg.prog6.thebakery.common.annotations.Default;
import java.util.UUID;

public class OrderItem {

    private final UUID orderItemUuid;
    private SalesOrder.SalesOrderUUID salesOrderUUID;
    private Product product;
    private int quantity;


    @Default
    public OrderItem(UUID orderItemUuid) {
        this.orderItemUuid = orderItemUuid;
    }


    public OrderItem(SalesOrder.SalesOrderUUID salesOrderUUID, Product product, int quantity) {
        this.orderItemUuid = UUID.randomUUID();
        this.salesOrderUUID = salesOrderUUID;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderItem(Product product, int quantity) {
        this.orderItemUuid = UUID.randomUUID();
        this.salesOrderUUID = null;
        this.product = product;
        this.quantity = quantity;
    }

    public void setSalesOrderUUID(SalesOrder.SalesOrderUUID salesOrderUUID) {
        this.salesOrderUUID = salesOrderUUID;
    }

    public UUID orderItemUuid() {
        return orderItemUuid;
    }

    public SalesOrder.SalesOrderUUID salesOrderUUID() {
        return salesOrderUUID;
    }

    public Product product() {
        return product;
    }

    public int quantity() {
        return quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem: {" +
                "orderItemUuid=" + orderItemUuid +
                ", salesOrderUUID=" + salesOrderUUID +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
