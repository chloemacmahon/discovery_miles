import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.dto.member.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class MemberDaoImplementation implements MemberDao {

    NamedParameterJdbcTemplate template;

    public MemberDaoImplementation(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Member> findAll() {
        return template.query("SELECT * from MEMBER", (RowMapper)new MemberRowMapper());
    }

    public void insertMember(Member member){
        String sql = "INSERT INTO member(id_number, name, surname, password, miles) VALUES(:id_number,:name,:surname,:password,:miles)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_number", member.getIdNumber())
                .addValue("name", member.getName())
                .addValue("surname", member.getSurname())
                .addValue("password", member.getPassword())
                .addValue("miles", member.getMiles());
        template.update(sql,param, holder);
    }

    public void updateMember(Member member){
        String sql = "UPDATE member set id_number=:id_number, name=:name, surname=:surname, password=:password, miles=:miles,";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id_number", member.getIdNumber())
                .addValue("name", member.getName())
                .addValue("surname", member.getSurname())
                .addValue("password", member.getPassword())
                .addValue("miles", member.getMiles());
        template.update(sql,param, holder);
    }


    public void executeUpdateMember(Member member){
        String sql = "UPDATE member set id_number=:id_number, name=:name, surname=:surname, password=:password, miles=:miles,";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id_number", member.getIdNumber());
        map.put("name", member.getName());
        map.put("surname", member.getSurname());
        map.put("password", member.getPassword());
        map.put("miles", member.getMiles());
        template.execute(sql, map, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement preparedStatement)
                throws SQLException, DataAccessException {
                    return preparedStatement.executeUpdate();
                }

        });
    }

    public void deleteMember(Member member){
        String sql = "DELETE FROM member WHERE member_id=:member_id";

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("member_id",member.getMemberId());
        template.execute(sql, map, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement preparedStatement)
                throws SQLException, DataAccessException {
                    return preparedStatement.executeUpdate();
                }
        });
    }
}
