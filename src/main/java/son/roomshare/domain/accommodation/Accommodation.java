package son.roomshare.domain.accommodation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Accommodation {

    @Id
    Long id;

    private String name;
    private String category;
    private String city;
    private String district;
    private String dong;
    @Column(name = "landnumber_address")
    private String landNumberAddress;

    @Column(name = "street_address")
    private String streetAddress;

    private String longitude;
    private String latitude;
    private String phone;
    private String homepage;
    @Column(name = "parking_available")
    private String parking;
    @Column(name = "toilet_available") // 공공화장실 가능?
    private String toilet;

    @Column(name = "toilet_type")
    private String toiletType;

    @Column(name = "nursing_room_available") // 모유수유실 가능?
    private String nursingRoom;

    @Column(name = "storage_available") // 물품보관소 가능?
    private String storage;


    @Column(name = "cradle_available") // 유아침대 가능?
    private String cradle;

    @Column(name = "wheelchair_available")
    private String wheelchair;

    @Column(name = "blind_guideway_available")
    private String blindGuide;

    @Column(name = "regist_date")
    private LocalDateTime registerDate;
}
