package com.office.conference.service;

import com.office.conference.dto.response.ConferenceRoomBookingResponseDto;
import com.office.conference.entity.ConferenceRoom;
import com.office.conference.exception.ConferenceRoomMaintenanceException;
import com.office.conference.exception.DurationNotMultipleOf15MinutesException;
import com.office.conference.predicate.IsDurationMultipleOf15Minutes;
import com.office.conference.predicate.IsNotOverlappedWithMaintenanceSchedule;
import com.office.conference.repository.ConferenceRoomRepository;
import com.office.conference.repository.MeetingRepository;
import com.office.conference.service.impl.ConferenceRoomBookingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ConferenceRoomBookingServiceImplTest {

    @InjectMocks
    ConferenceRoomBookingServiceImpl conferenceRoomBookingService;

    @Mock
    private ConferenceRoomRepository conferenceRoomRepository;

    @Mock
    private MeetingRepository meetingRepository;

    @Mock
    IsDurationMultipleOf15Minutes durationMultipleOf15Minutes;

    @Mock
    IsNotOverlappedWithMaintenanceSchedule notOverlappedWithMaintenanceSchedule;


    @Test
    public void testShouldReturnSuccessAfterBooking(){
        Mockito.when(durationMultipleOf15Minutes.test(Mockito.any(),Mockito.any()))
                .thenReturn(Boolean.TRUE);
        Mockito.when(notOverlappedWithMaintenanceSchedule.test(Mockito.any(),Mockito.any()))
                .thenReturn(Boolean.TRUE);
        Mockito.when(conferenceRoomRepository.findAvailableMeetingRoomsBasedOnCapacity(
                Mockito.any(),Mockito.any(),Mockito.anyInt()
        )).thenReturn(getConferenceRooms());
        ConferenceRoomBookingResponseDto responseDto= conferenceRoomBookingService
                .bookRoom(2,"08:00","09:00");
        Assertions.assertNotNull(responseDto);
    }

    @Test
    public void testShouldReturnDurationExceptionWhileBooking(){
        Mockito.when(durationMultipleOf15Minutes.test(Mockito.any(),Mockito.any()))
                .thenReturn(Boolean.FALSE);
        assertThrows(DurationNotMultipleOf15MinutesException.class,
                () -> conferenceRoomBookingService
                .bookRoom(2,"08:00","09:10"));
    }

    @Test
    public void testShouldReturnOverlapExceptionWhileBooking(){
        Mockito.when(durationMultipleOf15Minutes.test(Mockito.any(),Mockito.any()))
                .thenReturn(Boolean.TRUE);
        Mockito.when(notOverlappedWithMaintenanceSchedule.test(Mockito.any(),Mockito.any()))
                .thenThrow(ConferenceRoomMaintenanceException.class);
        assertThrows(ConferenceRoomMaintenanceException.class,
                () -> conferenceRoomBookingService
                        .bookRoom(2,"08:00","09:15"));
    }

    private List<ConferenceRoom> getConferenceRooms() {
        List<ConferenceRoom> conferenceRooms = new ArrayList<>();
        ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setId(1l);
        conferenceRoom.setCapacity(20);
        conferenceRoom.setName("Room 1");
        conferenceRooms.add(conferenceRoom);
        return conferenceRooms;
    }
}
