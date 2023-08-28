package kr.kh.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	// get, post 둘다 사용하고 싶으면 method를 삭제하거나 RequestMethod.Get, RequestMethod.Post 입력
	@RequestMapping(value = "/")
	public String home(Model model) {
		// 화면에 데이터를 전송하는 방법은 model 에 담아서 전송
		// model.addAttributes("화면에서 사용할 이름",전송할 값);
		model.addAttribute("name", "abc");
		return "home";
	}
	@RequestMapping(value = "/test1")
	public String text1(Model model,String id, Integer age) {
		// 화면에 데이터를 전송하는 방법은 model 에 담아서 전송
		// model.addAttributes("화면에서 사용할 이름",전송할 값);
		model.addAttribute("name", "abc");
		System.out.println("아이디" +id);
		System.out.println("나이:" + age);
		return "testjsp";
	}
	
}
