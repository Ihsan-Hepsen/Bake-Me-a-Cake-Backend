package be.kdg.prog6.thebakery.factory.domain.order;

import be.kdg.prog6.thebakery.common.annotations.Default;
import be.kdg.prog6.thebakery.factory.domain.recipe.Recipe;

import java.time.LocalDateTime;
import java.util.UUID;

public class BakingOrder {

    private BakingOrderUUID bakingOrderUUID;

    private UUID customerUuid;

    private Recipe.RecipeUuid recipeUuid;

    private LocalDateTime receivedOn;

    private OrderStatus status;

    private int quantity;

    public record BakingOrderUUID(UUID uuid){
    }


    @Default
    public BakingOrder(BakingOrderUUID uuid) {
        this.bakingOrderUUID = uuid;
        this.customerUuid = null;
        this.status = null;
        this.quantity = 0;
        this.recipeUuid = null;
        this.receivedOn = null;
    }

    public BakingOrder(UUID customerUuid, OrderStatus status, UUID recipeUuid, int quantity) {
        this.bakingOrderUUID = new BakingOrderUUID(UUID.randomUUID());
        this.customerUuid = customerUuid;
        this.status = status;
        this.quantity = quantity;
        this.recipeUuid = new Recipe.RecipeUuid(recipeUuid);
        this.receivedOn = LocalDateTime.now();
    }


    public BakingOrderUUID bakingOrderUUID() {
        return bakingOrderUUID;
    }

    public void setBakingOrderUUID(BakingOrderUUID bakingOrderUUID) {
        this.bakingOrderUUID = bakingOrderUUID;
    }

    public UUID customerUuid() {
        return customerUuid;
    }

    public void setCustomerUuid(UUID customerUuid) {
        this.customerUuid = customerUuid;
    }

    public Recipe.RecipeUuid recipeUuid() {
        return recipeUuid;
    }

    public void setRecipeUuid(UUID recipeUuid) {
        this.recipeUuid = new Recipe.RecipeUuid(recipeUuid);
    }

    public LocalDateTime receivedOn() {
        return receivedOn;
    }

    public void setReceivedOn(LocalDateTime receivedOn) {
        this.receivedOn = receivedOn;
    }

    public OrderStatus status() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean isPending() {
        return this.status == OrderStatus.PENDING;
    }

    public boolean isReceived() {
        return this.status == OrderStatus.RECEIVED_NEW;
    }

    public boolean isCompleted() {
        return this.status == OrderStatus.COMPLETED;
    }

    public void completeOrder() {
        this.status = OrderStatus.COMPLETED;
    }

    public void sendOrder() {
        this.status = OrderStatus.PENDING;
    }

    public boolean hasStatus() {
        return this.status != null;
    }

    public int quantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BakingOrder{" +
                "bakingOrderUUID=" + bakingOrderUUID +
                ", customerUuid=" + customerUuid +
                ", recipeUuid=" + recipeUuid +
                ", receivedOn=" + receivedOn +
                ", status=" + status +
                ", quantity=" + quantity +
                '}';
    }
}
