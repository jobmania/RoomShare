package son.roomshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import son.roomshare.repository.BasicRestaurantRepository;
import son.roomshare.service.RestaurantService;

@Controller
@RequiredArgsConstructor
public class MapController {
    private final RestaurantService restaurantService;

    @GetMapping
    public void um(){

    }

}
