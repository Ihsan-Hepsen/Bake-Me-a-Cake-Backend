package be.kdg.prog6.thebakery.warehouse.adapters.out.db.stock;

import be.kdg.prog6.thebakery.warehouse.adapters.out.mapper.StockItemMapper;
import be.kdg.prog6.thebakery.warehouse.domain.stock.StockItem;
import be.kdg.prog6.thebakery.warehouse.ports.out.stock.StockItemLoadPort;
import be.kdg.prog6.thebakery.warehouse.ports.out.stock.StockItemUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StockItemDBAdapter implements StockItemLoadPort, StockItemUpdatePort {

    private final StockItemRepository repository;
    private final StockItemMapper mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public StockItemDBAdapter(StockItemRepository repository, StockItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Optional<StockItem> loadStockItem(StockItem.StockItemUuid uuid) {
        Optional<StockItemJpaEntity> stockItemJpa = repository.findById(uuid.uuid());
        if (stockItemJpa.isEmpty()) {
            log.debug("Stock item: " + uuid + " does NOT exist!");
            return Optional.empty();
        }
        return Optional.of(mapper.jpaToDomain(stockItemJpa.get()));
    }

    @Override
    public Optional<List<StockItem>> loadAllStockItems() {
        List<StockItemJpaEntity> stockItemJpas = repository.findAll();
        return Optional.of(stockItemJpas.stream()
                .map(mapper::jpaToDomain)
                .toList());
    }

    @Override
    public void updateIngredient(StockItem stockItem) {
        repository.save(mapper.domainToJpa(stockItem));
    }
}
