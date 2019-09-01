package com.osadchuk.roman.roombooking.model;

import com.osadchuk.roman.roombooking.entity.Order;
import com.osadchuk.roman.roombooking.entity.OrderStatus;
import com.osadchuk.roman.roombooking.entity.Room;
import com.osadchuk.roman.roombooking.entity.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

//TODO ORDER BUILDER
//TODO ORDER SINGLETON

/**
 * Builder for order Entity. Using for booking rooms
 */
public class Booking {
    private static final double BREAKFAST_PRICE = 5;

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
        order.setStatus(OrderStatus.CREATED);
    }

    public Order getOrder() {
        calculatePrice();
        return order.clone();
    }

    public void setUser(User user) {
        order.setUser(user);
    }

    public void setRoom(Room room) {
        order.setRoom(room);
    }

    public void setBookingDate(String stringDate) {
        LocalDate localDate = LocalDate.parse(stringDate);
        Date bookingDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        order.setBookingDate(bookingDate);
    }

    public void setBookedDays(int amountOfDays) {
        order.setBookedDays(amountOfDays);
    }

    public void includeBreakfast() {
        order.setIncludedBreakfast(true);
    }

    private void calculatePrice() {
        Room room = order.getRoom();
        double priceForBooking = room.getBookingPrice();
        double priceForRoom = room.getPrice() * order.getBookedDays();
        double priceForBreakfast = order.isIncludedBreakfast() ? BREAKFAST_PRICE * order.getBookedDays() : 0;
        order.setPrice(priceForBooking + priceForRoom + priceForBreakfast);
    }

    public void setAmountOfPerson(int amountOfPerson) {
        order.setAmountOfPerson(amountOfPerson);
    }

}
