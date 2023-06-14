package son.roomshare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import son.roomshare.domain.restaurant.Restaurant_basic;
import son.roomshare.repository.BasicRestaurantRepository;
import son.roomshare.repository.DetailRestaurantRepository;
import son.roomshare.repository.ImageRestaurantRepository;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final BasicRestaurantRepository basicRestaurantRepository;
    private final DetailRestaurantRepository detailRestaurantRepository;
    private final ImageRestaurantRepository imageRestaurantRepository;


    @Transactional(readOnly = true)
    public void getAllList(int page, String sortBy, boolean isAsc){

        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC; // ASC 오래된순, DESC 최근순
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, 10, sort); // 한페이지당 10개
        Page<Restaurant_basic> list = basicRestaurantRepository.findAll(pageable);

    }




}
