package son.roomshare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import son.roomshare.domain.restaurant.Restaurant_basic;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasicRestaurantRepository extends JpaRepository<Restaurant_basic, Long> {
    Optional<Restaurant_basic> findOneById( Long id);

    // IgnoreCase를 추가 ~> keyword의 문자를 대문자로 바꾸어 준다
    Page<Restaurant_basic> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Restaurant_basic> findByLandNumberAddressContainingIgnoreCase(String keyword, Pageable pageable);


    List<Restaurant_basic> findByLongitudeBetweenAndLatitudeBetween(double minX, double maxX, double minY, double maxY);
}
