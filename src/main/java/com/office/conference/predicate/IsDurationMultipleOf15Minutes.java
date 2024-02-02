package com.office.conference.predicate;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.util.function.BiPredicate;


@Component
public class IsDurationMultipleOf15Minutes implements BiPredicate<String, String> {

    @Override
    public boolean test(String start, String end) {
        long minutes = Duration.between(LocalTime.parse(start),LocalTime.parse(end)).toMinutes();
        if (minutes % 15 == 0){
            return true;
        }
        return false;
    }
}
