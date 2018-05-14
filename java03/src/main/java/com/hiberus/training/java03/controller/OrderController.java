package com.hiberus.training.java03.controller;

import com.hiberus.training.java03.model.Order;
import com.hiberus.training.java03.model.OrderRepository;
import com.hiberus.training.java03.model.Product;
import com.hiberus.training.java03.model.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "/orders/list", method = RequestMethod.GET)
    public List<Order> list() {
        return orderRepository.findAll();
    }

    @RequestMapping( path = "products/{id}/orders", method = RequestMethod.POST)
    public ResponseEntity<?> create(@PathVariable Long id, @RequestBody CreateOrderParam data) {
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()) {
            return new ResponseEntity<Object>("El producto indicado no existe", HttpStatus.NOT_FOUND);
        }

        Order order = new Order();
        order.setProduct(product.get());
        order.setQuantity(data.getQuantity());

        return new ResponseEntity<>(orderRepository.save(order), HttpStatus.OK);
    }
}
