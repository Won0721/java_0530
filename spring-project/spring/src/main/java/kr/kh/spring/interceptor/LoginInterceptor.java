package kr.kh.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.kh.spring.vo.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	// 인터셉터 : 컨트롤러에 데이터가 들어가기 전과 나올 때 가로챔
	@Override 
	public void postHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler,
		ModelAndView mv) {
		
		MemberVO user = (MemberVO)mv.getModel().get("user");
		
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user); // 회원정보 저장
		}
		
		
	}
}
