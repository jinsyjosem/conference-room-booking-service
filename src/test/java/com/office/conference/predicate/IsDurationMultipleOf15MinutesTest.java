package com.office.conference.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IsDurationMultipleOf15MinutesTest {

    @InjectMocks
    IsDurationMultipleOf15Minutes durationMultipleOf15Minutes;

    @Test
    void testShouldReturnTrue(){
        Assertions.assertTrue(durationMultipleOf15Minutes.test("10:00","10:45"));
    }

    @Test
    void testShouldReturnFalse(){
        Assertions.assertFalse(durationMultipleOf15Minutes.test("10:00","10:55"));
    }
}
