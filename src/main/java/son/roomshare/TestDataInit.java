package son.roomshare;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import son.roomshare.domain.member.Member;
import son.roomshare.domain.member.MemberRole;
import son.roomshare.domain.member.dto.SignUpMemberRequestDto;
import son.roomshare.repository.MemberRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {


    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        SignUpMemberRequestDto memberRequestDto = new SignUpMemberRequestDto();
        memberRequestDto.setEmail("abc@abc.com");
        memberRequestDto.setPassword("qwer1234!!");
        memberRequestDto.setNickName("엄준식");
        memberRequestDto.setMemberRole(MemberRole.현지인);

        Member member = memberRequestDto.toMember(passwordEncoder,memberRequestDto.getMemberRole());

        memberRepository.save(member);
    }


}