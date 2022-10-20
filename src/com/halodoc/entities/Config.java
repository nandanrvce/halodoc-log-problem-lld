package com.halodoc.entities;

import java.time.Duration;

final public class Config {
    private EVENT_TYPE event_type;
    private Integer frequency;
    private Integer durationOfWindow;
    private Integer waiTime;

    public Config(EVENT_TYPE event_type, Integer frequency, Integer durationOfWindow, Integer waiTime) {
        this.event_type = event_type;
        this.frequency = frequency;
        this.durationOfWindow = durationOfWindow;
        this.waiTime = waiTime;
    }

    public EVENT_TYPE getEvent_type() {
        return event_type;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public Integer getDurationOfWindow() {
        return durationOfWindow;
    }

    public Integer getWaiTime() {
        return waiTime;
    }
}
