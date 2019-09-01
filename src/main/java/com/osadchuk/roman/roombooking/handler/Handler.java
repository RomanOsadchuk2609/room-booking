package com.osadchuk.roman.roombooking.handler;

import org.springframework.stereotype.Component;

/**
 * Chain of Responsibility representation
 * @param <T> - type of object needed to be checked
 */
@Component
public interface Handler<T> {
    void setNext(Handler<T> handler);

    boolean hasNext();

    boolean isValid(T object);

    boolean handle(T object);

    void setMessage(String message);

    String getMessage();
}
