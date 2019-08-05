package myhr.aspect;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	
	private final Logger log=LogManager.getLogger(LogAspect.class);

	@Pointcut("execution(* myhr.controller.*.*(..))")
	public void pcl() {
		
	}
	
	@Before(value = "pcl()")
	public void befor(JoinPoint jp) {
		String name=jp.getSignature().getName();
		log.warn(name+"方法开始执行>>>");
	}
	
	@After(value = "pcl()")
	public void after(JoinPoint jp) {
		String name=jp.getSignature().getName();
		log.warn(name+"方法结束执行<>");
	}
	
	@AfterReturning(value = "pcl()",returning = "result")
	public void afterReturn(JoinPoint jp,Object result) {
		String name=jp.getSignature().getName();
		log.error(name+"方法返回："+result);
	}
	
	@AfterThrowing(value = "pcl()",throwing = "e")
	public void aferThrowing(JoinPoint jp,Exception e) {
		
		String name=jp.getSignature().getName();
		log.warn(name+"抛出异常："+e.getMessage());
	}
	
	@Around(value = "pcl()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		return pjp.proceed();
	}
}
