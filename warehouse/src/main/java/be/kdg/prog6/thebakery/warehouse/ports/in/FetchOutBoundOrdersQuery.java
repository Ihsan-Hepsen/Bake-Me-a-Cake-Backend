package be.kdg.prog6.thebakery.warehouse.ports.in;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;

import java.util.List;

public interface FetchOutBoundOrdersQuery {

    List<OutBoundOrder> fetchOutBoundOrders();

}
