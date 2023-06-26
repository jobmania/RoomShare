package son.roomshare.domain.restaurant.dto;

import lombok.Data;

@Data
public class RestaurantOptionRequestDto {
    private Boolean isPark;
    private Boolean isPet;
    private Boolean isMultiLingual;
    private Boolean isDelivery;

    public RestaurantOptionRequestDto() {
    }

    public RestaurantOptionRequestDto(Boolean isPark, Boolean isPet, Boolean isMultiLingual, Boolean isDelivery) {
        this.isPark = isPark;
        this.isPet = isPet;
        this.isMultiLingual = isMultiLingual;
        this.isDelivery = isDelivery;
    }
}
