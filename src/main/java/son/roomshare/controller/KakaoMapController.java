package son.roomshare.controller;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import son.roomshare.domain.map.MapRequest;
import son.roomshare.domain.map.MapResponse;
import son.roomshare.service.MapService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/map")
@RequiredArgsConstructor
public class KakaoMapController extends HomeController {

    private final MapService mapService;

    @GetMapping("")
    public String viewPage(){
        return "kakaomap/map";
    }

    @GetMapping("/food")
    public String viewRestaurant(@ModelAttribute MapRequest mapRequest, Model model){

        Double latitude = Double.parseDouble(mapRequest.getLatitude());
        Double longitude =  Double.parseDouble(mapRequest.getLongitude());
        Double inputDistance = Double.valueOf(mapRequest.getDistance());

       List<MapResponse> mapList =  mapService.getByDistance(latitude, longitude, inputDistance);

        model.addAttribute("list", mapList);
        return "kakaomap/resultMap";
    }




}
