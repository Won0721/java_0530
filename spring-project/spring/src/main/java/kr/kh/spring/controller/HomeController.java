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

	// get, post �Ѵ� ����ϰ� ������ method�� �����ϰų� RequestMethod.Get, RequestMethod.Post �Է�
	@RequestMapping(value = "/")
	public String home(Model model) {
		// ȭ�鿡 �����͸� �����ϴ� ����� model �� ��Ƽ� ����
		// model.addAttributes("ȭ�鿡�� ����� �̸�",������ ��);
		model.addAttribute("name", "abc");
		return "home";
	}
	@RequestMapping(value = "/test1")
	public String text1(Model model,String id, Integer age) {
		// ȭ�鿡 �����͸� �����ϴ� ����� model �� ��Ƽ� ����
		// model.addAttributes("ȭ�鿡�� ����� �̸�",������ ��);
		model.addAttribute("name", "abc");
		System.out.println("���̵�" +id);
		System.out.println("����:" + age);
		return "testjsp";
	}
	
}
