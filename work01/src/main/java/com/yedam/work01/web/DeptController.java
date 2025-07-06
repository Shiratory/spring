package com.yedam.work01.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.work01.service.DeptService;
import com.yedam.work01.service.DeptVO;
import com.yedam.work01.service.impl.DeptServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	private final DeptServiceImpl deptServiceImpl;
	
	// GET : 조회, 데이터 조작(삭제)
	// POST : 데이터 조작(등록, 수정, 삭제)
	
	// 전체조회 : GET
	@GetMapping("deptList")
	public String DeptList(Model model) {
		List<DeptVO> list = deptService.findAllList();
		
		model.addAttribute("depts", list);
		return "dept/list";
	}
	// 단건조회 : GET => QueryString
	@GetMapping("deptInfo")
	public String deptInfo(DeptVO deptvo, Model model) {
		DeptVO findVO = deptService.findInfoById(deptvo);
		model.addAttribute("dept", findVO);
		return "dept/info";
	}
	
	// 등록 - 페이지 : GET
	@GetMapping("deptinsert")
	public String deptInsertForm() {
		return "dept/insert";
	}
	
	// 등록 - 처리 : POST => <form/> submit
	@PostMapping("deptInsert")
	public String deptInsertProcess(DeptVO deptVO) {
		int did = deptService.createInfo(deptVO);
		String url = null;
		if(did > -1) {
			url = "redirect:deptInfo?departmentId=" + did;
		} else {
			url = "redirect:deptList";
		}
		return url;
	}
	
	// 수정 - 페이지 : GET <=> 단건조회
	@GetMapping("deptUpdate")
	public String deptUpdateForm(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.findInfoById(deptVO);
		model.addAttribute("dept", findVO);
		return "dept/update";
	}
	
	// 수정 - 처리 : POST + AJAX + JSON
	@PostMapping("deptUpdate")
	@ResponseBody
	public Map<String, Object> deptUpdateProcess(@RequestBody DeptVO deptVO){
		return deptService.modifyInfo(deptVO);
	}
	
	// 삭제 - 처리 : GET
	@GetMapping("deptDelete")
	public String deptDelete(Integer departmentId) {
		deptService.removeInfo(departmentId);
		return "redirect:deptList";
	}
}
