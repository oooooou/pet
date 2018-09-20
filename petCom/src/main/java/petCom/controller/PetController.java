package pet.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pet.db.FriendRepository;
import pet.db.PetRepository;
import pet.db.UserRepository;
import pet.pojo.Pet;
import pet.pojo.Result;
import pet.service.CalDistance;
import pet.service.Verify;

import java.util.List;
<<<<<<< HEAD
@Controller

=======

@RestController
>>>>>>> springBoot
public class PetController {
    private UserRepository userRepository;

    private PetRepository petRepository;
    @Autowired
    PetController(UserRepository userRepository, PetRepository petRepository   ){
        this.userRepository=userRepository;
        this.petRepository=petRepository;
    }
    //post /query
    //查询宠物消息
    //参数 head处添加token
    //返回 json格式 Result<java.util.List<Pet> >
    @RequestMapping(value ="/query",method = RequestMethod.POST)
    @Cacheable(value = "Pet",key="#token")
    public String QuyPet(@RequestHeader String token){
            Result<List<Pet> > result=new Result<List<Pet>>();
        Gson gson=new GsonBuilder().serializeNulls().create();
        if(Verify.isTokenExisted(token)) {
            Long masterId = Verify.getId(token);
            try {
                java.util.List<Pet> list = petRepository.findByMasterID( Math.toIntExact(masterId));
                result.setCode(201);
                result.setData(list);
            } catch (EmptyResultDataAccessException e) {
                result.setCode(200);
                result.setData(null);
            }
        }
        return gson.toJson(result);
    }

    //post /addPet
    //添加宠物消息
    //参数 head处添加token
    //返回 json格式 Result<java.util.List<Pet> >
    @ResponseBody
    @RequestMapping(value ="/addPet",method = RequestMethod.POST)
<<<<<<< HEAD
    public String AddPet(@RequestHeader String token,@RequestBody Pet pet) {
        Result<java.util.List<Pet>> result = new Result<List<Pet>>();
=======
    @CacheEvict(value = "Pet",key="#token"  )
    public String AddPet(@RequestHeader String token, @RequestBody Pet pet) {
        Result<List<Pet>> result = new Result<List<Pet>>();
>>>>>>> springBoot

        Gson gson = new GsonBuilder().serializeNulls().create();
        if (Verify.isTokenExisted(token)) {
            Long masterId = Verify.getId(token);
            pet.setMasterId(masterId);
            if (petRepository.countPetByNameAndMaId(pet.getName(), pet.getMasterId() ) ==0 ) {
                petRepository.addPet(pet);
                result.setCode(201);
                result.setData(null);
            } else {
                result.setCode(203);
                result.setData(null);
            }
        }
        return gson.toJson(result);
    }

    //post /deletePet
    //删除宠物
    //参数 head处添加token
    //返回 json格式 Result<Long> data部分为空
    @ResponseBody

    @RequestMapping(value ="/deletePet",method = RequestMethod.POST)
<<<<<<< HEAD
    public String deletePet(@RequestHeader String token,@RequestParam Long id){
=======
    @CacheEvict(value = "Pet",key="#token"  )
    public String deletePet(@RequestHeader String token, @RequestParam Long id){
>>>>>>> springBoot
        Result<Long> result = new Result<Long>();

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
        if ( Verify.isTokenExisted(token)) {
            Long  masterId=Verify.getId(token);
            Long temp=petRepository.deletePetByMasterIdAndId(masterId,id);
            if(temp==0) result.setCode(203);
            else result.setCode(201);
        }
        return gson.toJson(result);
    }
}
