package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
// + Bean에 등록
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
    // @Around 어느 메서드에 적용할지
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toLongString());

        try {
            Object result = joinPoint.proceed();
            return result;
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish -start;
            System.out.println("END: " + joinPoint.toLongString()+" "+timeMs+"ms");
        }
    }
}
