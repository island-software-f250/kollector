package com.islandsoftwaref250.kollector.exceptions;

import java.util.NoSuchElementException;

public class DataNotFoundException extends NoSuchElementException {

    public DataNotFoundException(String message) {
        super(message);
    }
}