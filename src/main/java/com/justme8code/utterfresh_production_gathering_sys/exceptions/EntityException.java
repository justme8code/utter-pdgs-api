package com.justme8code.utterfresh_production_gathering_sys.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityException extends RuntimeException {
    private final HttpStatus httpStatus;

    public EntityException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
