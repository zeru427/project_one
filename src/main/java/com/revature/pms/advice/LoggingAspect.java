package com.revature.pms.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.revature.pms.services.*.*(..))")
    public void doLog() {
        logger.info("2.###I am doing the before logging from aspect");
    }

    @After("execution(* com.revature.pms.services.*.*(..))")
    public void doSecurityCheck() {
        logger.info("3.###I am doing after security checking");
    }

}
