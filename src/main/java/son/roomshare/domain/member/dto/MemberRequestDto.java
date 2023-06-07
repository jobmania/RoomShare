package son.roomshare.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import son.roomshare.domain.member.Member;
import son.roomshare.domain.member.MemberRole;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String email;
    private String password;
    private MemberRole memberRole;

    public Member toMember(PasswordEncoder passwordEncoder, MemberRole memberRole) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .memberRole(memberRole)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}