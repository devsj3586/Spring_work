package com.fastcampus.ch2;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	
	@InitBinder  // 컨트롤러 내에서만 사용 -> 날짜 
	public void toDate(WebDataBinder binder) {  // 매개변수는  WebDataBinder로 해야함 
		ConversionService conversionService = binder.getConversionService();
		System.out.println("ConversionService = " + conversionService);
				
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		binder.registerCustomEditor(String[].class,"hobby", new StringArrayPropertyEditor("#"));  //배열일때 구분자로 문자열 잘라서 넣어주기 
		binder.setValidator(new UserValidator());  // UserValidator를 WebDataBinder의 로컬 validator로 등록 
	}
	@RequestMapping (value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST})
//	@RequestMapping ("/register/add") // 신규회원 가입 화면
//	@GetMapping ("/register/add") // 신규회원 가입 화면
	public String register() {
		return "registerForm"; // WEB-INF/views/registerForm.jsp
	}
//	
//	@RequestMapping(value = "/register/save", method = RequestMethod.POST)
	@PostMapping("/register/save")  // 4.3버전 이후 사용가능 
	public String save(@Valid User user, BindingResult result, Model m) throws Exception {
		System.out.println("result = "+ result);
		System.out.println("user = "+ user);
		
		// 수동 검증 - Validator를 직접 생성하고 , validate()를 직접 호출
//		UserValidator userValidator = new UserValidator(); 
//		userValidator.validate(user, result);  // BindingResult 는 Errors 의 자손 
		
		// User객체를 검증한 결과 에러 있으면 , registerForm를 이용해 에러를 보여줘야 함.
		if(result.hasErrors()) {
			return "registerForm";
		}
		
		// 1.유효성 검사 
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");
////			return "redirect:/register/add?msg="+msg;		// URL재작성(rewriting)
//			m.addAttribute("msg", msg); // 위 한줄과 같음 
//			return "forward:/register/add";
//		}
		
		// DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
}

