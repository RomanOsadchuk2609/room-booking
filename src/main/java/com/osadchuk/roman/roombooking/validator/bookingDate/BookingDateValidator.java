package com.osadchuk.roman.roombooking.validator.bookingDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Booking date validator implementation
 */
public class BookingDateValidator implements ConstraintValidator<ValidBookingDate, String> {

    public boolean isValid(String date, ConstraintValidatorContext context) {
        LocalDate now = LocalDate.now();
        LocalDate bookingDate = LocalDate.parse(date);
        return bookingDate.compareTo(now) > 0;
    }
}
