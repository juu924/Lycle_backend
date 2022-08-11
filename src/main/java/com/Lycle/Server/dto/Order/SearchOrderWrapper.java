package com.Lycle.Server.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchOrderWrapper {
    private int quantity;
    private Long totalPrice;
    private String store;
    private String name;
    private Long price;
}
