package son.roomshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import son.roomshare.domain.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Long, Member> {
}
