package com.lineup.covid19lineup.dto;

import com.lineup.covid19lineup.constant.PlaceType;
import com.lineup.covid19lineup.domain.Place;

public record PlaceDTO(
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo
) {
    public static PlaceDTO of(PlaceType placeType, String placeName, String address, String phoneNumber, Integer capacity, String memo) {
        return new PlaceDTO(placeType, placeName, address, phoneNumber, capacity, memo);
    }


}
