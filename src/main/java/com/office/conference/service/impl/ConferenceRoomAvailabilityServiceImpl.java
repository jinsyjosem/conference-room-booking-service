package com.office.conference.service.impl;


import com.office.conference.dto.Interval;
import com.office.conference.dto.response.ConferenceRoomSearchResponseDto;
import com.office.conference.exception.ConferenceRoomNotFoundException;
import com.office.conference.exception.DurationNotMultipleOf15MinutesException;
import com.office.conference.predicate.IsDurationMultipleOf15Minutes;
import com.office.conference.predicate.IsNotOverlappedWithMaintenanceSchedule;
import com.office.conference.repository.ConferenceRoomRepository;
import com.office.conference.repository.MeetingRepository;
import com.office.conference.service.ConferenceRoomAvailabilityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConferenceRoomAvailabilityServiceImpl implements ConferenceRoomAvailabilityService {

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    IsDurationMultipleOf15Minutes durationMultipleOf15Minutes;

    @Autowired
    IsNotOverlappedWithMaintenanceSchedule notOverlappedWithMaintenanceSchedule;

    @Override
    public List<ConferenceRoomSearchResponseDto> checkAvailableRooms(String startTime, String endTime){
        if(durationMultipleOf15Minutes.test(startTime, endTime)) {
            if (notOverlappedWithMaintenanceSchedule.test(startTime, endTime)) {
                List<ConferenceRoomSearchResponseDto> availableConferenceRooms =fetchAvailableConferenceRooms(LocalTime.parse(startTime), LocalTime.parse(endTime));
                if(availableConferenceRooms.isEmpty()){
                    throw new ConferenceRoomNotFoundException();
                }
                return availableConferenceRooms;
            }
        }
        throw new DurationNotMultipleOf15MinutesException();
    }

    private List<ConferenceRoomSearchResponseDto> fetchAvailableConferenceRooms(LocalTime startTime, LocalTime endTime) {
        return Optional.ofNullable(conferenceRoomRepository.findAvailableMeetingRooms(startTime,endTime))
                .orElseThrow(ConferenceRoomNotFoundException:: new)
                .stream()
                .map(conferenceRoom -> ConferenceRoomSearchResponseDto
                    .builder()
                    .id(conferenceRoom.getId())
                    .name(conferenceRoom.getName())
                    .capacity(conferenceRoom.getCapacity()).build()).toList();
    }

}
