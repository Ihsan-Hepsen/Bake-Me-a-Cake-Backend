package be.kdg.prog6.thebakery.warehouse.core;

import be.kdg.prog6.thebakery.common.annotations.UseCase;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;
import be.kdg.prog6.thebakery.warehouse.ports.in.FetchOutBoundOrdersQuery;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound.OutBoundOrderLoadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@UseCase
public class DefaultFetchOutBoundOrdersQuery implements FetchOutBoundOrdersQuery {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final OutBoundOrderLoadPort orderLoadPort;

    @Autowired
    public DefaultFetchOutBoundOrdersQuery(OutBoundOrderLoadPort orderLoadPort) {
        this.orderLoadPort = orderLoadPort;
    }

    @Override
    public List<OutBoundOrder> fetchOutBoundOrders() {
        log.info("Fetching all OutBoundOrders");
        return orderLoadPort.loadAllOutBoundOrders().orElseGet(ArrayList::new);
    }

}
