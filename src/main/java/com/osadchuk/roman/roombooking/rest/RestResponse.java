package com.osadchuk.roman.roombooking.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response object which are returned in REST API
 * @param <T> - type of entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestResponse<T> {
    private boolean success = true;
    private T data;
    private String error = "";

    public RestResponse(T data) {
        this.data = data;
    }

    public RestResponse(String error) {
        this.error = error;
        this.success = false;
    }
}
