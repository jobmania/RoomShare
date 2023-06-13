package son.roomshare.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "member")
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

    @Column(unique = true,name = "nickname")
    private String nickName;


    @Column(unique = true)
    private String password;


    @Column(columnDefinition = "VARCHAR(255)") // Enum 타입시 Type 설정.
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;


    /**
     * 회원 가입
     * */
    @Builder
    public Member(String email, String nickName, String password, MemberRole memberRole) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.memberRole = memberRole;
    }

}
