package son.roomshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import son.roomshare.domain.accommodation.Accommodation;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
