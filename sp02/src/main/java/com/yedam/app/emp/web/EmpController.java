package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;

@Controller //route : 사용자의 요청(endpoint)와 그에 대한 처리
@RequiredArgsConstructor
public class EmpController {
	//해당 컨트롤러에서 제공하는 서비스 목록
	
	private final EmpService empService; //lombok을 사용한 주입
	
	// GET : 조회, 빈페이지, 데이터 조작(삭제)
	// POST : 데이터 조작(등록, 수정, 삭제)
	
	// 전체조회 : GET
//@RequestMapping(value = "/empList", method = {RequestMethod.GET})
	@GetMapping("empList") // 1) URI + METHOD
	public String empList(Model model) {
		// 2) 수행 기능
		List<EmpVO> list = empService.findAllList();
		// 2-1) view에 전달할 데이터 담기
		model.addAttribute("emps", list);
		
		// 3) 응답형태 : View
		return "emp/list"; //페이지 호출
		// classpath:/template/emp/list.html
		// prefix              return  suffix
	}
	
	// 단건조회 : GET => QueryString
	@GetMapping("empInfo")
	public String empInfo(EmpVO empVO, Model model) {
		//기본자료형 Integer로 전달하면
		// 객체를 만들어야되기 때문에 EmpVO로 전달하는 편이 좋다.
		EmpVO findVO = empService.findInfoById(empVO);
		
		model.addAttribute("emp", findVO);
		return "emp/info";
	}	
	
	// 등록 - 페이지 : GET
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
	}
	
	// 등록 - 처리 : POST => <form><input type='submit'/> / QueryString
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.createInfo(empVO);
		String url = null;
		if (eid > -1) {
			// 정상적으로 등록
			url = "redirect:empInfo?employeeId=" + eid;
		} else {
			url = "redirect:empList";
		}
		return url;
	}
	
	// 수정 - 페이지 : GET <=> 단건조회
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.findInfoById(empVO);
		model.addAttribute("emp", findVO);
		return "emp/update";
	}	
	
	// 수정 - 처리 : POST + AJAX + JSON
	@PostMapping("empUpdate")
	@ResponseBody //AJAX => Model 사용하지 않음
	public Map<String, Object> empUpdateProcess(@RequestBody EmpVO empVO) {
		// @RequestBody 전달받는 JSON 형식
		return empService.modifyInfo(empVO);
	}
	
	// 삭제 처리전 페이지는 없지만 회원탈퇴는 존재한다.
	//  삭제에서 중요한 데이터를 전달하면 POST로 전달한다.
	// 삭제 - 처리 : GET => QueryString
	@GetMapping("empDelete")
	public String empDelete(Integer employeeId) {
		empService.removeInfo(employeeId);
		return "redirect:empList";
	}
	
}
