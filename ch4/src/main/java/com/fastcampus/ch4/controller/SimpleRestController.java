package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
//@RestController
@Controller
public class SimpleRestController {
    @GetMapping("/ajax")
    public String ajax() {
        return "ajax";
    }


    @PostMapping("/send")
    @ResponseBody       // ResponseBody 응답  RestController 있으면 생략가능
    public Person test(@RequestBody Person p) {  // @RequestBody 로 받아 객체에 저장
        System.out.println("p = " + p);
        p.setName("ABC");
        p.setAge(p.getAge() + 10);

        return p;  // JSON 객체 반환 , 뷰이름 X
    }
}