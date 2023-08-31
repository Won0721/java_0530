package kr.kh.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.kh.spring.service.BoardService;
import kr.kh.spring.vo.BoardVO;
import kr.kh.spring.vo.MemberVO;

@Controller
@RequestMapping("/board")
public class BoardController {

		@Autowired
		BoardService boardService;
		
		@GetMapping("/list")
		public String list() {
			return "/board/list";
		}
		
		@GetMapping("/insert")
		public String insert(HttpServletRequest request) {
			// 매개변수에 HttpSession session으로 바로 가져올수있다.
			HttpSession session = request.getSession();
			MemberVO user = (MemberVO)session.getAttribute("user");
			if(user == null) {
				return "/member/login";
			}
			return "/board/insert";
		}
		@PostMapping("/insert")
		public String InsertPost(BoardVO board, HttpSession session, Model model) {
			MemberVO user = (MemberVO)session.getAttribute("user");
			String url;
			String msg;
			if(boardService.insertBoard(board,user)) {
				url = "/board/list";
				msg = "게시글을 등록했습니다.";
			}
			else {
				url = "/board/insert";
				msg = "게시글 등록 실패";
			}
			model.addAttribute("url",url);
			model.addAttribute("msg",msg);
			return "message";
		}
}
