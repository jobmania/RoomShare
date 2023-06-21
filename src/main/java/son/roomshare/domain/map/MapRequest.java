package son.roomshare.domain.map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MapRequest {
    private String latitude;
    private String longitude;
    private Long distance;
}
