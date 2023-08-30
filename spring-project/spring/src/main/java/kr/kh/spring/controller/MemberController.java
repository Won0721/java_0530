package kr.kh.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;

import kr.kh.spring.service.MemberService;
import kr.kh.spring.util.Message;
import kr.kh.spring.vo.MemberVO;
// controller 어노테이션을 추가해야 컨트롤러로 인식함

@Controller
public class MemberController {

	@Autowired // 의존성 중립 :서블릿에서는 Autowired 없으면 객체를 생성했어야함
	private MemberService memberService;
	
	
	// RequestMapping : url 찾아주는 역할
	@RequestMapping(value ="/member/signup", method =RequestMethod.GET)
	public String signup() {
		// member폴더 밑에 signup
		return "member/signup";
	}
	
	@GetMapping(value = "/member/login")
	public String memberLogin() {
		return "member/login";
	}
	
	@RequestMapping(value ="/member/signup", method =RequestMethod.POST)
	public String signupPost(MemberVO member, Model model) {
		Message msg = new Message("/member/signup","회원 가입에 실패 했습니다.");

		if(memberService.signup(member)) {
			msg = new Message("/", "회원 가입에 성공했습니다.");
		}
		model.addAttribute("msg", msg);
		return "message";
	}


	@PostMapping(value = "/member/login")
	public String memberLoginPost(MemberVO member, Model model) {
		Message msg = new Message("/member/login", "로그인에 실패했습니다.");
		MemberVO user = memberService.login(member);
		if(user != null) {
			msg = new Message("/", "로그인에 성공했습니다.");
		}
		
		model.addAttribute("msg", msg);
		return "message";
	}
	@GetMapping("/member/logout")
	public String memberLogout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		Message msg = new Message("/", null);
		if(user != null) {
			session.removeAttribute("user");
			msg.setMsg("로그아웃에 성공했습니다.");
		}
		model.addAttribute("msg", msg);
		return "message";
	}
}
