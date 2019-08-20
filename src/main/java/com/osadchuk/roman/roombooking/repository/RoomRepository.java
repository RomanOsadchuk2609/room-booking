package com.osadchuk.roman.roombooking.repository;

import com.osadchuk.roman.roombooking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * User Room repository
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByNumber(int number);

    List<Room> findAllByOrderByNumber();
}
