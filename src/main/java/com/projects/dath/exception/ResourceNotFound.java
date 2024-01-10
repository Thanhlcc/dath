package com.projects.dath.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
    private final String message;
    public ResourceNotFound(String msg) {
        super(msg);
        this.message = msg;
    }
}
