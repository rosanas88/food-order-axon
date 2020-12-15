package com.example.axonfoodordering.domain.events;

public class OrderPlacedEvent {

    private final String orderId;
    private final String product;

    public OrderPlacedEvent(final String orderId, final String product) {
        this.orderId = orderId;
        this.product = product;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "OrderPlacedEvent{" +
            "orderId='" + orderId + '\'' +
            ", product='" + product + '\'' +
            '}';
    }
}
