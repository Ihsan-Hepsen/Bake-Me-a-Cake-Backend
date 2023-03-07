package be.kdg.prog6.thebakery.warehouse.core;

import be.kdg.prog6.thebakery.warehouse.domain.stock.Category;
import be.kdg.prog6.thebakery.warehouse.domain.stock.IngredientType;
import be.kdg.prog6.thebakery.warehouse.domain.stock.StockItem;
import be.kdg.prog6.thebakery.warehouse.domain.stock.Unit;

import be.kdg.prog6.thebakery.warehouse.ports.out.stock.StockItemLoadPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockDefaultFetchStockItemsQueryTest {

    @Mock
    private StockItemLoadPort stockItemLoadPort;
    private static final UUID STOCK_ITEM_UUID = UUID.fromString("5077c8b3-6046-45df-a30c-69da46d2e294");

    @Test
    void fetchInBoundOrder() {
        // Arrange
        StockItem stockItem = new StockItem(new StockItem.StockItemUuid(STOCK_ITEM_UUID));
        stockItem.setIngredientType(IngredientType.FLOUR);
        stockItem.setCategory(Category.DAIRY);
        stockItem.setUnit(Unit.LITERS);
        stockItem.setExpirationDate(LocalDate.of(2023, 12, 22));
        stockItem.setAmount(100);

        // Act
        when(stockItemLoadPort.loadStockItem(new StockItem.StockItemUuid(STOCK_ITEM_UUID))).thenReturn(Optional.of(stockItem));
        StockItem fetchedItem = stockItemLoadPort.loadStockItem(new StockItem.StockItemUuid(STOCK_ITEM_UUID)).orElse(null);

        // Assert
        assertEquals(stockItem, fetchedItem);
    }
}
