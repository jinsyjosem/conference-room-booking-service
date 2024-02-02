package com.office.conference.dto.request;

import lombok.Data;

@Data
public class ConferenceRoomSearchRequestDto {
    private String startTime;

    private String endTime;
}
