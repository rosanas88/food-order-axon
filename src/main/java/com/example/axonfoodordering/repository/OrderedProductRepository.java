package com.example.axonfoodordering.repository;

import com.example.axonfoodordering.query.model.OrderStatus;
import com.example.axonfoodordering.query.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, String> {

    Optional<OrderedProduct> findByOrderIdAndOrderStatus(String orderId, OrderStatus orderStatus);

    Optional<OrderedProduct> findByOrderId(String orderId);

}
