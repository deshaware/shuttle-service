package com.deshaware.shuttleservice.model;

import java.time.Instant;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

public class Shuttle {
    @Id
    private int shuttle_id;

    @Enumerated(EnumType.STRING)
    private String suttle_type;

    private int capacity;

    private Instant modified;
    private boolean enabled;

}
