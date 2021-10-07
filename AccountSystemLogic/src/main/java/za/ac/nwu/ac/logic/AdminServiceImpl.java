package za.ac.nwu.ac.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.activity.Activity;
import za.ac.nwu.ac.domain.dto.activity.DrivingActivity;
import za.ac.nwu.ac.domain.dto.activity.HealthActivity;
import za.ac.nwu.ac.domain.dto.activity.SpendingActivity;
import za.ac.nwu.ac.domain.dto.admin.Admin;
import za.ac.nwu.ac.domain.dto.gameboard.GameTile;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;
import za.ac.nwu.ac.domain.exception.*;
import za.ac.nwu.ac.repository.ActivityRepository;
import za.ac.nwu.ac.repository.AdminRepository;
import za.ac.nwu.ac.repository.MemberRepository;
import za.ac.nwu.ac.repository.RewardPartnerRepository;

@Component
public class AdminServiceImpl implements AdminService {

    private static final int controlPassword = 3879;

    private AdminRepository adminRepository;

    private MemberRepository memberRepository;

    private RewardPartnerRepository rewardPartnerRepository;

    private ActivityRepository activityRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, MemberRepository memberRepository, RewardPartnerRepository rewardPartnerRepository, ActivityRepository activityRepository) {
        this.adminRepository = adminRepository;
        this.memberRepository = memberRepository;
        this.rewardPartnerRepository = rewardPartnerRepository;
        this.activityRepository = activityRepository;
    }

    public Admin registerAdmin(String email, String password, int controlPasswordGiven) {
        if (controlPassword == controlPasswordGiven) {
            if (Validator.isValidPassword(password)) {
                Admin admin = new Admin(email, password);
                adminRepository.save(admin);
                return admin;
            } else {
                throw new IncorrectPasswordException();
            }
        } else
            throw new FailedToCreateAdminException("Password does not match control password");
    }

    public Admin logInAdmin(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin == null) {
            throw new FailedToLogInException();
        }
        if (admin.getPassword().equals(password))
            return admin;
        else {
            throw new IncorrectPasswordException();
        }
    }

    public Activity addNewActivity(String activityType, String description, int pointsEarned) {
        if (pointsEarned < 0) {
            throw new FailedToCreateActivityException();
        }
            switch (activityType) {
                case "Health":
                    HealthActivity healthActivity = new HealthActivity(description, pointsEarned);
                    activityRepository.save(healthActivity);
                    return healthActivity;
                case "Drive":
                    DrivingActivity drivingActivity = new DrivingActivity(description, pointsEarned);
                    activityRepository.save(drivingActivity);
                    return drivingActivity;
                case "Spend":
                    SpendingActivity spendingActivity = new SpendingActivity(description, pointsEarned);
                    activityRepository.save(spendingActivity);
                    return spendingActivity;
                default:
                    throw new FailedToCreateActivityException();
            }
        }

        public Admin findAdminById (Long id){
            try {
                return adminRepository.findById(id).get();
            } catch (RuntimeException e) {
                throw new FailedToCreateAdminException();
            }
        }

        public void changeDefaultGoalPoints ( int points){
            Member.setDefaultGoalPoints(points);
        }

        public void changeMaxMilesValue ( int maxMilesValue){
            GameTile.setMaxMilesValue(maxMilesValue);
        }
    }
