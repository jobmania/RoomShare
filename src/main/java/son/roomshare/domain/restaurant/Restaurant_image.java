package son.roomshare.domain.restaurant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_image")
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant_image {

    @Id
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant_basic restaurant;

    private String imageUrl;

}
