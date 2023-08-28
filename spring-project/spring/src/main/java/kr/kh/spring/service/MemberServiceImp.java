package kr.kh.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kh.spring.dao.MemberDAO;

// service 어노테이션 등록
@Service
public class MemberServiceImp implements MemberService{
	@Autowired // autowired : 객체 자동생성
	private MemberDAO memberDao;

	@Override
	public int count() {
		
		return memberDao.selectMember();
	}
}
