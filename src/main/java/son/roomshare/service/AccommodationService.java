package son.roomshare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import son.roomshare.repository.AccommodationRepository;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;


}
