package com.office.conference.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
@Valid
public class ConferenceRoomBookingRequestDto {
    @NotNull
    @Min(value = 1,message = "Number of people attending should not be empty or zero")
    private Integer count;
    @NotNull(message = "Meeting start time should not be blank or empty")
    private String startTime;
    @NotNull(message = "Meeting end time should not be blank or empty")
    private String endTime;

}
