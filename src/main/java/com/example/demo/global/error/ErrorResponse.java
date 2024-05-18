package com.example.demo.global.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ErrorResponse {

    private String message;
    private LocalDateTime timeStamp;
    private String description;

    protected ErrorResponse(String message, LocalDateTime timeStamp, String description) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.description = description;
    }

    public static ErrorResponse of(String message, LocalDateTime timeStamp, String description) {
        return new ErrorResponse(message, timeStamp, description);
    }

}
