package com.osadchuk.roman.roombooking.model;
//TODO: PROTOTYPE
/**
 * Interface for implementing Prototype pattern
 * @param <T> - type of object needed to be cloned
 */
public interface Cloneable<T> {
    T clone();
}
