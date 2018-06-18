package pet.controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pet.db.FriendRepository;
import pet.db.PetRepository;
import pet.db.UserRepository;
import pet.pojo.Friend;
import pet.pojo.Pet;
import pet.pojo.Result;
import pet.pojo.User;
import pet.service.CalDistance;
import pet.pojo.*;

import java.util.*;
import java.util.List;

@Controller
public class UserController {
    private UserRepository userRepository;
    Map<String,Long> tokenMap;   //token id
    private PetRepository petRepository;
    private FriendRepository friendRepository;
    private CalDistance calDistance;
    @Autowired
    UserController(UserRepository userRepository,PetRepository petRepository,FriendRepository friendRepository,CalDistance calDistance){
        this.userRepository=userRepository;
        this.petRepository=petRepository;
        this.friendRepository=friendRepository;
        this.calDistance=calDistance;
        tokenMap=new HashMap<String,Long>();
    }


    @ResponseBody
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
    @ResponseBody
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
            System.out.println(tokenMap);
            if(finUser.getPwd().equals(pwd) ) {
                if(tokenMap.containsValue(finUser.getId() )  ){
                    for (String key : tokenMap.keySet()) {
                        if(tokenMap.get(key)==finUser.getId() )
                            token=key;
                    }
                }
                else{
                    token = String.valueOf(random.nextInt(1000000));
                    tokenMap.put(token, finUser.getId() );
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

    //post /query
    //查询宠物消息
    //参数 head处添加token
    //返回 json格式 Result<java.util.List<Pet> >
    @ResponseBody
    @RequestMapping(value ="/query",method = RequestMethod.POST)
    public String QuyPet(@RequestHeader String token){
        Result<java.util.List<Pet> > result=new Result<List<Pet>>();
        Gson gson=new GsonBuilder().serializeNulls().create();
        if(tokenMap.containsKey(token)) {
            Long masterId = tokenMap.get(token);
            try {
                java.util.List<Pet> list = petRepository.findByMasterID( Math.toIntExact(masterId));
                result.setCode(201);
                result.setData(list);
            } catch (EmptyResultDataAccessException e) {
                result.setCode(200);
                result.setData(null);

            }
        }
        else {
            result.setCode(202);
            result.setData(null);
        }
        return gson.toJson(result);
    }

    //post /addPet
    //添加宠物消息
    //参数 head处添加token
    //返回 json格式 Result<java.util.List<Pet> >
    @ResponseBody
    @RequestMapping(value ="/addPet",method = RequestMethod.POST)
    public String AddPet(@RequestHeader String token,@RequestBody Pet pet) {
        Result<java.util.List<Pet>> result = new Result<List<Pet>>();
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (tokenMap.containsKey(token)) {
            Long masterId = tokenMap.get(token);
            pet.setMasterId(masterId);
            if (petRepository.countPetByNameAndMaId(pet.getName(), pet.getMasterId() ) ==0 ) {
                petRepository.addPet(pet);
                result.setCode(201);
                result.setData(null);
            } else {
                result.setCode(203);
                result.setData(null);
            }
        } else {
            result.setCode(202);
            result.setData(null);
        }
        return gson.toJson(result);
    }

    //post /findNearBy
    //查找附近一千米用户
    //参数 head处添加token
    //返回 json格式 Result<java.util.List<User> >
    @ResponseBody
    @RequestMapping(value ="/findNearBy",method = RequestMethod.POST)
    public String findNearBy(@RequestHeader String token){
        Result<java.util.List<User>> result = new Result<List<User>>();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
        if (tokenMap.containsKey(token)) {
            Long Id = tokenMap.get(token);
            User user=userRepository.findOne(Id);
            if( (user.getLatitude()!=null)&&(user.getLongitude()!=null) ){
                java.util.List<User>temp=new ArrayList<User>();
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
        else{
            result.setCode(202);
            result.setData(null);
        }
        return gson.toJson(result);
    }

    //post /showFriend
    //显示好友
    //参数 head处添加token
    //返回 json格式 Result<java.util.List<User> >
    @ResponseBody
    @RequestMapping(value ="/showFriend",method = RequestMethod.POST)
    public String showFriend(@RequestHeader String token){
        Result<java.util.List<User>> result = new Result<List<User> >();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
        if (tokenMap.containsKey(token)) {
            Long  id=tokenMap.get(token);
            result.setCode(201);
            List<Friend> list=friendRepository.findAllFriend(id.intValue() );

            List<User> listUser=new ArrayList<User>();
            for(Friend friend:list) {
                listUser.add(userRepository.findOne(friend.getFriendId().longValue()) );
            }
            result.setData(listUser);
        }else{
            result.setCode(202);
            result.setData(null);
        }
        return gson.toJson(result);
    }

    //post /deletePet
    //删除宠物
    //参数 head处添加token
    //返回 json格式 Result<Long> data部分为空
    @ResponseBody
    @RequestMapping(value ="/deletePet",method = RequestMethod.POST)
    public String deletePet(@RequestHeader String token,@RequestParam Long id){
        Result<Long> result = new Result<Long>();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
        if (tokenMap.containsKey(token)) {
            Long  masterId=tokenMap.get(token);
            Long temp=petRepository.deletePetByMasterIdAndId(masterId,id);
            if(temp==0) result.setCode(203);
            else result.setCode(201);
        }else{
            result.setCode(202);
            result.setData(null);
        }
        result.setData(null);
        return gson.toJson(result);
    }

}

