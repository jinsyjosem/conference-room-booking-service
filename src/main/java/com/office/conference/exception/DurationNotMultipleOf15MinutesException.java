package com.office.conference.exception;

public class DurationNotMultipleOf15MinutesException extends RuntimeException{
    public DurationNotMultipleOf15MinutesException() {

        super("Selected duration must be multiple of 15 minutes");
    }
}
