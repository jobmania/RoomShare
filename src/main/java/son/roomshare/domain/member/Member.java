package son.roomshare.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    private Long id;

    /**
     * memberName = 유저 아이디
     * */
    @Column(unique = true)
    private String memberName;

    @Column(unique = true)
    private String nickName;

    /**
     * 회원 검증용 email
     * */
    @Column(unique = true)
    private String email;


    @Column(unique = true)
    private String password;


    @Column(columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;


    /**
     * 회원 가입
     * */
    public Member(String memberName, String nickName, String email, String password, MemberRole memberRole, PasswordEncoder passwordEncoder) {
        this.memberName = memberName;
        this.nickName = nickName;
        this.email = email;
        this.password = passwordEncoder.encode(password);
        this.memberRole = memberRole;
    }
}
