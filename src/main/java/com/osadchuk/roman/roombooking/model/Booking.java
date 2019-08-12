package com.osadchuk.roman.roombooking.model;
import com.osadchuk.roman.roombooking.entity.Order;
import com.osadchuk.roman.roombooking.entity.User;
import com.osadchuk.roman.roombooking.entity.Room;
import org.springframework.stereotype.Component;

//TODO ORDER BUILDER, SINGLETON

/**
 * Builder for order Entity. Using for booking rooms
 */
@Component
public class Booking {
    private static Booking INSTANCE = new Booking();

    private Order order;

    private Booking() {
        createOrder();
    }

    public static Booking getINSTANCE() {
        return INSTANCE;
    }

    public void createOrder() {
        order = new Order();
    }

    public Order getOrder() {
        return order.clone();
    }

    public void setUser(User user) {
        order.setUser(user);
    }

    public void setRoom(Room room){
        order.setRoom(room);
    }
}
