package com.yedam.app;

//import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
class Sp02ApplicationTests {
	//필드 주입은 사용 하지 않는 편이 좋다.
	//테스트라서 사용한다.
	@Autowired //필드 주입 : 테스트용
	private EmpMapper empMapper;
	
	@Test //해당 메서드를 테스트를 하는 어노테이션
	public void selectAll() {
		List<EmpVO> list = empMapper.selectAll();
		for (EmpVO emp : list) {
			System.out.println(emp.getLastName());
		}
		
		Assertions.assertTrue(!list.isEmpty()); //일치하면 성공
	}
	
	@Test
	public void selectOne() {
		//빌더패턴을 사용해서 EmpVO 생성
		EmpVO emp = EmpVO.builder()
				.employeeId(100)
				.build();
		EmpVO findVO = empMapper.selectInfo(emp);
		System.out.println(findVO);
		
		Assertions.assertEquals("King", findVO.getLastName()); //일치하면 성공
	}
	
	//@Test
	@Deprecated
	public void insertValue() {
		EmpVO emp = EmpVO.builder()
				.employeeId(1000)
				.lastName("Kang")
				.email("kg@google.com")
				.jobId("SA_REP")
				.build();
		int result = empMapper.insertInfo(emp);
		
		Assertions.assertEquals(1, result); //일치하면 성공
	}
	
	//@Test
	public void insertSelectKey() {
		EmpVO emp = EmpVO.builder()
				.lastName("Hong")
				.email("hong@naver.com")
				.jobId("IT_PROG")
				.salary(1200.0)
				.build();
		int result = empMapper.insertInfo(emp);
		System.out.println("사원번호 : " + emp.getEmployeeId());
		
		Assertions.assertEquals(1, result);
	}
	
	@Test
	public void updeteInfo() {
		//단건조회(EmpVO) -> EmpVO수정 -> 데이블에 업데이트
		
		// 1) 단건조회
		EmpVO emp = EmpVO.builder()
				.employeeId(4322)
				.build();
		EmpVO findVO = empMapper.selectInfo(emp);
		// 2) 값 변경
		findVO.setSalary(2550.0);
		// 3) 테이블에 업데이트
		int result = empMapper.updateInfo(emp.getEmployeeId(), findVO);
				
		Assertions.assertEquals(1, result);
	}
}
