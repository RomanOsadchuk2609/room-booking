package com.osadchuk.roman.roombooking.rest.controller;

import com.osadchuk.roman.roombooking.entity.Order;
import com.osadchuk.roman.roombooking.repository.OrderRepository;
import com.osadchuk.roman.roombooking.rest.BasicRestController;
import com.osadchuk.roman.roombooking.rest.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for Order API
 */
@RestController
@RequestMapping("/api/orders")
public class OrderRestController implements BasicRestController<Order> {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderRestController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @PostMapping
    public RestResponse<Order> create(@RequestBody Order order) {
        return new RestResponse<>(orderRepository.save(order));
    }

    @Override
    @GetMapping
    public RestResponse<List<Order>> get() {
        return new RestResponse<>(orderRepository.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public RestResponse<Order> get(@PathVariable long id) {
        return new RestResponse<>(orderRepository.findById(id).orElse(null));
    }

    @Override
    @PutMapping
    public RestResponse<Order> update(@RequestBody Order order) {
        return new RestResponse<>(orderRepository.save(order));
    }

    @Override
    @DeleteMapping
    public RestResponse<Order> delete(@RequestParam long id) {
        orderRepository.deleteById(id);
        return new RestResponse<>();
    }
}
