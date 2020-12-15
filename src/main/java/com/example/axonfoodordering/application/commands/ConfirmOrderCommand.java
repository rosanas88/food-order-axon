package com.example.axonfoodordering.application.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ConfirmOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;

    public ConfirmOrderCommand(final String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "ConfirmOrderCommand{" +
            "orderId='" + orderId + '\'' +
            '}';
    }
}
