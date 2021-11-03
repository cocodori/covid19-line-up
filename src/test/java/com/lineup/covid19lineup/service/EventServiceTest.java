package com.lineup.covid19lineup.service;

import com.lineup.covid19lineup.constant.EventStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class EventServiceTest {

    private EventService sut;

    @BeforeEach
    void setUp() {
        sut = new EventService();
    }

    @Test
    @DisplayName("검색조건 없이 이벤트 검색하면, 전체 결과를 출력하여 보여준다")
    void givenNoting_whenSearchEvents_thenReturnsEventList() {
        //given

        //when
        List<EventDTO> list = sut.getEvents(null, null, null, null, null);

        //then
        assertThat(list).hasSize(2);

    }

    @Test
    @DisplayName("검색 조건과 함께 이벤트 검색하면 검색된 결과를 출력하여 보여준다")
    void givenSearchParams_whenSearchingEvents_thenReturnsEventList() {
        //given
        Long placeId = 1L;
        String eventName = "오전-운동";
        EventStatus eventStatus = EventStatus.OPENED;
        LocalDateTime eventStartDateTime = LocalDateTime.now();
        LocalDateTime eventEndDateTime = eventStartDateTime.plusHours(1);

        //when
        List<EventDTO> list = sut.getEvents(placeId, eventName, eventStatus, eventStartDateTime, eventEndDateTime);

        //then
        assertThat(list)
                .hasSize(1)
                .allSatisfy(event -> {
                    assertThat(event)
                            .hasFieldOrPropertyWithValue("placeId", placeId)
                            .hasFieldOrPropertyWithValue("eventName", eventName)
                            .hasFieldOrPropertyWithValue("eventStatus", eventStatus);
                    assertThat(event.eventStartDateTime()).isAfterOrEqualTo(eventStartDateTime);
                    assertThat(event.eventEndDateTime()).isBeforeOrEqualTo(eventEndDateTime);
                });

    }

    @Test
    @DisplayName("이벤트ID로 조회하면, 해당 이벤트 정보를 출력하여 보여준다/")
    void givenEventId_whenSearchingExistingEvent_thenReturnsEvent() {
        //given
        long eventId = 2L;
        EventDTO eventDTO = createEventDTO(1L, "오전 운동", true);

        //when
        Optional<EventDTO> result = sut.getEvent(eventId);

        //then
        assertThat(result).hasValue(eventDTO);
    }

    @Test
    @DisplayName("이벤트ID로 조회하면, 빈 정보를 출력한다")
    void givenEventId_whenSearchingNoneExistEvent_thenREturnEmptyOptional() {
        //given
        long eventId = 2L;

        //when
        Optional<EventDTO> result = sut.getEvent(eventId);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("이벤트ID와 정보를 주면 이벤트 정보를 변경하고 true를 반환한다")
    void givenEventIdAndItsInfo_whenModifying_thenModifiesEventAndReturnsTrue() {
        //given
        long eventId  = 1L;
        EventDTO dto = createEventDTO(1L, "오후 운동", false);

        //when
        boolean result = sut.modifyEvent(eventId, dto);

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("이벤트ID를 주지 않으면 이벤트 정보 변경 중단하고 결과를 false로 보여준다")
    void givenNoEventId_whenModifying_thenAbortModifyingAndReturnsFalse() {
        //given
        EventDTO dto = createEventDTO(1L, "오후 운동", false);

        //when
        boolean result = sut.modifyEvent(null, dto);

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("이벤트ID만 주고 변경할 정보를 주지 않으면 이벤트 정보 변경 중단하고 false를 반환한다")
    void givenEventIdOnly_whenModifying_thenAbortModifyingAndReturnsFalse() {
        //given
        long eventId = 1L;

        //when
        boolean result = sut.modifyEvent(eventId, null);

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("이벤트ID를 주면 이벤트 정보를 삭제하고 true 반환한다")
    void givenEventId_whenDeleting_thenDeletesEventAndReturnsTrue() {
        //given
        long eventId = 1L;

        //when
        boolean result = sut.removeEvent(eventId);

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("이벤트ID를 주지 않으면, 삭제 중단하고 결과를 false로 보여준다")
    void givenNothing_whenDeleting_thenAbortsDeletingAndReturnsFalse() {
        //given
        
        //when
        boolean result = sut.removeEvent(null);

        //then
        assertThat(result).isFalse();
    }

    private EventDTO createEventDTO(
            long placeId,
            String eventName,
            boolean isMorning
    ) {
        String hourStart = isMorning ? "09" : "13";
        String hourEnd = isMorning ? "12" : "16";

        return createEventDTO(placeId,
                eventName,
                EventStatus.OPENED,
                LocalDateTime.parse("2021-01-01T%s:00:00".formatted(hourStart)),
                LocalDateTime.parse("2021-01-01T%s:00:00".formatted(hourEnd))
        );
    }

    private EventDTO createEventDTO(
            long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDateTime
    ) {
        return EventDTO.of(
                placeId,
                eventName,
                eventStatus,
                eventStartDateTime,
                eventEndDateTime,
                0
                , 24,
                "마스크 꼭 착용하세요",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}