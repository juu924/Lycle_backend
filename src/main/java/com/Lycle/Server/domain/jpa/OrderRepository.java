package com.Lycle.Server.domain.jpa;

import com.Lycle.Server.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
