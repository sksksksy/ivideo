package video.info.service.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import video.info.service.utils.CommonUtilInModule;
import video.manage.model.dto.request.QueryVideoDto;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 请求校验
 */
@Aspect
@Component
public class RequestCheck {

    private Map<String, String> methods = new HashMap<String, String>();

    RequestCheck() {
        methods.put("", "1");
    }

    @Pointcut("execution(public * video.info.service.controller.*.*(..))")
    public void requestCheck() {
    }

    /**
     * 不知道有没有用
     *
     * @param joinPoint
     */
    @Before("requestCheck()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        StringJoiner joiner = new StringJoiner(".");
        joiner.add(joinPoint.getSignature().getDeclaringTypeName());
        joiner.add(joinPoint.getSignature().getName());
        System.out.println("方法名：" + joiner.toString());
        if (methods.getOrDefault(joiner.toString(), null) != null) {
            QueryVideoDto query = (QueryVideoDto) joinPoint.getArgs()[0];
            CommonUtilInModule.checkQueryVideo(query);
        }
        System.out.println("doBefore");
    }

//    @After("requestCheck()")
//    public void doAfter(JoinPoint joinPoint) {
//        System.out.println("doAfter");
//    }
//
//    @AfterReturning("requestCheck()")
//    public void doAfterReturning(JoinPoint joinPoint) {
//        System.out.println("doAfterReturning");
//    }
//
//    @AfterThrowing("requestCheck()")
//    public void deAfterThrowing(JoinPoint joinPoint) {
//        System.out.println("deAfterThrowing");
//    }
//
//    @Around("requestCheck()")
//    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("deAround");
//        return joinPoint.proceed();
//    }
}
