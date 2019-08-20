package com.osadchuk.roman.roombooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDTO {
    private Integer number;
    private Integer amountOfPerson;
    private Double price;
    private Double bookingPrice;
}
