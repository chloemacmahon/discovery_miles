/*
package za.ac.nwu.ac.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.repository.ActivityRepository;
import za.ac.nwu.ac.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void registerMemberTest() {
        Member member = memberService.registerMember("Test", "Test", "0000000000000", "test@test.com","T3stT3st.");
        member.createWeeklyGoals(600);
        Assertions.assertEquals(memberRepository.findByEmail("test@test.com"),member);
        memberRepository.delete(member);
    }

    @Test
    void logInMemberTest() {
        Member member = new Member("Test", "Test", "0000000000000", "test@test.com","T3stT3st.");
        memberRepository.save(member);
        Assertions.assertEquals(member,memberService.logInMember("test@test.com","T3stT3st."));
        memberRepository.delete(member);
    }

    @Test
    void updateMemberInDatabaseTest() {
    }

    @Test
    void resetWeeklyGoalsTest() {
    }

    @Test
    void viewMilesTest() {
    }
}*/
