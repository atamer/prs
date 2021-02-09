package com.imc.prs;

public class InvalidHandException extends RuntimeException {
    public InvalidHandException(String desc) {
        super(desc);
    }
}
