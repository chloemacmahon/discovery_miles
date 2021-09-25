package za.ac.nwu.ac.logic;

import za.ac.nwu.ac.domain.dto.member.Member;

public interface MemberService {

    Member registerMember(String name, String surname, String idNumber, String email, String password);

    Member logInMember(String email, String password);

    void resetWeeklyGoals(Member member);
}
