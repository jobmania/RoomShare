package son.roomshare.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import son.roomshare.domain.map.MapRequest;
import son.roomshare.domain.map.MapResponse;
import son.roomshare.domain.restaurant.Restaurant_basic;
import son.roomshare.repository.BasicRestaurantRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MapService {
    private static final Long EARTH_RADIUS = 6378L; // 지구 반경(km단위)
    private final BasicRestaurantRepository basicRestaurantRepository;


    @Transactional(readOnly = true)
    public List<MapResponse> getByDistance(Double my_latitude, Double my_longitude, Double inputDistance) {


        // 위도 ,경도 계산
        //1. 처음 접속시는 : 내위치 주변 검색
        //2. 특정 위치 검색시 다른 화면으로
        // https://wildeveloperetrain.tistory.com/171


        List<Restaurant_basic> restaurantBasics = aroundRestaurantList(my_latitude, my_longitude, inputDistance);

        List<MapResponse> responseList = new ArrayList<>();

        for (Restaurant_basic restaurant : restaurantBasics) {

            MapResponse inputMap = MapResponse.builder()
                    .name(restaurant.getName())
                    .latitude(String.valueOf(restaurant.getLatitude()))
                    .longitude(String.valueOf(restaurant.getLongitude()))
                    .build();
            responseList.add(inputMap);
        }

        return responseList;
    }





    // 거리에 따른 정사각형 x,y 좌표
    public List<Restaurant_basic> aroundRestaurantList(Double latitude, Double longitude, Double inPutDistance) {
        //현재 위도 좌표 (y 좌표)
        double nowLatitude = latitude;
        //현재 경도 좌표 (x 좌표)
        double nowLongitude = longitude;

        //m당 y 좌표 이동 값
        double mForLatitude =(1 /(EARTH_RADIUS* 1 *(Math.PI/ 180)))/ 1000;
        //m당 x 좌표 이동 값
        double mForLongitude =(1 /(EARTH_RADIUS* 1 *(Math.PI/ 180)* Math.cos(Math.toRadians(nowLatitude))))/ 1000;

        //현재 위치 기준 검색 거리 좌표
        double maxY = nowLatitude +(inPutDistance* mForLatitude);
        double minY = nowLatitude -(inPutDistance* mForLatitude);
        double maxX = nowLongitude +(inPutDistance* mForLongitude);
        double minX = nowLongitude -(inPutDistance* mForLongitude);

        //해당되는 좌표의 범위 안에 있는 가맹점 (( 근방에서 가장 가까운 10개의 데이터만 들고오게끔..-> mybatis )
        List<Restaurant_basic>tempAroundList = basicRestaurantRepository.findByLongitudeBetweenAndLatitudeBetween(minX, maxX, minY, maxY);
        List<Restaurant_basic>resultAroundList = new ArrayList<>();

        //정확한 거리 측정 ( 반경보다 큰 좌표값 제거를 위해.)
        for(Restaurant_basic restaurant : tempAroundList) {
            double thisLatitude = restaurant.getLatitude();
            double thisLongitude = restaurant.getLongitude();
            double distance = getDistance(nowLatitude, nowLongitude, thisLatitude, thisLongitude);

            if(distance < inPutDistance) {
                resultAroundList.add(restaurant);
                log.info(" 식당 이름 ={}",restaurant.getName());
            }
        }
        return resultAroundList;
    }




    //두 지점 간의 거리 계산
    public double getDistance(double my_lat, double my_long, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - my_lat);
        double dLon = Math.toRadians(lon2 - my_long);

        double a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(my_lat))* Math.cos(Math.toRadians(lat2))* Math.sin(dLon/2)* Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return EARTH_RADIUS * c * 1000;
    }

}
