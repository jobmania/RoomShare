package son.roomshare.domain.map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MapResponse{
    private String name;
    private Long latitude;
    private Long longitude;
}