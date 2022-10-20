package com.halodoc.entities;

import java.time.LocalDateTime;

final public class LogRecord {
    private final EVENT_TYPE type;
    private final LocalDateTime timestamp;
    private final String message;

    public LogRecord(EVENT_TYPE type, LocalDateTime timestamp, String message) {
        this.type = type;
        this.timestamp = timestamp;
        this.message = message;
    }

    public EVENT_TYPE getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
