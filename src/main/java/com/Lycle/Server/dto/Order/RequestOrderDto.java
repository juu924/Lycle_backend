package com.Lycle.Server.dto.Order;

import com.Lycle.Server.domain.Orders;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestOrderDto {
    private Long userId;
    private Long itemId;
    private String address;
    private String telephone;
    private Integer quantity;
    private Long totalPrice;

    public Orders toEntity(){
        Orders order = Orders.builder()
                .userId(userId)
                .itemId(itemId)
                .address(address)
                .telephone(telephone)
                .quantity(quantity)
                .totalPrice(totalPrice)
                .build();
        return order;
    }

    @Builder
    public RequestOrderDto(Long userId, Long itemId, String address, String telephone,int quantity, Long totalPrice){
        this.userId = userId;
        this.itemId = itemId;
        this.address = address;
        this.telephone = telephone;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }


}
