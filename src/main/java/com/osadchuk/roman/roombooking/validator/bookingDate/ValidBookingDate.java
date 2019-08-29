package com.osadchuk.roman.roombooking.validator.bookingDate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Booking date validation annotation
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = BookingDateValidator.class)
@Documented
public @interface ValidBookingDate {
    String message() default "You can book a room only from tomorrow or later";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
