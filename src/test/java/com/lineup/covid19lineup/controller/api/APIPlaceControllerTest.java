package com.lineup.covid19lineup.controller.api;

import com.lineup.covid19lineup.constant.ErrorCode;
import com.lineup.covid19lineup.constant.PlaceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(APIPlaceController.class)
class APIPlaceControllerTest {

    private MockMvc mvc;

    public APIPlaceControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[API][GET] 장소 리스트 조회 - 장소 리스트 데이터를 담은 표준 API 출력")
    @Test
    void givenNothing_whenRequestingPlaces_thenReturnsPlacesInStandardResponse() throws Exception {
        //given

        //when
        //then
        mvc.perform(get("/api/places"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].placeType").value(PlaceType.COMMON.name()))
                .andExpect(jsonPath("$.data[0].placeName").value("배트민턴장"))
                .andExpect(jsonPath("$.data[0].address").value("강남구 강남대로1234"))
                .andExpect(jsonPath("$.data[0].phoneNumber").value("010-1234-1234"))
                .andExpect(jsonPath("$.data[0].capacity").value(30))
                .andExpect(jsonPath("$.data[0].memo").value("신장개업"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

    @DisplayName("[API][GET] 단일 장소 조회")
    @Test
    void givenPlaceAndItsId_whenRequestPlace_thenReturnsPlaceInStandardResponse() throws Exception {
        //given
        int placeId = 1;
        //when
        //then
        mvc.perform(get("/api/places/"+ placeId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap() )
                .andExpect(jsonPath("$.data.placeType").value(PlaceType.COMMON.name()))
                .andExpect(jsonPath("$.data.placeName").value("배트민턴장"))
                .andExpect(jsonPath("$.data.address").value("강남구 강남대로1234"))
                .andExpect(jsonPath("$.data.phoneNumber").value("010-1234-1234"))
                .andExpect(jsonPath("$.data.capacity").value(30))
                .andExpect(jsonPath("$.data.memo").value("신장개업"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

    @DisplayName("[API][GET] 단일 장소 조회 - 장소 없는 경우")
    @Test
    void givenPlaceAndItsId_whenRequestPlace_thenReturnsEmptyInStandardResponse() throws Exception {
        //given
        int placeId = 2;
        //when
        //then
        mvc.perform(get("/api/places/"+ placeId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isEmpty() )
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }
}