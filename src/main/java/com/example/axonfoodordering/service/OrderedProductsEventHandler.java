package com.example.axonfoodordering.service;

import com.example.axonfoodordering.events.OrderConfirmedEvent;
import com.example.axonfoodordering.events.OrderPlacedEvent;
import com.example.axonfoodordering.events.OrderShippedEvent;
import com.example.axonfoodordering.exception.OrderNotConfirmedException;
import com.example.axonfoodordering.exception.OrderNotFoundException;
import com.example.axonfoodordering.query.FindAllOrderedProductsQuery;
import com.example.axonfoodordering.query.GetOrderById;
import com.example.axonfoodordering.query.model.OrderStatus;
import com.example.axonfoodordering.query.model.OrderedProduct;
import com.example.axonfoodordering.repository.OrderedProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedProductsEventHandler {

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    @EventHandler
    public void on(OrderPlacedEvent event) {
        String orderId = event.getOrderId();
        OrderedProduct orderedProduct = new OrderedProduct(orderId, event.getProduct());
        orderedProductRepository.save(orderedProduct);
    }

    @EventHandler
    public void on(OrderConfirmedEvent event) {
        orderedProductRepository.findByOrderId(event.getOrderId()).ifPresent(orderedProduct -> {
            orderedProduct.setOrderConfirmed();
            orderedProductRepository.save(orderedProduct);
        });
    }

    @EventHandler
    public void on(OrderShippedEvent event) {
        orderedProductRepository.findByOrderIdAndOrderStatus(event.getOrderId(), OrderStatus.CONFIRMED)
            .ifPresent(orderedProduct -> {
                orderedProduct.setOrderShipped();
                orderedProductRepository.save(orderedProduct);
            });
    }

    @QueryHandler
    public List<OrderedProduct> handle(FindAllOrderedProductsQuery query) {
        return orderedProductRepository.findAll();
    }

    @QueryHandler
    public OrderedProduct handle(GetOrderById query) {
        return orderedProductRepository.findById(query.getOrderId())
            .orElseThrow(() -> new OrderNotFoundException());
    }

}
