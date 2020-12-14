package com.example.axonfoodordering.commands;

import com.example.axonfoodordering.query.model.OrderStatus;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ShipOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;

    private final OrderStatus orderStatus;

    public ShipOrderCommand(final String orderId, final OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        return "ShipOrderCommand{" +
            "orderId='" + orderId + '\'' +
            '}';
    }
}
