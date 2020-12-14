package com.example.axonfoodordering.query;

public class GetOrderById {

    private final String orderId;

    public GetOrderById(final String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
