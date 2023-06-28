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
import son.roomshare.domain.restaurant.dto.RestaurantOptionRequestDto;
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
    public Page<Restaurant_basic> getAllList(int page, String sortBy, boolean isAsc, String keyword, String target, RestaurantOptionRequestDto optionRequest){

        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC; // ASC 오래된순, DESC 최근순
        Sort sort = Sort.by(direction, sortBy); // 데이터베이스 열 조건, 정렬 조건,
        Pageable pageable = PageRequest.of(page, 10, sort); // 한페이지당 10개
        Page<Restaurant_basic> list ;

        // Option이 존재한다면 일대일 연관관계의 detail에서 해당에 맞는 것을 찾아야된느 부분도 존재. !



        // 이부분도 동적 쿼리 가능성
        // Select from TABLE WHERE name = ?
        // Select from TABLE WHERE address = ?

        if ("name".equals(target)) {
            list = basicRestaurantRepository.findByNameContainingIgnoreCase(keyword, pageable);
        } else  { // ("address".equals(target))
            list = basicRestaurantRepository.findByLandNumberAddressContainingIgnoreCase(keyword, pageable);
        }


        return list;
    }

    @Transactional(readOnly = true)
    public RestaurantResponse getDetail(Long id){


        Optional<Restaurant_basic> findRestaurant = basicRestaurantRepository.findOneById(id);
        Optional<Restaurant_detail> findDetailRestaurant = detailRestaurantRepository.findOneById(id);

        if(findRestaurant.isPresent() && findDetailRestaurant.isPresent() ){
            Restaurant_basic restaurantBasic = findRestaurant.get();
            Restaurant_detail restaurantDetail = findDetailRestaurant.get();

            return RestaurantResponse.builder()
                    .id(restaurantBasic.getId())
                    .restaurant_basic(restaurantBasic)
                    .restaurant_detail(restaurantDetail)
                   .build();
        }else {
            throw new IllegalArgumentException("찾을수 없다고!");
        }

    }



    @Transactional(readOnly = true)
    public  Page<Restaurant_basic> getDetailList(){
    // ?
        return null;
    }


}
