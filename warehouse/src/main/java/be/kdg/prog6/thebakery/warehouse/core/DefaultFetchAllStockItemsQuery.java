package be.kdg.prog6.thebakery.warehouse.core;

import be.kdg.prog6.thebakery.common.annotations.UseCase;
import be.kdg.prog6.thebakery.warehouse.domain.stock.StockItem;
import be.kdg.prog6.thebakery.warehouse.ports.in.FetchAllStockItemsQuery;
import be.kdg.prog6.thebakery.warehouse.ports.out.stock.StockItemLoadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@UseCase
public class DefaultFetchAllStockItemsQuery implements FetchAllStockItemsQuery {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final StockItemLoadPort stockItemLoadPort;

    @Autowired
    public DefaultFetchAllStockItemsQuery(StockItemLoadPort stockItemLoadPort) {
        this.stockItemLoadPort = stockItemLoadPort;
    }


    @Override
    public List<StockItem> fetchAllStockItems() {
        var stockItems = stockItemLoadPort.loadAllStockItems();
        return stockItems.orElseGet(ArrayList::new);
    }
}
