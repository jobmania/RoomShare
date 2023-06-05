package son.roomshare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import son.roomshare.domain.member.Member;

@Service
@RequiredArgsConstructor
public class MemberService {



    @Transactional
    public void signUp(Member signMember) {

        //
    }
}
