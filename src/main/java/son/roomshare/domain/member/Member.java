package son.roomshare.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * memberName = 유저 아이디
     * 회원 검증용 email
     * */
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickName;


    @Column(unique = true)
    private String password;


    @Column(columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;


    /**
     * 회원 가입
     * */
    @Builder
    public Member(String email, String password, MemberRole memberRole) {
        this.email = email;
        this.password = password;
        this.memberRole = memberRole;
    }

}
