package son.roomshare.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import son.roomshare.domain.member.Member;
import son.roomshare.domain.member.MemberDetailsImpl;
import son.roomshare.domain.member.dto.MemberResponseDto;
import son.roomshare.service.MemberService;
import son.roomshare.utils.SecurityUtil;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class TestController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> findMemberInfoById() {
        return ResponseEntity.ok(memberService.findMemberInfoByEmail(SecurityUtil.getCurrentMemberId()));
    }

    @GetMapping("/{email}")
    public ResponseEntity<MemberResponseDto> findMemberInfoByEmail(@PathVariable String email) {
        return ResponseEntity.ok(memberService.findMemberInfoByEmail(email));
    }

    @GetMapping("/pass")
    public ResponseEntity<MemberResponseDto> findMember( @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        log.info("memberDetails ={}",memberDetails.getMember().getEmail());
        Member member = memberDetails.getMember();
        return ResponseEntity.ok(memberService.findMemberInfoByEmail(member.getEmail()));

    }

}
