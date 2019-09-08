package com.osadchuk.roman.roombooking.controller;

import com.osadchuk.roman.roombooking.entity.Room;
import com.osadchuk.roman.roombooking.model.RoomDTO;
import com.osadchuk.roman.roombooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/rooms")
public class RoomManagementController {
    private final RoomService roomService;

    @Autowired
    public RoomManagementController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String rooms(Model model) {
        model.addAttribute("rooms", roomService.findAllOrderByNumber());
        model.addAttribute("newRoom", new RoomDTO());
        return "admin/rooms";
    }

    @PostMapping
    public String addRoom(Model model, @ModelAttribute("newRoom") @Valid RoomDTO room,
                          BindingResult result, WebRequest request, Errors errors) {

        if (!result.hasErrors()) {
            if (!roomService.roomExists(room.getNumber())) {
                Room newRoom = roomService.addRoom(room);
                model.addAttribute("successMessage", "Room #" + newRoom.getNumber() + " was successfully created");
                model.addAttribute("newRoom", new RoomDTO());
            } else {
                model.addAttribute("errorMessage", "Room #" + room.getNumber() + " already exists");
                model.addAttribute("newRoom", room);
            }
        }
        model.addAttribute("rooms", roomService.findAllOrderByNumber());
        return "admin/rooms";
    }

    @GetMapping("/delete")
    public String deleteRoom(Model model, @RequestParam long id) {
        roomService.deleteById(id);
        return rooms(model);
    }
}
