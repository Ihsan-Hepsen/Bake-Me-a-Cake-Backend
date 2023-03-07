package be.kdg.prog6.thebakery.warehouse.adapters.in.web;

import be.kdg.prog6.thebakery.warehouse.domain.stock.StockItem;
import be.kdg.prog6.thebakery.warehouse.ports.in.FetchAllStockItemsQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
@CrossOrigin(origins = "*")
public class StockItemsController {

    private final FetchAllStockItemsQuery fetchAllStockItemsQuery;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public StockItemsController(FetchAllStockItemsQuery fetchAllStockItemsQuery) {
        this.fetchAllStockItemsQuery = fetchAllStockItemsQuery;
    }

    @GetMapping("/stock-items")
    public ResponseEntity<List<StockItem>> getAllStockItems() {
        log.info("GET call received: fetching all stock items.");
        var stockItems = fetchAllStockItemsQuery.fetchAllStockItems();
        return new ResponseEntity<>(stockItems, HttpStatus.OK);
    }
}
