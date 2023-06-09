package son.roomshare.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import son.roomshare.domain.member.Member;
import son.roomshare.domain.member.MemberDetailsImpl;
import son.roomshare.repository.MemberRepository;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional  // UserDetails 와 Authentication 의 패스워드를 비교하고 검증하는 로직 ( 로그인)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Member> findMember = memberRepository.findByEmail(email);
        if(findMember.isPresent()){
            Member member = findMember.get();

           log.info("memberDetails 저장 ={}",member.getEmail());

            return new MemberDetailsImpl(member);
        }else {
            throw new UsernameNotFoundException(email + "데이터 베이스에 없음");
        }

    }


   
}
