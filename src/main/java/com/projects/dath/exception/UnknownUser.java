package com.projects.dath.exception;

public class UnknownUser extends RuntimeException {
    public UnknownUser(String msg) {
        super(msg);
    }
}
