package com.office.conference.predicate;

import com.office.conference.dto.Interval;
import com.office.conference.exception.ConferenceRoomMaintenanceException;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

@Component
public class IsNotOverlappedWithMaintenanceSchedule implements BiPredicate<String, String> {
    @Override
    public boolean test(String start, String end) {
        List<Interval> maintenanceWindow = fetchMaintenanceTimings();
        for(Interval interval : maintenanceWindow){
            LocalTime maintenanceStartTime = LocalTime.parse(interval.getStartTime());
            LocalTime maintenanceEndTime = LocalTime.parse(interval.getEndTime());
            if(LocalTime.parse(start).isBefore(maintenanceEndTime) &&
                    LocalTime.parse(end).isAfter(maintenanceStartTime)){
                throw new ConferenceRoomMaintenanceException("Room is under maintenance from "
                        +maintenanceStartTime+" to "+maintenanceEndTime);
            }
        }
        return true;
    }
    private List<Interval> fetchMaintenanceTimings() {
        List<Interval> maintenanceIntervals = new ArrayList<>();
        maintenanceIntervals.add(Interval.builder().startTime("09:00").endTime("09:15").build());
        maintenanceIntervals.add(Interval.builder().startTime("13:00").endTime("13:15").build());
        maintenanceIntervals.add(Interval.builder().startTime("17:00").endTime("17:15").build());
        return maintenanceIntervals;
    }
}
