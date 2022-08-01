package com.Lycle.Server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long itemId;
    private Long addressId;
    private Integer quantity;
    private Long totalPrice;

    @Builder
    public Order(Long id, Long userId, Long itemId, Long addressId,Integer quantity, Long totalPrice) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.addressId = addressId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

}
