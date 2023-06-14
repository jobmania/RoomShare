package son.roomshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import son.roomshare.domain.restaurant.Restaurant_basic;
import son.roomshare.domain.restaurant.Restaurant_detail;

import java.util.Optional;

@Repository
public interface DetailRestaurantRepository extends JpaRepository<Restaurant_detail, Long> {
    Optional<Restaurant_detail> findOneById(@Param("id") Long id);
}
