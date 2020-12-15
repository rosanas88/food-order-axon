package com.example.axonfoodordering.application.rest;

import com.example.axonfoodordering.application.commands.ConfirmOrderCommand;
import com.example.axonfoodordering.application.commands.PlaceOrderCommand;
import com.example.axonfoodordering.application.commands.ShipOrderCommand;
import com.example.axonfoodordering.query.FindAllOrderedProductsQuery;
import com.example.axonfoodordering.query.GetOrderById;
import com.example.axonfoodordering.domain.model.OrderedProduct;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;


    public OrderController(final CommandGateway commandGateway, final QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/place")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String placeOrder() {
        String orderId = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(orderId, "Meal box 3"));
        return orderId;
    }

    @PutMapping("/{orderId}/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmOrder(@PathVariable("orderId") String orderId) {
        commandGateway.send(new ConfirmOrderCommand(orderId));
    }

    @PutMapping("/{orderId}/ship")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void shipOrder(@PathVariable("orderId") String orderId) {
        OrderedProduct orderedProduct = queryGateway
            .query(new GetOrderById(orderId), OrderedProduct.class).join();
        commandGateway.send(new ShipOrderCommand(orderId, orderedProduct.getOrderStatus()));
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderedProduct> findAllOrderedProducts() {
        return queryGateway.query(new FindAllOrderedProductsQuery(),
            ResponseTypes.multipleInstancesOf(OrderedProduct.class))
            .join();
    }

}
