package com.osadchuk.roman.roombooking.service;

import com.osadchuk.roman.roombooking.entity.Room;
import com.osadchuk.roman.roombooking.entity.RoomStatus;
import com.osadchuk.roman.roombooking.model.RoomDTO;
import com.osadchuk.roman.roombooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public List<Room> findAllOrderByNumber() {
        return roomRepository.findAllByOrderByNumber();
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
}
