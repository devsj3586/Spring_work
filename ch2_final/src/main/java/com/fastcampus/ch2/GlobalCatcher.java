package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.fastcampus.ch3") // ������ ��Ű������ �߻��� ���ܸ� ó��
//	@ControllerAdvice // ��� ��Ű���� ����
public class GlobalCatcher {
    @ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
    public String catcher2(Exception ex, Model m) {
        m.addAttribute("ex", ex);
		printEx(ex);
		return "error";
    }


	private static void printEx(Exception ex) {
		System.out.println("ex = " + ex);
		System.out.println("ex = " + ex);
		System.out.println("ex = " + ex);
	}

	@ExceptionHandler(Exception.class)
    public String catcher(Exception ex, Model m) {
		m.addAttribute("ex", ex);
		return "error";
    }
}
