package com.osadchuk.roman.roombooking.controller;

import com.osadchuk.roman.roombooking.entity.OrderStatus;
import com.osadchuk.roman.roombooking.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin/orders")
public class OrderManagementController {
    private final OrderService orderService;

    public OrderManagementController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String orderManagement(Model model) {
        model.addAttribute("orders", orderService.findAllAsDTO());
        return "/admin/orders";
    }

    @PostMapping
    public RedirectView updateOrderStatus(@RequestParam long id, @RequestParam OrderStatus status) {
        orderService.updateStatus(id, status);
        return new RedirectView("/admin/orders");
    }

    @GetMapping("/delete")
    public RedirectView deleteOrder(@RequestParam long id) {
        orderService.deleteById(id);
        return new RedirectView("/admin/orders");
    }
}
