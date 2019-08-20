package com.osadchuk.roman.roombooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Entity which represents room table in DB
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "room", schema = "public")
public class Room {
    @GenericGenerator(
            name = "roomSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "roomSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )

    @Id
    @GeneratedValue(generator = "roomSequenceGenerator")
    private long id;
    private int number;
    private int amountOfPerson;
    private double price;
    private double bookingPrice;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private List<Order> orders;

    @JsonIgnore
    public boolean isFree(){
        return status.equals(RoomStatus.FREE);
    }
}
