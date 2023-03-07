package be.kdg.prog6.thebakery.factory.adapters.out.db.product;

import be.kdg.prog6.thebakery.factory.adapters.out.db.recipe.RecipeJpaEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(schema = "fruit_db", name = "products")
public class ProductJpaEntity {

    @Id
    @Column(name = "product_uuid", nullable = false)
    @Type(type = "uuid-char")
    private UUID productUuid;


    @Column(name = "product_name")
    private String name;

    @OneToOne(targetEntity = RecipeJpaEntity.class, cascade = CascadeType.ALL)
    private RecipeJpaEntity recipe;

    public UUID getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(UUID productUuid) {
        this.productUuid = productUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecipe(RecipeJpaEntity recipe) {
        this.recipe = recipe;
    }

    public RecipeJpaEntity getRecipe() {
        return recipe;
    }
}
