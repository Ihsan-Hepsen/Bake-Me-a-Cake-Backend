package be.kdg.prog6.thebakery.store.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SalesOrder {

    private final SalesOrderUUID orderUuid;
    private final LocalDateTime orderDate;
    private double price;
    private PaymentMethod paymentMethod;
    private Customer.CustomerUUID customerUUID;

    private List<OrderItem> orderItems;

    public record SalesOrderUUID(UUID uuid) {

    }


    public SalesOrder() {
        this.orderUuid = new SalesOrderUUID(UUID.randomUUID());
        this.orderDate = LocalDateTime.now();
        this.price = 0;
        this.paymentMethod = null;
        this.customerUUID = null;
        this.orderItems = new ArrayList<>();
    }

    public SalesOrder(LocalDateTime orderDate, double price, PaymentMethod paymentMethod,
                      Customer.CustomerUUID customerUUID, List<OrderItem> orderItems) {
        this.orderUuid = new SalesOrderUUID(UUID.randomUUID());
        this.orderDate = orderDate;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.customerUUID = customerUUID;
        this.orderItems = orderItems;
    }


    public SalesOrderUUID orderUUID() {
        return orderUuid;
    }

    public LocalDateTime orderPlacedAt() {
        return orderDate;
    }

    public double orderTotal() {
        return price;
    }

    public PaymentMethod paymentMethod() {
        return paymentMethod;
    }

    public Customer.CustomerUUID customer() {
        return customerUUID;
    }

    public List<OrderItem> orderContent() {
        return orderItems;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setPayerUuid(Customer.CustomerUUID payer) {
        this.customerUUID = payer;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
