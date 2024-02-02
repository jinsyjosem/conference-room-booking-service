package com.office.conference.service;

import com.office.conference.dto.response.ConferenceRoomSearchResponseDto;

import com.office.conference.entity.Meeting;

import java.util.List;

public interface ConferenceRoomAvailabilityService {

    List<ConferenceRoomSearchResponseDto> checkAvailableRooms(String startTime, String endTime);
    //Meeting bookRoom(int count, String startTime, String endTime);
}
