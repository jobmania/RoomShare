package son.roomshare.controller;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import son.roomshare.domain.map.MapResponse;
import son.roomshare.service.MapService;

import java.util.List;

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
    public String viewRestaurant(
                                @RequestParam("latitude") Long latitude,
                                @RequestParam("longitude") Long longitude,
                                @RequestParam("distance")Long distance){
       List<MapResponse> mapList =  mapService.getByDistance(latitude,longitude,distance);

        return "kakaomap/resultMap";
    }




}
