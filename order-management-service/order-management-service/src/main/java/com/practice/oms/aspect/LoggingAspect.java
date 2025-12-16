package com.practice.oms.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * LoggingAspect
 *
 * <p>
 * Logs execution details of service layer methods
 * without polluting business logic.
 * </p>
 * @author Rajan kumar
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * Pointcut for all service layer methods.
     */
    @Pointcut("execution(* com.practice.oms.service..*(..))")
    public void serviceLayer(){

    }

    /**
     * Logs method execution time.
     */
    @Around("serviceLayer()")
    public Object logExecutiontime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("➡️ Entering method: {}",
                joinPoint.getSignature().toShortString());
        Object result = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - start;
        log.info("⬅️ Exiting method: {} | Time Taken: {} ms",
                joinPoint.getSignature().toShortString(),
                timeTaken);
        return result;
    }
}
