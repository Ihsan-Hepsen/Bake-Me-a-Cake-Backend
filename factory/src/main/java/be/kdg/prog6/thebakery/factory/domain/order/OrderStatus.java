package be.kdg.prog6.thebakery.factory.domain.order;

/**
 * Order status that can be used
 */
public enum OrderStatus {
    /**
     * Order has been received from store.
     */
    RECEIVED_NEW,
    /**
     * Order has been sent to the warehouse, waiting for ingredients to be baked.
     */
    PENDING,
    /**
     * Order is baked and ready.
     */
    COMPLETED



}
