package com.office.conference.service;

import com.office.conference.dto.response.ConferenceRoomBookingResponseDto;

public interface ConferenceRoomBookingService {
    ConferenceRoomBookingResponseDto bookRoom(int count, String startTime, String endTime);
}
