package com.Lycle.Server.controller;

import com.Lycle.Server.dto.BasicResponse;
import com.Lycle.Server.dto.Order.RequestOrderDto;
import com.Lycle.Server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/user/order")
    public ResponseEntity<BasicResponse> makeOrder(@RequestBody RequestOrderDto requestOrderDto){
        BasicResponse orderResponse = BasicResponse.builder()
                .code(HttpStatus.CREATED.value())
                .httpStatus(HttpStatus.CREATED)
                .message("주문이 성공적으로 생성되었습니다.")
                .count(1)
                .result(Collections.singletonList(orderService.makeOrder(requestOrderDto)))
                .build();

        return new ResponseEntity<>(orderResponse, orderResponse.getHttpStatus());
    }
}
