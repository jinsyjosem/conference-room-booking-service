package com.office.conference.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ConferenceRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private int capacity;

    @Column
    private String description;
}
