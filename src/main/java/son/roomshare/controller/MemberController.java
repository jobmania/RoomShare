package son.roomshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import son.roomshare.domain.member.Member;
import son.roomshare.domain.member.dto.SignUpMemberDto;
import son.roomshare.repository.MemberRepository;
import son.roomshare.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/signup")
    public String signUpForm(@ModelAttribute("loginForm") SignUpMemberDto dto){
        return "login/loginForm";
    }

    @PostMapping("/signup")
    public String signUp(@Validated SignUpMemberDto dto, BindingResult bindingResult){

        Member signMember = new Member(dto.getMemberName(),dto.getNickName(),dto.getEmail(),dto.getPassword(),dto.getMemberRole(),passwordEncoder );

        if(bindingResult.hasErrors()){
            return "member_view";
        }


        // 정상 로직 수행.
        memberService.signUp(signMember);





        return "member_view";
    }

}
