package pet.db;

import pet.pojo.User;

import java.util.List;

public interface UserRepository {

    Long count();

    List<User> findAll();

    void sava(User user);

    User findOne(Long id);

    User findByName(String name);

    List<User> findNearBy(Long id,Double latitude,Double longitude);


}
