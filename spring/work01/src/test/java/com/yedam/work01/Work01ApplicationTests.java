package com.yedam.work01;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.work01.mapper.DeptMapper;
import com.yedam.work01.service.DeptVO;

@SpringBootTest
class Work01ApplicationTests {

	@Autowired // 필드주입:테스트용
	private DeptMapper deptMapper;
	
	@Test // 해당 메소드를 테스트하겠다고 선언
	public void selectAll() {
		List<DeptVO> list = deptMapper.selectAll();
		for(DeptVO dept : list) {
			System.out.println(dept.getDepartmentName());
		}
		
		assertTrue(!list.isEmpty());
	}
	

}
