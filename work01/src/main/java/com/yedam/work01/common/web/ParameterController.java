package com.yedam.work01.common.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.work01.service.DeptVO;

@CrossOrigin(origins = "*") // cors 허용 설정
@Controller
public class ParameterController {
	// QueryString(질의문자열) : key=value&key=value&...
	// Content-type : application/x-www-form-urlencoded
	// Method : 상관없음

	// QueryString, 1) 커맨드 객체 (annotation X, 객체)
	@RequestMapping("comobj")
	@ResponseBody // AJAX
	public String commandObject(DeptVO deptVO) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t department_id : " + deptVO.getDepartmentId();
		result += "\t department_name : " + deptVO.getDepartmentName();
		return result;
	}
	
	// QueryString, 2) @RequestParam (기본타입, 단일값)
	@RequestMapping("reqparm")
	@ResponseBody
	public String requestParam
	(@RequestParam Integer departmentId,
			String departmentName) {
		String result = "";
		result += "path : /reqparm \n";
		result += "\t department_id : " + departmentId;
		result += "\t department_name : " + departmentName;
		return result;
	}
	
	// @PathVariable  : 경로에 값을 포함하는 방식, 단일 값
	// 메소드는 상관없음
	// Content-type도 상관없음
	@RequestMapping("path/{id}") // path/Hong, path/1234
	@ResponseBody
	public String pathVariable(@PathVariable String id) {
		String result = "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		return result;
	}
	
	// @RequestBody : JSON 포맷, 배열 or 객체
	// 메소드 : POST, PUT
	// Content-type : application/json
	@PostMapping("resbody")
	@ResponseBody
	public DeptVO requestBody(@RequestBody DeptVO deptVO) {
		return deptVO;
	}
	
	@PostMapping("resbodyList")
	@ResponseBody
	public List<DeptVO> resbodyList
				(@RequestBody List<DeptVO> list){
		return list;
	}
}

