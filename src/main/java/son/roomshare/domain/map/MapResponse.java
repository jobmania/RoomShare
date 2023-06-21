package son.roomshare.domain.map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MapResponse{
    public String name;
    public String latitude;
    public String longitude;
}