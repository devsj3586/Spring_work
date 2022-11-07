package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {
	
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})  // 하나의 메서드로 여러개 예외 처리할땐 배열 {,}로 처리
	public String catcher2(Exception ex, Model m) {
		m.addAttribute("ex", ex);
		return "error";
	} 
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 200번 상태코드를 -> 500번으로 변경 
	public String catcher(Exception ex, Model m) {
		System.out.println("catcher in ExceptionController");
//		m.addAttribute("ex", ex);
		return "error";
	} // catch 블럭 이라 생각 하면 됨 
	 
	@RequestMapping("/ex")
	public String main() throws Exception {
		throw new Exception("예외가 발생했습니다");
	}
	@RequestMapping("/ex2")
	public String main2() throws Exception {
		throw new FileNotFoundException(" 예외가 발생했습니다");
	}
}
