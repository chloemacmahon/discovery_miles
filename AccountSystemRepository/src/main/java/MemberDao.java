import za.ac.nwu.ac.domain.dto.member.Member;

import java.util.List;

public interface MemberDao {

    List<Member> findAll();

    public void insertMember(Member member);

    public void updateMember(Member member);

    public void executeUpdateMember(Member member);

    public void deleteMember(Member member);
}
