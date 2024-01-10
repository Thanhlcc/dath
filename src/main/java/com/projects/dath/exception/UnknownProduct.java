package com.projects.dath.exception;

import lombok.Getter;

@Getter
public class UnknownProduct extends ResourceNotFound{
    private final String id;

    public UnknownProduct(String msg, String id) {
        super(msg);
        this.id = id;
    }
}
