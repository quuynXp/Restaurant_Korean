package com.connectJPA.repository;

import com.connectJPA.entity.Order;
import com.connectJPA.entity.RestaurantTable;
import com.connectJPA.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByStatus(OrderStatus status);

    Optional<Order> findByTableAndStatus(RestaurantTable table, OrderStatus status);
}
