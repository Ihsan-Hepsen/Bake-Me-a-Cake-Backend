package be.kdg.prog6.thebakery.store.domain;

import java.util.UUID;

public class Customer {

    private final CustomerUUID customerUUID;
    private final String name;
    private final int age;
    private final int loyaltyPoints;

    public record CustomerUUID(UUID uuid) {

    }


    public Customer(String name, int age) {
        this.customerUUID = new CustomerUUID(UUID.randomUUID());
        this.name = name;
        this.age = age;
        this.loyaltyPoints = 0;
    }

    public CustomerUUID customerUUID() {
        return customerUUID;
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }

    public int loyaltyPoints() {
        return loyaltyPoints;
    }
}
