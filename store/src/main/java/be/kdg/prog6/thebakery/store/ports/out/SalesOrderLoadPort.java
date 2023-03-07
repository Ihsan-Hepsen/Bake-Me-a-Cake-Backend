package be.kdg.prog6.thebakery.store.ports.out;

import be.kdg.prog6.thebakery.store.domain.Customer;
import be.kdg.prog6.thebakery.store.domain.SalesOrder;

import java.util.Optional;

public interface SalesOrderLoadPort {

    Optional<SalesOrder> loadSalesOrder(Customer.CustomerUUID customerUUID);
}
