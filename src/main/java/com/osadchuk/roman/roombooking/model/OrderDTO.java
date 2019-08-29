package com.osadchuk.roman.roombooking.model;

import com.osadchuk.roman.roombooking.validator.bookingDate.ValidBookingDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private int amountOfPerson;

    @ValidBookingDate
    private String bookingDate;

    private int amountOfDays;

    private boolean includeBreakfast;

    private long roomId;

    private boolean enableForBooking;
}
