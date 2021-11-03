package com.lineup.covid19lineup.service;

import com.lineup.covid19lineup.constant.EventStatus;
import lombok.extern.java.Log;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class EventService {
    public List<EventDTO> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDateTime
    ) {
        return List.of();
    }

    public Optional<EventDTO> getEvent(Long eventId) {
        return Optional.empty();
    }

    public boolean createEvent(EventDTO eventDTO) {
        return false;
    }

    public boolean modifyEvent(Long eventId, EventDTO eventDTO) {
        return false;
    }

    public boolean removeEvent(Long eventId) {
        return false;
    }
}
