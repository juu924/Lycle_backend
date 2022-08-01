package com.Lycle.Server.dto.Order;

import com.Lycle.Server.domain.Orders;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RequestOrderDto {
    private Long userId;
    private Long itemId;
    private Long addressId;
    private Integer quantity;
    private Long totalPrice;

    public Orders toEntity(){
        Orders order = Orders.builder()
                .userId(userId)
                .itemId(itemId)
                .addressId(addressId)
                .quantity(quantity)
                .totalPrice(totalPrice)
                .build();
        return order;
    }

    @Builder
    public RequestOrderDto(Long userId, Long itemId, Long addressId, int quantity, Long totalPrice){
        this.userId = userId;
        this.itemId = itemId;
        this.addressId = addressId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }


}
