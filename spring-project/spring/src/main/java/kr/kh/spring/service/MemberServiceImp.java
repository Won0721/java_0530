package kr.kh.spring.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.kh.spring.dao.MemberDAO;
import kr.kh.spring.vo.MemberVO;

// service 어노테이션 등록
@Service
public class MemberServiceImp implements MemberService{
	@Autowired // autowired : 객체 자동생성
	private MemberDAO memberDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public boolean signup(MemberVO member) {
		if(member ==null) {
			return false;
		}
		// 아이디 중복 확인
		MemberVO dbMember = memberDao.selectMember(member.getMe_id());
		// 가입하려는 아이디가 이미 가입된 경우
		if(dbMember !=null) {
			return false;
		}
		// 아이디,비번 정규표현식(유효성검사)	
		String idRegex = "^[a-zA-Z][a-zA-Z0-9]{5,14}+$"; // 영문숫자조합 6~15자리
		String pwRegex = "^[a-zA-Z0-9!@#$%]{6,15}$";   // (영문, 특수문자, 숫자 포함 6~15자
		if(!Pattern.matches(idRegex, member.getMe_id())) {
			return false;
		}
		if(!Pattern.matches(pwRegex, member.getMe_pw())) {
			return false;
		}
		
		// 비번 암호화
		String encPw = passwordEncoder.encode(member.getMe_pw());
		member.setMe_pw(encPw);
		// 회원가입
		memberDao.insertMember(member);
		return true;
		
	}

	@Override
	public MemberVO login(MemberVO member) {
		if(member == null) {
			return null;			
		}
		MemberVO dbMember = memberDao.selectMember(member.getMe_id());
		// 가입된 아이디가 아니면
		if(dbMember == null) {
			return null;
		}
		// 비밀번호 확인 
		// matches(입력한 비밀번호, db에 저장된 비밀번호 문자열)
		if(passwordEncoder.matches(member.getMe_pw(), dbMember.getMe_pw())) {
			return dbMember;
		}
		return null;
	}



}
