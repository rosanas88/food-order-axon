package com.example.axonfoodordering.domain.events;

public class OrderShippedEvent {

    private final String orderId;

    public OrderShippedEvent(final String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "OrderShippedEvent{" +
            "orderId='" + orderId + '\'' +
            '}';
    }
}
