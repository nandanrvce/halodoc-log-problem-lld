package com.halodoc.entities;

import java.time.LocalDateTime;

public class ProcessingInfo {
   private EVENT_TYPE event_type;
   private LocalDateTime windowStart;
   private LocalDateTime windowEnd;
   private Integer numberOfEvents;
   private boolean wait;
   private LocalDateTime waitEnd;

    public ProcessingInfo(EVENT_TYPE event_type, LocalDateTime windowStart, LocalDateTime end, Integer numberOfEvents) {
        this.event_type = event_type;
        this.windowStart = windowStart;
        this.windowEnd = end;
        this.numberOfEvents = numberOfEvents;
    }

    public EVENT_TYPE getEvent_type() {
        return event_type;
    }

    public LocalDateTime getWindowStart() {
        return windowStart;
    }

    public LocalDateTime getWindowEnd() {
        return windowEnd;
    }

    public void setEvent_type(EVENT_TYPE event_type) {
        this.event_type = event_type;
    }

    public void setWindowStart(LocalDateTime windowStart) {
        this.windowStart = windowStart;
    }

    public void setWindowEnd(LocalDateTime windowEnd) {
        this.windowEnd = windowEnd;
    }

    public void setNumberOfEvents(Integer numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }

    public Integer getNumberOfEvents() {
        return numberOfEvents;
    }

    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

    public LocalDateTime getWaitEnd() {
        return waitEnd;
    }

    public void setWaitEnd(LocalDateTime waitEnd) {
        this.waitEnd = waitEnd;
    }
}
