package com.example.axonfoodordering.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class PlaceOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;

    private final String product;

    public PlaceOrderCommand(final String orderId, final String product) {
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
        return "PlaceOrderCommand{" +
            "orderId='" + orderId + '\'' +
            ", product='" + product + '\'' +
            '}';
    }
}
