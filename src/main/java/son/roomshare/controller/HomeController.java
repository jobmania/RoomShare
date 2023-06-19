package son.roomshare.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import son.roomshare.domain.member.MemberDetailsImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
@Slf4j
public class HomeController {


    @GetMapping("/")
    public String home(){
        return "home";
    }



    @ModelAttribute("isLoggedIn")
    public boolean isLoggedIn(@AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        // 로그인 상태를 확인하는 로직을 구현하여 true 또는 false 반환
        return memberDetails != null;
    }




}
