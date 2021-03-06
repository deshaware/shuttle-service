package com.deshaware.shuttleservice.response;

import java.io.Serializable;
import java.time.Instant;

import lombok.Data;

@Data
public class Response implements Serializable {
    private String status ;
    private Instant processed_on=Instant.now();
    private Object message;
    private Object data;
}
