package son.roomshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import son.roomshare.domain.restaurant.Restaurant_basic;
import son.roomshare.domain.restaurant.dto.RestaurantResponse;
import son.roomshare.service.RestaurantService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/food")
public class RestaurantController extends HomeController{

    private final RestaurantService restaurantService;

    @GetMapping("")
    public String getAllList(@RequestParam(value = "page" , defaultValue = "0") int page,
                             @RequestParam(value = "sort", defaultValue = "id" ) String sort,
                             @RequestParam(value = "asc", defaultValue = "true") boolean isAsc,
                             @RequestParam(value = "search_keyword", defaultValue = "" ) String keyword,
                             @RequestParam(value = "search_target", defaultValue = "") String target,
                             Model model){
        Page<Restaurant_basic> allList = restaurantService.getAllList(page, sort, isAsc, keyword, target);
        model.addAttribute("list", allList);
        return "restaurant/list";
    }

    @GetMapping("/{id}")
    private String getDetail( @PathVariable("id") Long id, Model model){
        RestaurantResponse response = restaurantService.getDetail(id);
        model.addAttribute("item", response);
        return "restaurant/detail";
    }

}
