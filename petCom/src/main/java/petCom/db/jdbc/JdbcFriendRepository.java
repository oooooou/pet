package petCom.db.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import petCom.db.FriendRepository;
import petCom.pojo.Friend;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcFriendRepository implements FriendRepository {
    private JdbcTemplate jdbcTemplate;
    JdbcFriendRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public List<Friend> findAllFriend(Integer id){
        String sql="select * from friend where searchTargetId=? ";
        return jdbcTemplate.query(sql,new PetRowMapper(),id);
    }
    private final class PetRowMapper implements RowMapper<Friend> {
        @Override
        public Friend mapRow(ResultSet rs, int rownum) throws SQLException {
            Long id=rs.getLong("id");
            Integer searchTargetId=rs.getInt("searchTargetId");
            Integer friendId=rs.getInt("friendId");

            Friend friend=new Friend(id,searchTargetId,friendId );
            return friend;
        }
    }
}
