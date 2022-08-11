package com.Lycle.Server.domain.jpa;

import com.Lycle.Server.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {
}
