package son.roomshare.domain.map;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
public class MapRequest {
    private String latitude;
    private String longitude;

    @Range(max = 1000)
    private Long distance;
}
