package be.kdg.prog6.thebakery.store.ports.out;

import be.kdg.prog6.thebakery.store.domain.SalesOrder;

public interface SalesOrderUpdatePort {

    void updateSalesOrders(SalesOrder order);
}
