package son.roomshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import son.roomshare.domain.restaurant.Restaurant_basic;
@Repository
public interface BasicRestaurantRepository extends JpaRepository<Restaurant_basic, Long> {
}
