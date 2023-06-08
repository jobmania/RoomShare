package son.roomshare.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import son.roomshare.config.security.jwt.TokenDto;
import son.roomshare.config.security.jwt.TokenRequestDto;
import son.roomshare.domain.member.MemberRole;
import son.roomshare.domain.member.dto.MemberResponseDto;
import son.roomshare.domain.member.dto.SignUpMemberRequestDto;
import son.roomshare.service.MemberService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/auth/signup")
    public String signUpForm(@ModelAttribute("member") SignUpMemberRequestDto dto){
        return "login/signForm";
    }


    @PostMapping("/auth/signup")
    public String signUp(@Validated @ModelAttribute("member")SignUpMemberRequestDto dto, BindingResult bindingResult){


        if(bindingResult.hasErrors()){
            log.error("bindingResult ={}",bindingResult.getTarget()); // 잘들고오며, error 처리가 필요해~
            return "login/signForm";
        }

        memberService.signup(dto);
        return "home";
    }

//    @PostMapping("/auth/signup")
//    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
//        return ResponseEntity.ok(memberService.signup(memberRequestDto));
//    }

//    @PostMapping("/auth/login")
//    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response) {
//        return ResponseEntity.ok(memberService.login(memberRequestDto,response));
//    }

    @PostMapping("/auth/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(memberService.reissue(tokenRequestDto));
    }



    @ModelAttribute("memberType")
    public MemberRole[] memberTypes() {
        return MemberRole.values();
    }
}
