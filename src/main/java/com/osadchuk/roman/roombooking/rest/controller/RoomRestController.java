package com.osadchuk.roman.roombooking.rest.controller;

import com.osadchuk.roman.roombooking.entity.Room;
import com.osadchuk.roman.roombooking.repository.RoomRepository;
import com.osadchuk.roman.roombooking.rest.BasicRestController;
import com.osadchuk.roman.roombooking.rest.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for Room API
 */
@RestController
@RequestMapping("/api/rooms")
public class RoomRestController implements BasicRestController<Room> {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomRestController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    @PostMapping
    public RestResponse<Room> create(@RequestBody Room room) {
        return new RestResponse<>(roomRepository.save(room));
    }

    @Override
    @RequestMapping
    public RestResponse<List<Room>> get() {
        return new RestResponse<>(roomRepository.findAll());
    }

    @Override
    @RequestMapping("/{id}")
    public RestResponse<Room> get(@PathVariable long id) {
        return new RestResponse<>(roomRepository.findById(id).orElse(null));
    }

    @Override
    @PutMapping
    public RestResponse<Room> update(@RequestBody Room room) {
        return new RestResponse<>(roomRepository.save(room));
    }

    @Override
    @DeleteMapping
    public RestResponse<Room> delete(@RequestParam long id) {
        roomRepository.deleteById(id);
        return new RestResponse<>();
    }
}
