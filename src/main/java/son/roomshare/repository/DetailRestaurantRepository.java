package son.roomshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import son.roomshare.domain.restaurant.Restaurant_basic;
import son.roomshare.domain.restaurant.Restaurant_detail;
@Repository
public interface DetailRestaurantRepository extends JpaRepository<Restaurant_detail, Long> {
}
