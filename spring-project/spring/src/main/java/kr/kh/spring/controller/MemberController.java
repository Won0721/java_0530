package kr.kh.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.kh.spring.service.MemberService;
// controller 어노테이션을 추가해야 컨트롤러로 인식함
@Controller
public class MemberController {

	@Autowired
	private MemberService memberService; 
	
	// 회원가입 페이지 연결
	@RequestMapping(value ="/signup", method =RequestMethod.GET)
	public String signup() {
		// member폴더 밑에 signup
		int count = memberService.count();
		System.out.println(count);
		return "member/signup";
	}
}
