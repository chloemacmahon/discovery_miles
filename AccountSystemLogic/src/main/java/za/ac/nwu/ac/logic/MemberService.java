package za.ac.nwu.ac.logic;

import za.ac.nwu.ac.domain.dto.activity.Activity;
import za.ac.nwu.ac.domain.dto.member.Member;

import java.util.List;

public interface MemberService {

    Member registerMember(String name, String surname, String idNumber, String email, String password);

    Member logInMember(String email, String password);

    void updateMemberInDatabase(Member member);

    void resetWeeklyGoals(Member member);
    
    int viewMiles(Member member);

    List<Activity> viewActivities();

    Member findMemberById(Long id);

    //View goal info
    //View purchased rewards

}
