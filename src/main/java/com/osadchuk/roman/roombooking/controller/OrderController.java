package com.osadchuk.roman.roombooking.controller;

import com.osadchuk.roman.roombooking.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String orders(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("orders", orderService.findAllByUsernameAsDTO(userDetails.getUsername()));
        return "orders";
    }

    @GetMapping("/delete")
    public String deleteOrder(@RequestParam long id, @AuthenticationPrincipal UserDetails userDetails,
                              Model model){
        orderService.deleteById(id);
        return orders(userDetails, model);
    }
}
