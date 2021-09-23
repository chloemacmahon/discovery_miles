package za.ac.nwu.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.nwu.ac.domain.dto.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
