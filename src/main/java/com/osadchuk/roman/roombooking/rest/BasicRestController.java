package com.osadchuk.roman.roombooking.rest;

import java.util.List;

/**
 * Interface for RestControllers with basic CRUD methods
 * @param <T> - type of entity
 */
public interface BasicRestController<T> {

    RestResponse<T> create(T entity);

    RestResponse<List<T>> get();

    RestResponse<T> get(long id);

    RestResponse<T> update(T entity);

    RestResponse<T> delete(long id);

}
