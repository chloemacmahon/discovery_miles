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
public class MemberServiceImpl implements MemberService{

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
        if (Validator.isValidPassword(password)) {
            if (Validator.isValidID(idNumber)) {
                if (Validator.isValidEmail(email)) {
                    Member member = new Member(name, surname, password, idNumber, email);
                    memberRepository.save(member);
                    return member;
                }else
                    throw new InvalidEmailException();
            }else
                throw new InvalidIdNumberException();
        }else
            throw new InvalidPasswordException();
    }

    @Override
    public Member logInMember(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if (member == null){
            throw new FailedToLogInException();
        }
        if (member.getPassword().equals(password))
            return member;
        else {
            throw new IncorrectPasswordException();
        }
    }

    @Override
    public void updateMemberInDatabase(Member member){
        memberRepository.save(member);
    }

    @Override
    public void resetWeeklyGoals(Member member) {
        member.resetGoals();
    }

    @Override
    public int viewMiles(Member member) {return member.getMiles();}

    @Override
    public List<Activity> viewActivities(){
        return activityRepository.findAll();
    }
}
