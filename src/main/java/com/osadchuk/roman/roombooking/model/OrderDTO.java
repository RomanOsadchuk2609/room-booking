package com.osadchuk.roman.roombooking.model;

import com.osadchuk.roman.roombooking.entity.OrderStatus;
import com.osadchuk.roman.roombooking.validator.bookingDate.ValidBookingDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private long id;

    private int amountOfPerson;

    @ValidBookingDate
    private String bookingDate;

    private int amountOfDays;

    private boolean includeBreakfast;

    private long roomId;

    private int roomNumber;

    private long userId;

    private String user;

    private String phoneNumber;

    private boolean enableForBooking;

    private OrderStatus status;

    private double price;
}
