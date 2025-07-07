package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Service //AOP(공통 기능)에 대해 지원이 가능한 Bean
         //예를 들어, @Transactional
public class EmpServiceImpl implements EmpService {
	private EmpMapper empMapper;
	
	@Autowired
	public EmpServiceImpl(EmpMapper empMapper) {
		this.empMapper = empMapper;
	}

	@Override
	public List<EmpVO> findAllList() {
		return empMapper.selectAll();
	}

	@Override
	public EmpVO findInfoById(EmpVO empVO) {
		return empMapper.selectInfo(empVO);
	}

	@Override
	public int createInfo(EmpVO empVO) {
		int result = empMapper.insertInfo(empVO);
		return result == 1 ? empVO.getEmployeeId() : -1;
		//정상적으로 되었으면 employee_id를 리턴;
		// 실패되면 -1
	}

	@Override
	//AJAX를 위해 Map<>으로 전달
	public Map<String, Object> modifyInfo(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = empMapper.updateInfo(empVO.getEmployeeId(), empVO);
		isSuccessed = result == 1;
		
		map.put("result", isSuccessed);
		map.put("target", empVO);
		/* // map data
		 * {
		 *   "result" : true,
		 *   "target" : { "employeeId" : 100, "lastName" : "king", ... }
		 * }
		 */
		return map;
	}

	@Override
	public Map<String, Object> removeInfo(int empId) {
		Map<String, Object> map = new HashMap<>();
		
		int result = empMapper.deleteInfo(empId);
		if (result == 1) {
			map.put("employeeId", empId);
		}
		
		/* // map data
		 * {} // result = 0
		 * or
		 * { "employeeId": 104 } // result = 1
		 */
		return map;
	}
}
