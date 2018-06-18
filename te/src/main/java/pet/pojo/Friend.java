package pet.pojo;

import com.google.gson.annotations.Expose;

public class Friend {
    @Expose
    Long id;
    @Expose
    Integer searchTargetId ;
    @Expose
    Integer friendId    ;
    public Friend(){};
    public Friend(Integer searchTargetId,Integer friendId){
        this.friendId=friendId;
        this.searchTargetId=searchTargetId;
    }
    public Friend(Long id,Integer searchTargetId,Integer friendId){
        this.id=id;
        this.friendId=friendId;
        this.searchTargetId=searchTargetId;
    }
    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public Integer getSearchTargetId() {
        return searchTargetId;
    }
}
