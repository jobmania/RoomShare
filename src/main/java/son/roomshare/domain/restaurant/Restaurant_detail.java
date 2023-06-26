package son.roomshare.domain.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import son.roomshare.converter.YesNoConverter;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Restaurant_detail {

    @Id
    Long id;

//    @Convert(converter = YesNoConverter.class)
    private Boolean parkingAvailable;
    private Boolean wifiAvailable;
    private Boolean kidsRoomAvailable;
    private Boolean petAvailable;
    private Boolean multilingualMenuAvailable;
    private Boolean toiletInfoAvailable;
    private String holidayInfo;
    private String businessHourInfo;
    @Column(name = "parcel_sale_service_available")
    private Boolean parcelService;

    @Column(name = "barrier_free_available")
    private Boolean barrierFree;

    @Column(name = "delivery_available")
    private Boolean deliveryService;

    @Column(name = "reservation_available")
    private String reservation;

    private String homepage;
    @Column(name = "nearby_landmark")
    private String landmark;

    @Column(name = "landmark_latitude")
    private String landmarkLatitude;

    @Column(name = "landmark_longitude")
    private String landmarkLongitude;

    @Column(name = "kiosk_available")
    private Boolean kiosk;

    @Column(name = "mobilepay_available")
    private Boolean mobile;
    @Column(name = "smartorder_available")
    private Boolean smartOrder;

}
