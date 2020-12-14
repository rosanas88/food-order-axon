package com.example.axonfoodordering.query.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderedProduct {

    @Id
    private String orderId;

    private String product;

    private OrderStatus orderStatus;

    public OrderedProduct() {

    }

    public OrderedProduct(final String orderId, final String product) {
        this.orderId = orderId;
        this.product = product;
        this.orderStatus = OrderStatus.PLACED;
    }

    public void setOrderConfirmed() {
        this.orderStatus = OrderStatus.CONFIRMED;
    }

    public void setOrderShipped() {
        this.orderStatus = OrderStatus.SHIPPED;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProduct() {
        return product;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
