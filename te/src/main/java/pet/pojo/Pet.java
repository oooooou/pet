package pet.pojo;

import com.google.gson.annotations.Expose;

public class Pet {
    @Expose
    Long id;
    @Expose
    String name;
    @Expose
    String sex;
    @Expose
    String species;
    @Expose
    Long age;
    @Expose
    Long masterId;
    public Pet(){}

    public Pet (Long id,String name,String sex,String species,Long age,Long masterId){
        this.id=id;
        this.name=name;
        this.sex=sex;
        this.species=species;
        this.age=age;
        this.masterId=masterId;
    }
    public void setAge(Long age) {
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Long   getId(   ){
        return id ;
    }
    public String getName(){
        return  name;
    }

    public Long getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getSpecies() {
        return species;
    }

    public Long getMasterId() {
        return masterId;
    }


}
