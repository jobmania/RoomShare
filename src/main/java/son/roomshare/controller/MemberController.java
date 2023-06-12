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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private static final String AUTHORIZATION = "Authorization";
    private static final String REFRESH = "Refresh_Token";

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
            TokenDto tokenDto = memberService.login(dto, response);

            setCookie(AUTHORIZATION, "Bearer_" + tokenDto.getAccessToken(), response);
            setCookie(REFRESH, tokenDto.getRefreshToken(), response);

        } catch (BadCredentialsException e){
            bindingResult.addError(new ObjectError("login", "로그인에 실패했습니다."));
            return "login/loginForm";
        }
        return "loginHome";
    }

    private static void setCookie(String name, String value, HttpServletResponse response) {
        Cookie refreshCookie = new Cookie(name, value);
        refreshCookie.setPath("/"); // / 동일 사이트과 크로스 사이트에 모두 쿠키 전송이 가능합니다
//        refreshCookie.setSecure(true);  // Secure 속성을 설정하면 쿠키는 HTTPS 프로토콜을 통해서만 전송
        refreshCookie.setHttpOnly(true); //  JavaScript를 통한 쿠키 접근을 막을 수 있습니다
        response.addCookie(refreshCookie);
    }


    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response, AUTHORIZATION);
        expireCookie(response, REFRESH);
        log.info("로그아웃완료!");
        return "home";
    }
    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);

        cookie.setMaxAge(0);
        response.addCookie(cookie);
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
