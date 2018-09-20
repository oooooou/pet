package petCom.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemArchitecture {
    @Pointcut("execution(* petCom.controller.PetController.*(String,..))")
    public void dataAccessFromPet() {}
    @Pointcut("execution(* petCom.controller.UserController.*(String,..))")
    public void dataAccessFromUser() {}
}
