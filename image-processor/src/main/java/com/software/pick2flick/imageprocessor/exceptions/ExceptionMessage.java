package com.software.pick2flick.imageprocessor.exceptions;

public class ExceptionMessage {
    public final static String BAD_READING_BYTES_MESSAGE = "{\"error\": \"Cannot read bytes from uploaded image.\"}";
    public final static String MODEL_NOT_FOUND_MESSAGE = "{\"error\": \"Model %s not found.\"}";
    public final static String UNCLEAR_IMAGE_MESSAGE = "{\"error\": \"This image is unclear. Please try again or " +
            "send another image.\"}";
}