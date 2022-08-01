package com.Lycle.Server.service;

import com.Lycle.Server.domain.jpa.OrderRepository;
import com.Lycle.Server.dto.Order.RequestOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public Long makeOrder(RequestOrderDto requestOrderDto){
        return orderRepository.save(requestOrderDto.toEntity()).getId();
    }

}
