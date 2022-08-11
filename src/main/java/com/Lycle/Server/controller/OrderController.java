package com.Lycle.Server.controller;

import com.Lycle.Server.config.auth.UserPrincipal;
import com.Lycle.Server.dto.BasicResponse;
import com.Lycle.Server.dto.Order.RequestOrderDto;
import com.Lycle.Server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    //주문 생성
    @PostMapping("/user/order")
    public ResponseEntity<BasicResponse> makeOrder(Authentication authentication, @RequestBody RequestOrderDto requestOrderDto){
        BasicResponse orderResponse = BasicResponse.builder()
                .code(HttpStatus.CREATED.value())
                .httpStatus(HttpStatus.CREATED)
                .message("주문이 성공적으로 생성되었습니다.")
                .count(1)
                .result(Collections.singletonList(orderService.makeOrder(requestOrderDto)))
                .build();

        return new ResponseEntity<>(orderResponse, orderResponse.getHttpStatus());
    }

    //주문 조회
    @GetMapping("/user/order")
    public ResponseEntity<BasicResponse> searchOrder(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication;
        Long userId = userPrincipal.getId();
        BasicResponse searchOrder = BasicResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("주문 조회가 완료되었습니다.")
                .count(orderService.searchOrder(userId).size())
                .result(Collections.singletonList(orderService.searchOrder(userId)))
                .build();

        return new ResponseEntity<>(searchOrder, searchOrder.getHttpStatus());

    }


    //주문 삭제
    @DeleteMapping("/user/order/{id}")
    public ResponseEntity<BasicResponse> deleteOrder(Authentication authentication, @PathVariable Long orderId){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        orderService.deleteOrder(orderId, userPrincipal.getId());
        BasicResponse orderResponse = BasicResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("주문이 취소 되었습니다.")
                .build();

        return new ResponseEntity<>(orderResponse, orderResponse.getHttpStatus());
    }
}
