package com.office.conference.exception;

public class ConferenceRoomNotFoundException extends RuntimeException {

    public ConferenceRoomNotFoundException() {

        super("Conference room not available for the selected time period");
    }
}
