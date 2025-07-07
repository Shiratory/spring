package com.yedam.app.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/*
 * // end_point : 최종적으로 도달해야하는 경로
 * route = end_point(URL + http method)
 *         + Service
 *         + 응답형태(view or data)
 * @See DispatcherServlet 이 활용한 정보를 등록하는 Bean
 * route : 사용자의 요청(end_point)와 그에 대한 처리
 *   URI + HTTP METHOD => Service => Response(View or Data[JSON])
 */
@Controller //HandlerMapping
public class HomeController {
	//해당 컨트롤러에서 제공하는 서비스 목록
	
	//@RequestMapping("/") //route 경로
	//@RequestMapping는 모든메소드를 허용(get, put, post, delete)
	// 그래서 사용을 하지 않는 편이 좋다.
	public String mainPage(String message, Model model) {
		//message는 Request로부터 받는 데이터.
		//model은 'Spring고유의 객체' Response로 데이터를 보낼때 많이 사용한다.
		// Model = Request + Response
		
		//service 실행
		model.addAttribute("msg", message); //Model
		//req.setAttribute("msg", message); //HttpServletRequest
		
		// HttpServletRequest
		//req.getRequestDispatcher("/view/home.jsp").forward(req, res);
		// Spring
		//return "home"; //'thymeleaf'가 경로를 만들어 준다.
		
		// HttpServletResponse
		//response.sendRedirect("/view/homeList.jsp);
		// Spring
		//return "redirect:homeList"; //return에 'redirect'를 추가한다.
		
		return "home"; //페이지이름을 반환
		// 'classpath:/templates/home.html'로 최종 변환된다.
		// 'classpath:/templates/'는 공통 경로이다.
	}
	
	//@RequestMapping()을 사용하지 않고 나눠서 작업한다.
	
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
