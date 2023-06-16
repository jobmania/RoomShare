package son.roomshare.domain.restaurant.dto;

import lombok.Builder;
import lombok.Data;
import son.roomshare.domain.restaurant.Restaurant_basic;
import son.roomshare.domain.restaurant.Restaurant_detail;

@Data
@Builder
public class RestaurantResponse {
    private Long id;
    private Restaurant_basic restaurant_basic;
    private Restaurant_detail restaurant_detail;

}
