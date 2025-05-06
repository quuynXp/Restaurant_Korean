package com.connectJPA.repository;

import com.connectJPA.entity.KitchenOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenOrderRepository extends JpaRepository<KitchenOrder, Long> {
}
