package com.esprit.bankPi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogingAspet {
//
	Logger logger = LoggerFactory.getLogger(LogingAspet.class);

	@Before("execution(* com.esprit.bankPi.resources.*.*(..))")
	public void logMethodEntry(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		logger.info("In method " + name + " : ");
	}

	@After("execution(* com.esprit.bankPi.resources.*.*(..))")
	public void logMethodFinished(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		logger.info("Method " + name + " finished sucessfully ");
	}

	@Before("execution(* com.esprit.bankPi.controller.*.*(..))")
	public void logMethodEntryController(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		logger.info("In method " + name + " : ");
	}

	@After("execution(* com.esprit.bankPi.controller.*.*(..))")
	public void logMethodControllerFinished(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		logger.info("Method " + name + " finished sucessfully ");
	}
//
	@Around("execution(* com.esprit.bankPi.resources.*.*(..))")
	public Object serviceMethodeConsumedTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object obj = proceedingJoinPoint.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		logger.info("Method execution time: " + elapsedTime + " milliseconds.");
		return obj;
	}

	@Around("execution(* com.esprit.bankPi.controller.*.*(..))")
	public Object controlerMethodeConsumedTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object obj = proceedingJoinPoint.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		logger.info("Method execution time: " + elapsedTime + " milliseconds.");
		return obj;
	}
//
//	@AfterThrowing(pointcut = "execution(* *.*.*.*(..))", throwing = "ex")
//	public void afterThrowingLog(JoinPoint joinPoint, Exception ex) throws Throwable {
//		String name = joinPoint.getSignature().getName();
//		logger.error("Exception throwed in the method " + name + " exception log " + ex);
//	}
//
}
