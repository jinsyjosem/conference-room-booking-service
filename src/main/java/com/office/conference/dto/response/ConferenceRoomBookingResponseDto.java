package com.office.conference.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConferenceRoomBookingResponseDto {
    private Long roomId;

    private String startTime;

    private String endTime;

    private String name;

    private int capacity;
}
