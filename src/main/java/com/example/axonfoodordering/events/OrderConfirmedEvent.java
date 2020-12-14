package com.example.axonfoodordering.events;

public class OrderConfirmedEvent {

    private final String orderId;

    public OrderConfirmedEvent(final String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "OrderConfirmedEvent{" +
            "orderId='" + orderId + '\'' +
            '}';
    }
}
