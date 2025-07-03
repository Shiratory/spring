package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;
import com.yedam.app.emp.service.impl.EmpServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
// DispatcherServlet이 활용한 정보를 등록하는 Bean
// route : 사용자의 요정(endpoint)과 그에 대한 처리
// URI + HTTP Method => Service => Response(View or Data)

@RequiredArgsConstructor
public class EmpController {

    private final EmpServiceImpl empServiceImpl;
	// 해당 컨트롤러에서 제공하는 서비스 몰골
	private final EmpService empService;

	// GET : 조회, 빈페이지, 데이터 주작(삭제)
	// POST : 데이터 주작(등록, 수정, 삭제)
	
	// 전체조회 : GET
	@GetMapping("empList") // 1) URI + Method
	public String empList(Model model) {
		
		// 2) 수행 기능 => Service
		List<EmpVO> list = empService.findAllList();
		
		// 2-1) View에 전달할 데이터 담기
		model.addAttribute("emps", list);
		
		// 3) 응답 형태 : View
		return "emp/list";
		
		// classpath:/template/  emp/list  .html
		// prefix			      return    suffix
	}
	// 단건조회 : GET => QueryString
	@GetMapping("empInfo")
	public String empInfo(EmpVO empVO, Model model) {
		EmpVO findVO = empService.findInfoById(empVO);
		model.addAttribute("emp", findVO);
		return "emp/info";
	}
	
	// 등록 - 페이지 : GET
	@GetMapping("empinsert")
	public String empInsertForm() {
		return "emp/insert";
	}
	
	// 등록 - 처리 : POST => <form/> submit
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.createInfo(empVO);
		String url = null;
		if(eid > -1) {
			// 정상등록
			url = "redirect:empInfo?employeeId=" + eid;
		} else {
			// 등록되지 않음
			url = "redirect:empList";
		}
		return url;
	}
	
	// 수정 - 페이지 : GET <=> 단건조회
	@GetMapping("empInfo")
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.findInfoById(empVO);
		model.addAttribute("emp", findVO);
		return "emp/update";
	}
	
	// 수정 - 처리 : POST + AJAX + JSON
	@PostMapping("empUpdate")
	@ResponseBody // AJAX => Model 사용하지 않음
	public Map<String, Object> empUpdateProcess(@RequestBody EmpVO empVO) { // JSON
		return empService.modifyInfo(empVO);
	}
	
	// 삭제 - 처리 : GET
	@GetMapping("empDelete")
	public String empDelete(Integer employeeId) {
		empService.removeInfo(employeeId);
		return "redirect:empList";
	}
}