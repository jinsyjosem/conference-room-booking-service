package com.office.conference.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private long roomId;

    @Column
    private String startTime;

    @Column
    private String endTime;

}
