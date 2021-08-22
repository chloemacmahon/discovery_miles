package session;

import Database.Database;
import container.MemberBehaviour;
import goal.HealthGoal;
import member.Member;

public class Session {
    private int memberID;
    private Member member;
    private MemberBehaviour memberBehaviour;

    public Session(int memberID, Member member) {
        this.memberID = memberID;
        this.member = member;
        this.memberBehaviour = new MemberBehaviour(member);
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public MemberBehaviour getMemberBehaviour() {
        return memberBehaviour;
    }

    public void setMemberBehaviour(MemberBehaviour memberBehaviour) {
        this.memberBehaviour = memberBehaviour;
    }

    @Override
    public String toString() {
        return "Session{" +
                "memberID=" + memberID +
                ", member=" + member +
                ", memberBehaviour=" + memberBehaviour +
                '}';
    }
}
