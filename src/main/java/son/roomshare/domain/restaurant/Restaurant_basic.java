package son.roomshare.domain.restaurant;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant_basic")
@Getter
@NoArgsConstructor
public class Restaurant_basic {

    @Id
    Long id;

    private String name;

    @Column(name = "roadname_address")
    private String roadNameAddress;

    @Column(name = "landnumber_address")
    private String landNumberAddress;

    private String latitude;
    private String longitude;
    private String phoneNumber;
    private String category;
    private String type;
    private String description;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Restaurant_image> imageList;



}
