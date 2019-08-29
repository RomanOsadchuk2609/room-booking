package com.osadchuk.roman.roombooking.controller;

import com.osadchuk.roman.roombooking.entity.Order;
import com.osadchuk.roman.roombooking.entity.Room;
import com.osadchuk.roman.roombooking.entity.RoomStatus;
import com.osadchuk.roman.roombooking.model.OrderDTO;
import com.osadchuk.roman.roombooking.service.OrderService;
import com.osadchuk.roman.roombooking.service.RoomService;
import com.osadchuk.roman.roombooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {
    private final RoomService roomService;

    private final OrderService orderService;

    private final UserService userService;

    @Autowired
    public BookingController(RoomService roomService, OrderService orderService, UserService userService) {
        this.roomService = roomService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String booking(Model model) {
        model.addAttribute("rooms", roomService.findAllOrderByNumber());
        model.addAttribute("orderDTO", new OrderDTO());
        return "booking";
    }

    @PostMapping
    public String findRooms(Model model, @ModelAttribute("orderDTO") @Valid OrderDTO orderDTO,
                            BindingResult result, WebRequest request, Errors errors) {
        if (!result.hasErrors()) {
            List<Room> suitableRooms = roomService.findSuitableRooms(orderDTO);
            if (!suitableRooms.isEmpty()) {
                model.addAttribute("rooms", suitableRooms);
                orderDTO.setEnableForBooking(true);
            } else {
                model.addAttribute("errorMessage", "There are no suitable rooms");
                model.addAttribute("rooms", roomService.findAllOrderByNumber());
            }
        } else {
            model.addAttribute("rooms", roomService.findAllOrderByNumber());
        }
        model.addAttribute("orderDTO", orderDTO);
        return "booking";
    }

    @PostMapping("/bookRoom")
    public String bookRoom(@AuthenticationPrincipal UserDetails userDetails, Model model,
                           @ModelAttribute("orderDTO") OrderDTO orderDTO, BindingResult result,
                           WebRequest request, Errors errors) {
        Order order = orderService.bookRoom(orderDTO, roomService.findById(orderDTO.getRoomId()),
                userService.findByUsername(userDetails.getUsername()));
        if (order != null) {
            Room bookedRoom = order.getRoom();
            bookedRoom.setStatus(RoomStatus.RESERVED);
            roomService.save(bookedRoom);
            model.addAttribute("successMessage", "Room #" + order.getRoom().getNumber()
                    + " was successfully booked");
        } else {
            model.addAttribute("errorMessage", "Room booking error");
        }
        return booking(model);
    }


}
