package son.roomshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import son.roomshare.domain.restaurant.Restaurant_basic;

import java.util.Optional;

@Repository
public interface BasicRestaurantRepository extends JpaRepository<Restaurant_basic, Long> {
    Optional<Restaurant_basic> findOneById( Long id);

}
