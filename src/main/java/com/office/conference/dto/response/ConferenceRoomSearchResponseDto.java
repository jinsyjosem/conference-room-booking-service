package com.office.conference.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConferenceRoomSearchResponseDto {
    private Long id;

    private String name;

    private int capacity;
}
