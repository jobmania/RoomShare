package son.roomshare.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import son.roomshare.domain.restaurant.Restaurant_basic;
import son.roomshare.domain.restaurant.Restaurant_detail;
import son.roomshare.domain.restaurant.Restaurant_image;
import son.roomshare.domain.restaurant.dto.RestaurantResponse;
import son.roomshare.repository.BasicRestaurantRepository;
import son.roomshare.repository.DetailRestaurantRepository;
import son.roomshare.repository.ImageRestaurantRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {

    private final BasicRestaurantRepository basicRestaurantRepository;
    private final DetailRestaurantRepository detailRestaurantRepository;
    private final ImageRestaurantRepository imageRestaurantRepository;


    @Transactional(readOnly = true)
    public Page<Restaurant_basic> getAllList(int page, String sortBy, boolean isAsc){

        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC; // ASC 오래된순, DESC 최근순
        Sort sort = Sort.by(direction, sortBy); // 행 조건, 정렬 조건,
        Pageable pageable = PageRequest.of(page, 10, sort); // 한페이지당 10개
        Page<Restaurant_basic> list = basicRestaurantRepository.findAll(pageable);
        return list;
    }

    @Transactional(readOnly = true)
    public RestaurantResponse getDetail(Long id){

        log.info("여기까지오나?");

        Optional<Restaurant_basic> findRestaurant = basicRestaurantRepository.findOneById(1085L);
        Optional<Restaurant_detail> findDetailRestaurant = detailRestaurantRepository.findOneById(1085L);
        Optional<Restaurant_image> byRestaurant = imageRestaurantRepository.findByRestaurant(1085L);

        log.info("머가문제냐?");
        if(findRestaurant.isPresent() && findDetailRestaurant.isPresent() ){
            Restaurant_basic restaurantBasic = findRestaurant.get();
            Restaurant_detail restaurantDetail = findDetailRestaurant.get();

            return RestaurantResponse.builder()
                    .id(restaurantBasic.getId())
                   .build();
        }else {
            throw new IllegalArgumentException("찾을수 없다고!");
        }

    }




}
