package kr.kh.spring.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.kh.spring.dao.MemberDAO;
import kr.kh.spring.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService {
	
	@Autowired
	private MemberDAO memberDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public boolean signup(MemberVO member) {
		if(member == null) {
			return false;
		}
		
		//아이디 중복 확인
		MemberVO dbMember = memberDao.selectMember(member.getMe_id());
		//가입하려는 아이디가 이미 가입된 경우
		if(dbMember != null) {
			return false;
		}
		//아이디, 비번 null 체크 + 유효성 검사
		//아이디는 영문으로 시작하고, 6~15자
		String idRegex = "^[a-zA-Z][a-zA-Z0-9]{5,14}$";
		//비번은 영문,숫자,!@#$%로 이루어지고 6~15자 
		String pwRegex = "^[a-zA-Z0-9!@#$%]{6,15}$";
		
		//아이디가 유효성에 맞지 않으면
		if(!Pattern.matches(idRegex, member.getMe_id())) {
			return false;
		}
		//비번이 유효성에 맞지 않으면
		if(!Pattern.matches(pwRegex, member.getMe_pw())) {
			return false;
		}
		
		//비번 암호화 
		String encPw = passwordEncoder.encode(member.getMe_pw());
		member.setMe_pw(encPw);
		//회원가입
		return memberDao.insertMember(member);
	}

	@Override
	public MemberVO login(MemberVO member) {
		if(member == null) {
			return null;
		}
		MemberVO dbMember = memberDao.selectMember(member.getMe_id());
		//가입된 아이디가 아니면
		if(dbMember == null) {
			return null;
		}
		//비번확인
		//matches(암호화안된문자열, 암호화된문자열)
		if(passwordEncoder.matches(member.getMe_pw(), dbMember.getMe_pw())) {
			return dbMember;
		}
		return null;
	}

	@Override
	public void updateMemberSession(MemberVO user) {
		if(user == null) {
			return;
		}
		memberDao.updateMemberSession(user);
	}

	@Override
	public MemberVO getMemberBySession(String session_id) {
		return memberDao.selectMemberBySession(session_id);
	}

	
}
