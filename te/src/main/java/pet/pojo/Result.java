package pet.pojo;

import com.google.gson.annotations.Expose;

public class Result <DATE_TYPE>{
    @Expose
    private int code;
    @Expose
    DATE_TYPE    data;
    public Result ( ){ this.code=202;this.data=null ;};
    public void setCode(int code){
        this.code=code;
    }
    public void  setData(DATE_TYPE data){
        this.data=data;
    }

    public int getCode(){
        return  code;
    }

    public DATE_TYPE getData() {
        return data;
    }
}
