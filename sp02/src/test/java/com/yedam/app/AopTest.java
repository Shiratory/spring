package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.aop.service.AopService;

@SpringBootTest
public class AopTest {
	@Autowired
	private AopService aopService;
	//AopService는 interface이다.
	//AopSserviceImpl는 @Service이기 때문에 자동으로 감지할 수 있다.
	// AopService aopService = new AopServiceImpl();
	
	@Test
	public void aopTest() {
		aopService.insert();
		assertTrue(true); //해당 메서드에서 에러나지 않으면 성공
	}
}
