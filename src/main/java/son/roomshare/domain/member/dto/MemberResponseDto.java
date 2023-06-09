package son.roomshare.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import son.roomshare.domain.member.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {

    private String email;


    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getEmail());
    }

}
