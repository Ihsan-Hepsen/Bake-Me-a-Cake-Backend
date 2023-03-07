package be.kdg.prog6.thebakery.warehouse.ports.in;

import be.kdg.prog6.thebakery.warehouse.domain.stock.StockItem;

import java.util.List;

public record NewInBoundOrderCommand(StockItem stockItems, double amount) {

}
