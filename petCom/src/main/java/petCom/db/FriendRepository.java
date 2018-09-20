package petCom.db;

import petCom.pojo.Friend;

import java.util.List;

public interface FriendRepository {
    public List<Friend> findAllFriend(Integer id);
}
