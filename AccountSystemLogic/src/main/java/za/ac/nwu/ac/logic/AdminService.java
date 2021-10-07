package za.ac.nwu.ac.logic;

import za.ac.nwu.ac.domain.dto.activity.Activity;
import za.ac.nwu.ac.domain.dto.admin.Admin;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;

public interface AdminService {

    Admin registerAdmin(String email, String password, int controlPasswordGiven);

    Admin logInAdmin(String email, String password);

    Activity addNewActivity(String activityType, String activityName, int pointsEarned);

    Admin findAdminById(Long id);

    void changeDefaultGoalPoints(int points);

    void changeMaxMilesValue(int maxMilesValue);
}
