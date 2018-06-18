package pet.db.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pet.db.PetRepository;
import pet.pojo.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcPetRepository implements PetRepository{
    private JdbcTemplate jdbcTemplate;
    JdbcPetRepository (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public Pet findByNameAndMasterID(String name, int masterId) {
        return jdbcTemplate.queryForObject("select * from pet where (name=? and masterId=?)",new PetRowMapper(),name,masterId);
    }
    @Override
    public List<Pet> findByMasterID(int masterId){
        return  jdbcTemplate.query("select * from pet where masterId=?",new Object[]{masterId},new PetRowMapper());
    }
    @Override
    public void addPet(Pet pet){
        jdbcTemplate.update("INSERT into pet (name,sex,species,age,masterId) VALUES (?,?,?,?,?)",
              pet.getName(),pet.getSex(),pet.getSpecies(),pet.getAge(),pet.getMasterId()   );
    }
    @Override
    public Long countPetByNameAndMaId(String name,Long masterId){
        return (Long )jdbcTemplate.queryForObject("select count(*) from pet WHERE (name=? and masterId=?)",
                java.lang.Long.class,name,masterId );
    }

    @Override
    public Long  deletePetByMasterIdAndId(Long masterId,Long id){
        int  a= jdbcTemplate.update("delete from Pet WHERE masterId=?and id=?",masterId,id);
        return new  Long( (long)a );
    }

    private final class PetRowMapper implements RowMapper<Pet> {
        @Override
        public Pet mapRow(ResultSet rs, int rownum) throws SQLException {
            Long id=rs.getLong("id");
            String name=rs.getString("name");
            String sex=rs.getString("sex");
            String species=rs.getString("species");
            Long age=rs.getLong("age");
            Long masterId=rs.getLong("masterId");
            Pet pet=new Pet(id,name,sex,species,age,masterId);
            return pet;
        }
    }



}
