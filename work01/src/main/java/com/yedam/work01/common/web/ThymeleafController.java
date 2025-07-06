package com.yedam.work01.common.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.work01.service.DeptService;
import com.yedam.work01.service.DeptVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ThymeleafController {

	private final DeptService deptService;
	
	@GetMapping("thymeleaf")
	public String thymeleafTest(Model model) {
		DeptVO deptVO = DeptVO.builder()
							  .departmentId(10)
							  .build();
		DeptVO findVO = deptService.findInfoById(deptVO);
		model.addAttribute("deptInfo", findVO);
		
		List<DeptVO> list = deptService.findAllList();
		model.addAttribute("deptList", list);
		return "test";
	}
}
