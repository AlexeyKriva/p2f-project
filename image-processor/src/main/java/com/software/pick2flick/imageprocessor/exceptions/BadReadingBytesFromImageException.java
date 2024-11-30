package com.software.pick2flick.imageprocessor.exceptions;

import java.io.IOException;

public class BadReadingBytesFromImageException extends RuntimeException {
    public BadReadingBytesFromImageException(String message) {
        super(message);
    }
}
