package com.projects.dath.exception;

import lombok.Getter;

@Getter
public class UnknownSupplier extends ResourceNotFound {
    private final String name;
    public UnknownSupplier(String msg, String name) {
        super(msg);
        this.name = name;
    }
}
