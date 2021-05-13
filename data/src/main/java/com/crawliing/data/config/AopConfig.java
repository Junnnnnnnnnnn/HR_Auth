package com.crawliing.data.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

@Component
@Aspect
public class AopConfig {
    
    @Around("within(com.crawliing.data.*.controller.*)")
    public Object testAop(ProceedingJoinPoint pjp) throws Throwable{
        Logger log = (Logger) LoggerFactory.getLogger(pjp.getTarget().getClass());
        log.info("/{}",pjp.getSignature().getName());


        return pjp.proceed();
    }
}
