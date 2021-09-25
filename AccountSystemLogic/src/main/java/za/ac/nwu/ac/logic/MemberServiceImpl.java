package za.ac.nwu.ac.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.exception.IncorrectPasswordException;
import za.ac.nwu.ac.domain.exception.InvalidEmailException;
import za.ac.nwu.ac.domain.exception.InvalidIdNumberException;
import za.ac.nwu.ac.domain.exception.InvalidPasswordException;
import za.ac.nwu.ac.repository.MemberRepository;

@Component
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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
        if (member.getPassword().equals(password))
            return member;
        else {
            throw new IncorrectPasswordException();
        }
    }

    @Override
    public void resetWeeklyGoals(Member member) {
        member.resetGoals();
    }
}
