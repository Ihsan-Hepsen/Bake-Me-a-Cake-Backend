package be.kdg.prog6.thebakery.warehouse.ports.in;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrder;

import java.util.List;
public interface FetchInBoundOrdersQuery {

    List<InBoundOrder> fetchInBoundOrders();

}
