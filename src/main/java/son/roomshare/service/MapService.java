package son.roomshare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import son.roomshare.domain.map.MapResponse;
import son.roomshare.repository.BasicRestaurantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapService {
    private final BasicRestaurantRepository basicRestaurantRepository;


    public List<MapResponse> getByDistance(Long distance, Long my_latitude, Long my_longitude) {

        // 위도 ,경도 계산 
        // https://wildeveloperetrain.tistory.com/171

        //위도 1° = 지구 반지름 x 1° x 1rad(라디안)
//        my_latitude = 6371 * 1 * (Math.PI / 180);
//
////경도 1° = 지구 반지름 x 1° x cos(위도) x 1rad(라디안)
//        my_longitude = 6371 * 1 * (Math.PI / 180) * Math.cos(Math.toRadians(35.8448));
//



        return null;
    }





    Long getDistance(){
        Long distance = 0L;
        return distance;
    }

}
