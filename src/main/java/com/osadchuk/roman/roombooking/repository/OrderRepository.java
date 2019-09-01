package com.osadchuk.roman.roombooking.repository;

import com.osadchuk.roman.roombooking.entity.Order;
import com.osadchuk.roman.roombooking.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Order JPA repository
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByRoomIdAndStatusIn(long roomId, List<OrderStatus> statuses);

    List<Order> findAllByUserUsername(String username);
}
