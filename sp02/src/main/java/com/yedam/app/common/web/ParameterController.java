package com.yedam.app.common.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@CrossOrigin(origins = "*") //cors 설정
@Controller
public class ParameterController {
	// QueryString(질의문자열) : ?key=value&key=value&...
	// Content-type : application/x-www-form-urlencoded
	// Method : 상관없음
	
	// QueryString, 1) 커맨드 객체 (객체, 어노테이션을 사용하지 않는)
	@ResponseBody // AJAX위한 페이지(순수데이터를 통신하기 위해 사용하는 어노테이션)
	@RequestMapping(value = "comobj", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
	// QueryString 2) @RequestParam (기본타입, 단일값)
	@ResponseBody
	@RequestMapping(value = "reqparm", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
	public String requestParam(@RequestParam Integer employeeId,
			                 String lastName) {
		//Parameter Type은 'Wrapper class'를 사용해야한다.
		// 값이 없으면 null값이 들어가야되기 때문에.
		// 만약 필수값이 없으면 http status: 400 'Bad Request'
		String result = "";
		result += "path : /reqparm \n";
		result += "\t employee_id : " + employeeId;
		result += "\t last_name : " + lastName;
		return result;
	}
	
	// @RequestPart : MultipartFile //멀티 미디어 파일
	
	// @PathVariable : 경로에 값을 포함하는 방식, 단일 값
	// Content-type : 상관없음
	// Method : 상관없음
	
	@ResponseBody
	@RequestMapping(value = "path/{id}", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
	public String pathVariable(@PathVariable String id) {
	  //path: path/Hong, path/1234
		//'{}'중괄호 안에 사용되는 이름이랑 변수랑 같아야한다.
		//값이 없으면 이 경로가 아니라 '/path'경로로 이동한다.
		String result = "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		return result;
	}
	
	// @RequestBody : JSON 포맷, 배열 or 객체
	// Content-type : application/json
	// Method : POST, PUT
	
	@ResponseBody
	@PostMapping("resbody")
	public EmpVO resuqestBody(@RequestBody EmpVO empVO) {
		//@RequestBody : 사용자가 보낸 JSON 객체(XML도 가능하다.)
		//@ResponseBody : 서버가 보내는 JSON 객체
		return empVO;
	}
	
	@ResponseBody
	@PostMapping("resbodyList")
	public List<EmpVO> resuqestBody(@RequestBody List<EmpVO> empVO) {
		//배열도 가능하다.
		//serialize : 직렬화 (객체 -> 문자열)
		//            toString(), JSON.stringify()
		//deserialize : 역직력화 (문자열 -> 객체)
		//            JSON.parse()
		return empVO;
	}
	
	//리턴 타입
	// String, ModelAndView, void //view로 이동.
	// @ResponseBody //데이터(JSON)를 전달.
	// ResponseEntity<> // header를 건들때 사용.
}
