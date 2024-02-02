package com.office.conference.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Interval {
    private String startTime;
    private String endTime;
}
