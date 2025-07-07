package com.yedam.app.aop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.aop.mapper.AopMapper;

import lombok.RequiredArgsConstructor;

@Service //AOP(관심분리) 기능을 적용할 수 있는 대상
@RequiredArgsConstructor //final 필드 생성자
public class AopServiceImpl implements AopService {
	// Lombok을 활용한 생성자 주입
	private final AopMapper aopMapper;
	
	@Override
	@Transactional //트랜젝션AOP을 사용하는 어노테이션
	public void insert() {
//		@Transactional 속성
//		- 전파(Propagation) 속성
//		- 격리(Isolation) 레벨
//		- Read-only 속성
//		- rollback-for-예외
		
		//하나라도 실패하면 rollback처리한다.
		String[] values = {"101", "a101"};
		int result;
		
		//'101'은 삽입되는데 'a101'은 들어가지 않는다.
		for (String v : values) {
			result = aopMapper.insert(v);
			System.out.println("AOP INSERT: " + (result == 1));
		}
	}
}
