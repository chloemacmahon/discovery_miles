/*
package za.ac.nwu.ac.logic;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.repository.MemberRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class MemberServiceTest {

    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @Before
    void init() {
        MockitoAnnotations.initMocks(this);
        memberService = new MemberServiceImpl(memberRepository);
    }

    @Test
    void registerMemberTest() {
        Member member = new Member();
        when(memberService.registerMember(any(Member.class))).thenReturn(null);
        Assertions.assertEquals();
        Assertions.assertEquals(member,memberRepository.findByEmail("test@test.com"));
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
}
*/
