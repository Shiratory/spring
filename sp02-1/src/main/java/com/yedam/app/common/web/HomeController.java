package com.yedam.app.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
/* route = Endpoint(URL + Http Method)
         + Service
	     + 응답형태 (View or Data)
*/		 
public class HomeController {
  @RequestMapping("/")                       // Model = Request + Response
	public String mainPage(String message, Model model ) {
		// service 시행
		model.addAttribute("msg", message);
		return "home";
		// classpath:/templates/home.html
	}
	
	@GetMapping("/")
	public String mainPage() {
		return "home";
	}
	@PostMapping("/")
	public String mainMsgPage(String message, Model model) {
		model.addAttribute("msg", message);
		return "home";
	}
}
