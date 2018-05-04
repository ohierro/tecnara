package com.hiberus.training.java03.controller;

import com.hiberus.training.java03.model.Order;
import com.hiberus.training.java03.model.OrderRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(path = "/orders/list", method = RequestMethod.GET)
    public List<Order> list() {
        return orderRepository.findAll();
    }
}
