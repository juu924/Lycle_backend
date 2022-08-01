package com.Lycle.Server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Orders extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private Long addressId;

    @Column(length = 10, nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Long totalPrice;

    @Builder
    public Orders(Long id, Long userId, Long itemId, Long addressId, Integer quantity, Long totalPrice) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.addressId = addressId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

}
