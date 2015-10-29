package cn.gorun8.easyfk.workflow.test;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AspactTest {

	@Before("execution(public * cn.gorun8.easyfk.workflow.test.TestService.*(..))")
	public void bf()
	{
		
		System.out.println("bf.....");
	}
}
