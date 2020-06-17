package tn.esprit.spring.config;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	

	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

	@Before("execution(* tn.esprit.spring.service.impl.*.*(..))")
	public void logMethodEntry(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	logger.debug("In method " + name + " : ");
	}


    @Around("execution(* tn.esprit.spring.service.impl.*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint pJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object output = pJoinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
     logger.debug(pJoinPoint.getSignature() + " executed in " + executionTime + "ms");
     return output;
    }

	@After("execution(* tn.esprit.spring.service.impl.*.*(..))")
	public void logMethodExit(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	logger.debug("Out of " + name );
	}

}
