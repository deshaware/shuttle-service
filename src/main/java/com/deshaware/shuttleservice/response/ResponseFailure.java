package com.deshaware.shuttleservice.response;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResponseFailure extends Response{
    private HttpStatus httpStatus;
    private String status="FAILED";
    private Instant processed_on;
    private Object reason;
}
