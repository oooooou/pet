package petCom.db.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import petCom.db.UserRepository;
import petCom.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class JdbcUserRepository implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public Long count() {
        return (Long )jdbcTemplate.queryForObject("select count(id) from user",Long.class);
    }

    public List<User> findAll(){
        String sql = "select * from user";
        return jdbcTemplate.query(sql,new UserRowMapper());
    }
    private final class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rownum) throws SQLException {
            Long id=rs.getLong("id");
            String name=rs.getString("name");
            String pwd=rs.getString("pwd");
            Double latitude=rs.getDouble("latitude");
            Double longitude=rs.getDouble("longitude");

            User user=new User(id,name,pwd,latitude,longitude);
            return user;
        }
    }

    @Override
    public User findByName(String name) {
        return jdbcTemplate.queryForObject("select * from user where name=?",new UserRowMapper(),name);
    }

    @Override
    public User findOne(Long id) {
        return jdbcTemplate.queryForObject("select * from user where id=?",new UserRowMapper(),id);
    }

    @Override
    public void sava(User user) {
        Long id=user.getId();
        if(id==null){
            jdbcTemplate.update("INSERT into user (name,pwd) VALUES (?,?)",user.getName(),user.getPwd());
        }
        else{

        }
    }
    @Override
    public List<User> findNearBy (Long id,Double latitude,Double longitude){
        String sql = "select * from user WHERE  abs(latitude +longitude-?-? )<? AND  id!=?";
        return jdbcTemplate.query(sql,new UserRowMapper(),latitude,longitude,0.1,id);
    }
}
