package com.osadchuk.roman.roombooking.entity;

import com.osadchuk.roman.roombooking.model.Cloneable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity which represents order table in DB
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "order", schema = "public")
public class Order implements Cloneable<Order> {
    @GenericGenerator(
            name = "orderSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "orderSequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )

    @Id
    @GeneratedValue(generator = "orderSequenceGenerator")
    private long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="room_id")
    private Room room;

    private int amountOfPerson;
    private Date bookingDate;
    private int bookedDays;
    private boolean includedBreakfast;
    private double price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "user")
    protected List<Order> users;

    @Override
    public Order clone() {
        Order order = new Order();
        order.setId(id);
        order.setUser(user);
        order.setRoom(room);
        order.setAmountOfPerson(amountOfPerson);
        order.setBookingDate(bookingDate);
        order.setBookedDays(bookedDays);
        order.setIncludedBreakfast(includedBreakfast);
        order.setPrice(price);
        order.setStatus(status);
        return order;
    }
}
