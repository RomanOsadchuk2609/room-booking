package com.osadchuk.roman.roombooking.repository;

import com.osadchuk.roman.roombooking.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order JPA repository
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
