package son.roomshare.domain.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant_basic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant_basic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    @Column(name = "roadname_address")
    private String streetAddress;

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


    @Override
    public String toString() {
        return  String.valueOf(id);
    }
}
