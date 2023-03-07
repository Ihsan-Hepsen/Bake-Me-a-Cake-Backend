package be.kdg.prog6.thebakery.warehouse.ports.out.stock;

import be.kdg.prog6.thebakery.warehouse.domain.stock.StockItem;

import java.util.List;
import java.util.Optional;

public interface StockItemLoadPort {

    Optional<StockItem> loadStockItem(StockItem.StockItemUuid uuid);

    Optional<List<StockItem>> loadAllStockItems();
}
