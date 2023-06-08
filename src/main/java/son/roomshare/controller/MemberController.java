package son.roomshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import son.roomshare.config.security.jwt.TokenDto;
import son.roomshare.config.security.jwt.TokenRequestDto;
import son.roomshare.domain.member.Member;
import son.roomshare.domain.member.dto.MemberRequestDto;
import son.roomshare.domain.member.dto.MemberResponseDto;
import son.roomshare.domain.member.dto.SignUpMemberRequestDto;
import son.roomshare.service.MemberService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

//    @GetMapping("/auth/signup")
//    public String signUpForm(@ModelAttribute("loginForm") SignUpMemberRequestDto dto){
//        return "login/loginForm";
//    }
//
//    @PostMapping("/auth/signup")
//    public String signUp(@Validated SignUpMemberRequestDto dto, BindingResult bindingResult){
//
//        return "member_view";
//    }

//    @PostMapping("/auth/signup")
//    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
//        return ResponseEntity.ok(memberService.signup(memberRequestDto));
//    }
//
//    @PostMapping("/auth/login")
//    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response) {
//        return ResponseEntity.ok(memberService.login(memberRequestDto,response));
//    }

    @PostMapping("/auth/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(memberService.reissue(tokenRequestDto));
    }

}
