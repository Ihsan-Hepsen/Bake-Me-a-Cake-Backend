package be.kdg.prog6.thebakery.warehouse.ports.out.stock;

import be.kdg.prog6.thebakery.warehouse.domain.stock.StockItem;

public interface StockItemUpdatePort {

    void updateIngredient(StockItem stockItem);
}
