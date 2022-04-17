package com.deshaware.shuttleservice.response;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class Response implements Serializable {
    private String status ;
    private Instant processed_on;
    private Object reason;
    private Object data;
}
