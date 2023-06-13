package son.roomshare.domain.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_detail")
public class Restaurant_detail {

    @Id
    Long id;

    private String parkingAvailable;
    private String wifiAvailable;
    private String kidsRoomAvailable;
    private String multilingualMenuAvailable;
    private String toiletInfoAvailable;
    private String holidayInfo;
    private String businessHourInfo;
    @Column(name = "parcel_sale_service_available")
    private String parcelService;

    @Column(name = "barrier_free_available")
    private String barrierFree;

    @Column(name = "delivery_available")
    private String deliveryService;

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
    private String kiosk;

    @Column(name = "mobilepay_available")
    private String mobile;
    @Column(name = "smartorder_available")
    private String smartOrder;

}
