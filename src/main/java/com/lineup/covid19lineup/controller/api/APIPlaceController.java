package com.lineup.covid19lineup.controller.api;

import com.lineup.covid19lineup.constant.PlaceType;
import com.lineup.covid19lineup.dto.APIDataResponse;
import com.lineup.covid19lineup.dto.PlaceResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIPlaceController {
    @GetMapping("/places")
    public APIDataResponse<List<PlaceResponse>> getAllPlace() {
        return APIDataResponse.of(
                List.of(
                        PlaceResponse.of(
                                PlaceType.COMMON,
                                "배트민턴장",
                                "강남구 강남대로1234",
                                "010-1234-1234",
                                30,
                                "신장개업"
                        )
                )
        );
    }

    @PostMapping("/places")
    public Boolean createPlace() {
        return true;
    }

    @GetMapping("/places/{placeId}")
    public APIDataResponse<PlaceResponse> getPlace(@PathVariable Integer placeId) {
        if (placeId.equals(2))
            return APIDataResponse.of(null);

        return APIDataResponse.of(
                PlaceResponse.of(
                        PlaceType.COMMON,
                        "배트민턴장",
                        "강남구 강남대로1234",
                        "010-1234-1234",
                        30,
                        "신장개업"
                )
        );
    }

    @PutMapping("/places/{placeId}")
    public Boolean modifyPlace(@PathVariable Integer placeId) {
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean removePlace(@PathVariable Integer placeId) {
        return true;
    }
}
