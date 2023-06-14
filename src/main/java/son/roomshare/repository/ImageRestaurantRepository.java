package son.roomshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import son.roomshare.domain.restaurant.Restaurant_detail;
import son.roomshare.domain.restaurant.Restaurant_image;

import java.util.Optional;

@Repository
public interface ImageRestaurantRepository extends JpaRepository<Restaurant_image, Long> {

    Optional<Restaurant_image> findByRestaurant(Long id);
}
