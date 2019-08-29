package com.osadchuk.roman.roombooking.service;

import com.osadchuk.roman.roombooking.entity.Room;
import com.osadchuk.roman.roombooking.entity.RoomStatus;
import com.osadchuk.roman.roombooking.model.OrderDTO;
import com.osadchuk.roman.roombooking.model.RoomDTO;
import com.osadchuk.roman.roombooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//TODO: ADAPTER (DTO -> Entity)

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    private final OrderService orderService;

    @Autowired
    public RoomService(RoomRepository roomRepository, OrderService orderService) {
        this.roomRepository = roomRepository;
        this.orderService = orderService;
    }

    public Optional<Room> findById(long id) {
        return roomRepository.findById(id);
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public List<Room> findAllOrderByNumber() {
        return roomRepository.findAllByOrderByNumber();
    }

    public List<Room> findAllByStatusOrderByNumber(RoomStatus status) {
        return roomRepository.findAllByStatusOrderByNumber(status);
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public Room addRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setNumber(roomDTO.getNumber());
        room.setAmountOfPerson(roomDTO.getAmountOfPerson());
        room.setPrice(roomDTO.getPrice());
        room.setBookingPrice(roomDTO.getBookingPrice());
        room.setStatus(RoomStatus.FREE);
        return roomRepository.save(room);
    }

    public boolean roomExists(int number) {
        return roomRepository.findByNumber(number).isPresent();
    }

    public void deleteById(long id) {
        roomRepository.deleteById(id);
    }

    public List<Room> findSuitableRooms(OrderDTO orderDTO) {
        List<Room> rooms = roomRepository
                .findAllByAmountOfPersonGreaterThanEqualOrderByAmountOfPerson(orderDTO.getAmountOfPerson());
        rooms = rooms.stream()
                .filter(room -> orderService.checkIfRoomWillBeFree(room.getId(), orderDTO))
                .collect(Collectors.toList());
        return rooms;
    }
}
