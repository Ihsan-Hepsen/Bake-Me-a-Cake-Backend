package be.kdg.prog6.thebakery.warehouse.ports.in;

public interface NewInBoundOrderUseCase {

    void generateInBoundOrder(NewInBoundOrderCommand orderCommand);

}
