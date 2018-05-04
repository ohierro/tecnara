package com.hiberus.training.java03;

import com.hiberus.training.java03.model.Order;
import com.hiberus.training.java03.model.OrderRepository;
import com.hiberus.training.java03.model.Product;
import com.hiberus.training.java03.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Startup implements ApplicationRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setName("Cepillo");
        product.setDescription("Cepillo de dientes azul");

        productRepository.save(product);

        product = new Product();
        product.setId(2L);
        product.setName("Secador");
        product.setDescription("Secador de pelo");

        productRepository.save(product);

        Order order = new Order();
        order.setId(1L);
        order.setQuantity(10);

        orderRepository.save(order);
    }
}
