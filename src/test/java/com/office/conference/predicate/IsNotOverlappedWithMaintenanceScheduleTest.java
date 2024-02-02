package com.office.conference.predicate;

import com.office.conference.dto.Interval;
import com.office.conference.exception.ConferenceRoomMaintenanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class IsNotOverlappedWithMaintenanceScheduleTest {

    @InjectMocks
    IsNotOverlappedWithMaintenanceSchedule notOverlappedWithMaintenanceSchedule;

    @Test
    public void testShouldReturnTrueIfNotOverlappedWithMaintenanceTimings(){
        Assertions.assertTrue(notOverlappedWithMaintenanceSchedule.test("08:00","09:00"));
        Assertions.assertTrue(notOverlappedWithMaintenanceSchedule.test("09:15","10:00"));
        Assertions.assertTrue(notOverlappedWithMaintenanceSchedule.test("10:15","11:00"));
        Assertions.assertTrue(notOverlappedWithMaintenanceSchedule.test("13:15","14:00"));
        Assertions.assertTrue(notOverlappedWithMaintenanceSchedule.test("12:15","13:00"));
    }

    @Test
    public void testShouldReturnExceptionIfOverlappedWithMaintenanceTimings(){
        assertThrows(ConferenceRoomMaintenanceException.class,
                () -> notOverlappedWithMaintenanceSchedule.test("09:00","10:00"));
        assertThrows(ConferenceRoomMaintenanceException.class,
                () -> notOverlappedWithMaintenanceSchedule.test("08:30","09:30"));
        assertThrows(ConferenceRoomMaintenanceException.class,
                () -> notOverlappedWithMaintenanceSchedule.test("12:00","14:00"));
        assertThrows(ConferenceRoomMaintenanceException.class,
                () -> notOverlappedWithMaintenanceSchedule.test("12:15","13:15"));
    }

}
