package be.kdg.prog6.thebakery.factory.adapters.out.db.bakingorder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BakingOrderRepository extends JpaRepository<BakingOrderJpaEntity, UUID> {

    //    @Query("SELECT b FROM BakingOrderJpaEntity b")
    @Query("SELECT b FROM BakingOrderJpaEntity b WHERE HOUR(b.orderDate) < 22 AND DAY(b.orderDate) = DAY(current_date)")
    Optional<List<BakingOrderJpaEntity>> findAllForTheDay();
}
