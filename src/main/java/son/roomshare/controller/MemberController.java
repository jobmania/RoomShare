package son.roomshare.controller;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import son.roomshare.config.security.jwt.TokenDto;
import son.roomshare.config.security.jwt.TokenRequestDto;
import son.roomshare.domain.member.MemberRole;
import son.roomshare.domain.member.dto.LoginMemberRequestDto;
import son.roomshare.domain.member.dto.SignUpMemberRequestDto;
import son.roomshare.service.MemberService;

import javax.servlet.http.HttpServletResponse;

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
    public String signUp(@Validated @ModelAttribute("member") SignUpMemberRequestDto dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("bindingResult ={}",bindingResult.getTarget());
            return "login/signForm";
        }

        try {
            memberService.signup(dto);
        } catch (DuplicateRequestException e) { // 중복된 내용 있을시 처리!
            bindingResult.addError(new ObjectError("signup", e.getMessage()));
            return "login/signForm";
        }
        return "home";
    }

    @GetMapping("/auth/login")
    public String loginForm(@ModelAttribute("member") LoginMemberRequestDto dto){
        return "login/loginForm";
    }

    @PostMapping("/auth/login")
    public String login(@Validated @ModelAttribute("member") LoginMemberRequestDto dto, BindingResult bindingResult, HttpServletResponse response){

        log.info("로그인 확인 {},{}",dto.getEmail(),dto.getPassword());

        if(bindingResult.hasErrors()){
            log.error("bindingResult ={}",bindingResult.getTarget());
            return "login/loginForm";
        }

        try {
            memberService.login(dto, response);
        } catch (BadCredentialsException e){
            bindingResult.addError(new ObjectError("login", "로그인에 실패했습니다."));
            return "login/loginForm";
        }
        return "home";
    }


    @PostMapping("/auth/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(memberService.reissue(tokenRequestDto));
    }

    @ModelAttribute("memberType")
    public MemberRole[] memberTypes() {
        return MemberRole.values();
    }
}
