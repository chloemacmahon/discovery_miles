import za.ac.nwu.ac.domain.dto.member.Member;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet resultSet, int rowNumber) throws SQLException{
        Member member = new Member();
        member.setMemberId(resultSet.getLong("member_id"));
        member.setName(resultSet.getString("name"));
        member.setSurname(resultSet.getString("surname"));
        member.setPassword(resultSet.getString("password"));
        member.setMiles(resultSet.getInt("miles"));

        return member;
    }
}
