package za.ac.nwu.ac.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.activity.Activity;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.exception.*;
import za.ac.nwu.ac.repository.ActivityRepository;
import za.ac.nwu.ac.repository.MemberRepository;

import java.util.List;

@EntityScan("za.ac.nwu.ac.domain.dto")
@Component
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    private ActivityRepository activityRepository;

    public MemberServiceImpl() {
    }

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, ActivityRepository activityRepository) {
        this.memberRepository = memberRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public Member registerMember(String name, String surname, String idNumber, String email, String password) {
        if (!Validator.isValidPassword(password)) {
            throw new InvalidPasswordException();
        }
        if (!Validator.isValidID(idNumber)) {
            throw new InvalidIdNumberException();
        }
        if (!Validator.isValidEmail(email)) {
            throw new InvalidEmailException();
        }
        if (Validator.isValidName(name) && Validator.isValidName(surname)) {
            Member member = new Member(name, surname, password, idNumber, email);
            memberRepository.save(member);
            return member;
        } else {
            throw new InvalidNameException("Name contains characters other than letters");
        }
    }

    @Override
    public Member logInMember(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new FailedToLogInException();
        }
        if (member.getPassword().equals(password))
            return member;
        else {
            throw new IncorrectPasswordException();
        }
    }

    @Override
    public void updateMemberInDatabase(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void resetWeeklyGoals(Member member) {
        member.resetGoals();
        memberRepository.save(member);
    }

    @Override
    public int viewMiles(Member member) {
        return member.getMiles();
    }

    @Override
    public List<Activity> viewActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Member findMemberById(Long id) {
        try {
            return memberRepository.findById(id).get();
        } catch (RuntimeException e) {
            throw new FailedToCreateRewardPartnerException();
        }
    }
}
