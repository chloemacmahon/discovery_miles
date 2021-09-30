package za.ac.nwu.ac.logic;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.exception.*;
import za.ac.nwu.ac.repository.MemberRepository;

import java.sql.SQLException;

@EntityScan("za.ac.nwu.ac.domain.dto")
@Component
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    public MemberServiceImpl() {
    }

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member registerMember(String name, String surname, String idNumber, String email, String password) {
        if (Validator.isValidPassword(password)) {
            if (Validator.isValidID(idNumber)) {
                if (Validator.isValidEmail(email)) {
                    System.out.println("In register member");
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
}
