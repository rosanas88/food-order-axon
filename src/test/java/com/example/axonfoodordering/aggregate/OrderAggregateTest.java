package com.example.axonfoodordering.aggregate;

import com.example.axonfoodordering.application.commands.PlaceOrderCommand;
import com.example.axonfoodordering.application.commands.ShipOrderCommand;
import com.example.axonfoodordering.domain.aggregate.OrderAggregate;
import com.example.axonfoodordering.domain.events.OrderConfirmedEvent;
import com.example.axonfoodordering.domain.events.OrderPlacedEvent;
import com.example.axonfoodordering.domain.events.OrderShippedEvent;
import com.example.axonfoodordering.domain.exception.OrderNotConfirmedException;
import com.example.axonfoodordering.domain.model.OrderStatus;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class OrderAggregateTest {

    private FixtureConfiguration<OrderAggregate> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }

    @DisplayName("When handles a command to place order, sends an event.")
    @Test
    void whenHandlePlaceOrderProducesOrderPlacedEvent() {
        String orderId = UUID.randomUUID().toString();
        String product = "Meal box 3";
        fixture.givenNoPriorActivity()
            .when(new PlaceOrderCommand(orderId, product))
            .expectEvents(new OrderPlacedEvent(orderId, product));
    }

    @DisplayName("When ships an unconfirmed order, throws error.")
    @Test
    void whenShipAnUnconfirmedOrderTrowsError() {
        String orderId = UUID.randomUUID().toString();
        String product = "Meal box 3";
        fixture.given(new OrderPlacedEvent(orderId, product))
            .when(new ShipOrderCommand(orderId, OrderStatus.PLACED))
            .expectException(OrderNotConfirmedException.class);
    }

    @DisplayName("When ships a confirmed order, sends an event.")
    @Test
    void whenShipAConfirmedOrderProducesOrderShippedEvent() {
        String orderId = UUID.randomUUID().toString();
        String product = "Meal box 3";

        fixture.given(new OrderPlacedEvent(orderId, product), new OrderConfirmedEvent(orderId))
            .when(new ShipOrderCommand(orderId, OrderStatus.CONFIRMED))
            .expectEvents(new OrderShippedEvent(orderId));
    }
}