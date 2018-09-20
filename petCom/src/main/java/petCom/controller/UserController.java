package petCom.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD:te/src/main/java/pet/controller/UserController.java
import pet.db.FriendRepository;
import pet.db.PetRepository;
import pet.db.UserRepository;
import pet.pojo.Friend;
import pet.pojo.Pet;
import pet.pojo.Result;
import pet.pojo.User;
import pet.service.CalDistance;
import pet.pojo.*;
import pet.service.Verify;
=======
import petCom.db.FriendRepository;
import petCom.db.PetRepository;
import petCom.db.UserRepository;
import petCom.pojo.Friend;
import petCom.pojo.Result;
import petCom.pojo.User;
import petCom.service.CalDistance;
import petCom.service.Verify;
>>>>>>> springBoot:petCom/src/main/java/petCom/controller/UserController.java

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class UserController {
    private UserRepository userRepository;

    private PetRepository petRepository;
    private FriendRepository friendRepository;
    private CalDistance calDistance;
    @Autowired
    UserController(UserRepository userRepository,PetRepository petRepository,FriendRepository friendRepository,CalDistance calDistance){
        this.userRepository=userRepository;
        this.petRepository=petRepository;
        this.friendRepository=friendRepository;
        this.calDistance=calDistance;

    }

    @RequestMapping(value ="/test",method = RequestMethod.GET)
    public String test(){
        String str=new String("test");
    //    str =redis.opsForValue().get("key");
    //    Result<java.util.List<User> > result=new Result<List<User>>();
     //   Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
     //       result.setData(   userRepository.findNearBy(1L, 1.0,2.0)       )
        return str;
    }
    //post /login
    //登陆
    //参数 json格式 User
    //返回 json格式 Result<>

    @RequestMapping(value = "/login",method= RequestMethod.POST)
    public String login(@RequestBody User user  ){
        Result<String> result=new Result<String>();
        String name=user.getName();
        String pwd=user.getPwd();
        Gson gson=new GsonBuilder().serializeNulls().create();
        Random random=new Random();
        String token = null;
        try{
            User finUser= userRepository.findByName(name);
            if(finUser.getPwd().equals(pwd) ) {
                if(Verify.isIdExisted(finUser.getId() )  ){
                    token=Verify.getToken(  finUser.getId() );
                }
                else{
                    token = String.valueOf(random.nextInt(1000000));
                    Verify.addToken(token, finUser.getId() );
                }
                result.setCode(101);
                result.setData(token);
                return gson.toJson(result);
            }
            else {
                result.setCode(100);
                result.setData(null);
                return gson.toJson(result);
            }
        }
        catch(EmptyResultDataAccessException e)
        {
            result.setCode(102);
            result.setData(null);
            return gson.toJson(result);
        }
    }


    //post /findNearBy
    //查找附近一千米用户
    //参数 head处添加token
    //返回 json格式 Result<java.util.List<User> >

    @RequestMapping(value ="/findNearBy",method = RequestMethod.POST)
    public String findNearBy(@RequestHeader String token){
        Result<List<User>> result = new Result<List<User>>();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();

        if (Verify.isTokenExisted(token)) {
            Long Id = Verify.getId(token);
            User user=userRepository.findOne(Id);
            if( (user.getLatitude()!=null)&&(user.getLongitude()!=null) ){
                List<User>temp=new ArrayList<User>();
                List<User> temp2=new ArrayList<User>();
                temp=userRepository.findNearBy(Id,user.getLatitude(),user.getLongitude());
                for(User k:temp){
                    if(calDistance.getDistance(
                            user.getLatitude(),user.getLongitude(),k.getLatitude(),k.getLongitude())<1000 ){
                        temp2.add(k);
                    }
                }
                result.setCode(201);
                result.setData( temp2);
            }
            else{
                result.setCode(203);
                result.setData(null);
            }
        }

        return gson.toJson(result);
    }

    //post /showFriend
    //显示好友
    //参数 head处添加token
    //返回 json格式 Result<java.util.List<User> >

    @RequestMapping(value ="/showFriend",method = RequestMethod.POST)
    public String showFriend(@RequestHeader String token){
        Result<List<User>> result = new Result<List<User> >();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();

        if (Verify.isTokenExisted(token)) {
            Long  id=Verify.getId(token);
            result.setCode(201);
            List<Friend> list=friendRepository.findAllFriend(id.intValue() );

            List<User> listUser=new ArrayList<User>();
            for(Friend friend:list) {
                listUser.add(userRepository.findOne(friend.getFriendId().longValue()) );
            }
            result.setData(listUser);
        }
        return gson.toJson(result);
    }

}

