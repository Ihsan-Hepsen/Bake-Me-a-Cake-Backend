package be.kdg.prog6.thebakery.factory.adapters.out.db.ingredient;

import be.kdg.prog6.thebakery.factory.domain.recipe.IngredientType;
import be.kdg.prog6.thebakery.factory.domain.recipe.IngredientUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(schema = "fruit_db", name = "ingredient")
public class IngredientJpaEntity {

    public IngredientJpaEntity() {

    }

    public IngredientJpaEntity(UUID ingredientUuid) {
        this.ingredientUuid = ingredientUuid;
    }

    @Id
    @Type(type = "uuid-char")
    private UUID ingredientUuid;

    @Column
    @Enumerated(value = EnumType.STRING)
    private IngredientType type;

    @Column
    @Enumerated(value = EnumType.STRING)
    private IngredientUnit unit;

    @Column
    private double amount;



    public UUID getIngredientUuid() {
        return ingredientUuid;
    }

    public void setIngredientUuid(UUID ingredientUuid) {
        this.ingredientUuid = ingredientUuid;
    }

    public IngredientType getType() {
        return type;
    }

    public void setType(IngredientType type) {
        this.type = type;
    }

    public IngredientUnit getUnit() {
        return unit;
    }

    public void setUnit(IngredientUnit unit) {
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
