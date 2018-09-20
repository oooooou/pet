package petCom.pojo;

import com.google.gson.annotations.Expose;

public class User {

    private  long id;
    @Expose
    private   String name;

    private   String pwd;
    @Expose
    private  Double latitude;
    @Expose
    private Double longitude;

    public User(){}
    public  User(String name,String pwd){
        this.pwd=pwd;
        this.name=name;
    }
    public  User(long id,String name,String pwd){
        this.id=id;
        this.pwd=pwd;
        this.name=name;
    }
    public  User(long id,String name,String pwd,Double latitude,Double longitude){
        this.id=id;
        this.pwd=pwd;
        this.name=name;
        this.latitude=latitude;
        this.longitude=longitude;
    }
    public void setId(long id){
        this.id=id;
    }
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPwd(String pwd){
        this.pwd=pwd;
    }
    public String getPwd(){
        return pwd;
    }
    public void setLatitude(Double latitude){this.latitude=latitude;}
    public void setLongitude(Double longitude){this.longitude=longitude;}

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}