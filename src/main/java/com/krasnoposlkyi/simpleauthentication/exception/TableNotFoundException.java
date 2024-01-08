package com.krasnoposlkyi.simpleauthentication.exception;

import java.util.function.Supplier;

public class TableNotFoundException extends Exception {
    public TableNotFoundException(String message) {
        super(message);
    }
}
