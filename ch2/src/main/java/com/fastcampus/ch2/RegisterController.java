package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	@RequestMapping (value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST})
//	@RequestMapping ("/register/add") // 신규회원 가입 화면
//	@GetMapping ("/register/add") // 신규회원 가입 화면
	public String register() {
		return "registerForm"; // WEB-INF/views/registerForm.jsp
	}
//	
//	@RequestMapping(value = "/register/save", method = RequestMethod.POST)
	@PostMapping("/register/save")  // 4.3버전 이후 사용가능 
	public String save(User user, Model m) throws Exception {
		// 1.유효성 검사 
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");
//			return "redirect:/register/add?msg="+msg;		// URL재작성(rewriting)
			m.addAttribute("msg", msg); // 위 한줄과 같음 
			return "forward:/register/add";
		}
		
		// DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return false;
	}
}

