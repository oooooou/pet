package petCom.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Recode {
    @Before( value = "petCom.aop.SystemArchitecture.dataAccessFromPet() &&"+"args(token,..)")
    public void accseeFromPet(String token){
        System.out.println("token "+token+" try to access,from PetC");
    }
    @Before( value = "petCom.aop.SystemArchitecture.dataAccessFromUser() &&"+"args(token,..)")
    public void accseeFromUser(String token){
        System.out.println("token "+token+" try to access,from UserC");
    }
}
