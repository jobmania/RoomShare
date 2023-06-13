package son.roomshare.domain.restaurant;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_image")
public class Restaurant_image {

    @Id
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant_basic restaurant;

    private String imageUrl;

}
