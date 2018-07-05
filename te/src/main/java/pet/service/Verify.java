package pet.service;


import java.util.HashMap;
import java.util.Map;

public class Verify {
    private static Map<String,Long>  tokenMap=new HashMap<String,Long>();
    public static Long getId(String Token){
        return tokenMap.get(Token);
    }
    public static void  addToken(String token,Long id){
        tokenMap.put(token,id);
    }
    public static String getToken(Long id){
        for (String key : tokenMap.keySet()) {
            if(tokenMap.get(key).equals(id  ))
                return  key;
        }
        return  null;
    }

    public static Boolean isTokenExisted(String token){
        return tokenMap.containsKey(token);
    }
    public static Boolean isIdExisted(Long id){
        return tokenMap.containsValue(id);
    }
}
