package be.kdg.prog6.thebakery.warehouse.core;

import be.kdg.prog6.thebakery.common.annotations.UseCase;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrder;
import be.kdg.prog6.thebakery.warehouse.ports.in.FetchInBoundOrdersQuery;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.inbound.InBoundOrderLoadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@UseCase
public class DefaultFetchInBoundOrdersQuery implements FetchInBoundOrdersQuery {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final InBoundOrderLoadPort inBoundOrderLoadPort;

    @Autowired
    public DefaultFetchInBoundOrdersQuery(InBoundOrderLoadPort inBoundOrderLoadPort) {
        this.inBoundOrderLoadPort = inBoundOrderLoadPort;
    }

    @Override
    public List<InBoundOrder> fetchInBoundOrders() {
        return inBoundOrderLoadPort.loadAllInBoundOrders().orElseGet(ArrayList::new);
    }
}
