package com.office.conference.service.impl;

import com.office.conference.dto.response.ConferenceRoomBookingResponseDto;
import com.office.conference.entity.ConferenceRoom;
import com.office.conference.entity.Meeting;
import com.office.conference.exception.ConferenceRoomNotFoundException;
import com.office.conference.exception.DurationNotMultipleOf15MinutesException;
import com.office.conference.predicate.IsDurationMultipleOf15Minutes;
import com.office.conference.predicate.IsNotOverlappedWithMaintenanceSchedule;
import com.office.conference.repository.ConferenceRoomRepository;
import com.office.conference.repository.MeetingRepository;
import com.office.conference.service.ConferenceRoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class ConferenceRoomBookingServiceImpl implements ConferenceRoomBookingService {
    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    IsDurationMultipleOf15Minutes durationMultipleOf15Minutes;

    @Autowired
    IsNotOverlappedWithMaintenanceSchedule notOverlappedWithMaintenanceSchedule;

    @Override
    public ConferenceRoomBookingResponseDto bookRoom(int count, String startTime, String endTime){
        if(durationMultipleOf15Minutes.test(startTime, endTime)) {
            if (notOverlappedWithMaintenanceSchedule.test(startTime, endTime)) {
                List<ConferenceRoom> availableConferenceRooms = fetchListOfAvailableRooms(LocalTime.parse(startTime),
                    LocalTime.parse(endTime),count);
                if(!availableConferenceRooms.isEmpty()) {
                    Meeting meeting = new Meeting();
                    meeting.setRoomId(availableConferenceRooms.get(0).getId());
                    meeting.setStartTime(startTime);
                    meeting.setEndTime(endTime);
                    meetingRepository.save(meeting);
                    return ConferenceRoomBookingResponseDto.builder()
                            .roomId(meeting.getRoomId())
                            .name(availableConferenceRooms.get(0).getName())
                            .capacity(availableConferenceRooms.get(0).getCapacity())
                            .startTime(meeting.getStartTime())
                            .endTime(meeting.getEndTime())
                            .build();
                }
                throw new ConferenceRoomNotFoundException();
            }
        }
        throw new DurationNotMultipleOf15MinutesException();
    }

    private List<ConferenceRoom> fetchListOfAvailableRooms(LocalTime startTime, LocalTime endTime,int capacity) {
        return conferenceRoomRepository.findAvailableMeetingRoomsBasedOnCapacity(startTime,endTime,capacity);

    }
}
