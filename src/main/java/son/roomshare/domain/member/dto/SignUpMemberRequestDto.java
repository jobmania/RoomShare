package son.roomshare.domain.member.dto;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import son.roomshare.domain.member.Member;
import son.roomshare.domain.member.MemberRole;

import javax.validation.constraints.*;

@Data
public class SignUpMemberRequestDto {

    @Email
    private String email;

    @NotBlank
    @Size(min = 2, max = 10,message = "2 ~ 10자 사이를 입력")
    private String nickName;


    @NotNull
    @Pattern(regexp="[a-zA-Z0-9~!@#$%^&*()-_=+]{6,12}", message = "비밀번호는 영어,숫자,특수문자 포함해서 6~12자리 이내로 입력해주세요.")
    private String password;

    @NotNull(message = "일치하지 않습니다.")
    private String passwordCheck;

    @NotNull(message = "선택해주셈")
    private MemberRole memberRole;


    public Member toMember(PasswordEncoder passwordEncoder, MemberRole memberRole) {
        return Member.builder()
                .email(email)
                .nickName(nickName)
                .password(passwordEncoder.encode(password))
                .memberRole(memberRole)
                .build();
    }


}
