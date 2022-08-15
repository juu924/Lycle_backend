package com.Lycle.Server.domain.jpa;

import com.Lycle.Server.domain.Orders;
import com.Lycle.Server.dto.Order.SearchOrderWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query(value = "select * from (select o.quantity, o.total_price totalPrice from orders o where o.id=:userId"
            +"union select i.name, i.price, i.store from item i where i.id=o.item_id)"+
            "order by o.created_date desc", nativeQuery = true)
    List<SearchOrderWrapper>findAllByUserIdOrderByCreatedDateDesc(Long userId);

    @Query(value = "select * from(select o.quantity, o.total_price totalPrice, " +
            "from orders o where o.item_id=id union " +
            "select i.name, i.price, i.store from item i where i.id=id)", nativeQuery = true)
    Optional<SearchOrderWrapper> findOrdersById(Long id);

    @Override
    Optional<Orders> findById(Long id);
}
