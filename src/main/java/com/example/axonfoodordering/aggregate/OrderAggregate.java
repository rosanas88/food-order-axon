package com.example.axonfoodordering.aggregate;

import com.example.axonfoodordering.commands.ConfirmOrderCommand;
import com.example.axonfoodordering.commands.PlaceOrderCommand;
import com.example.axonfoodordering.commands.ShipOrderCommand;
import com.example.axonfoodordering.events.OrderConfirmedEvent;
import com.example.axonfoodordering.events.OrderPlacedEvent;
import com.example.axonfoodordering.events.OrderShippedEvent;
import com.example.axonfoodordering.exception.OrderNotConfirmedException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;

    protected OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(PlaceOrderCommand command) {
        apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }


    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        apply(new OrderConfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) {
        if (command.getOrderStatus().isUnconfirmed()) {
            throw new OrderNotConfirmedException();
        }

        apply(new OrderShippedEvent(orderId));
    }

    @EventSourcingHandler
    public void on(OrderPlacedEvent event) {
        this.orderId = event.getOrderId();
    }

}
