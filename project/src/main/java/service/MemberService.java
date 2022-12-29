package service;

import java.util.List;

import domain.MemberVO;

public interface MemberService {

	MemberVO login(MemberVO mvo);

	int register(MemberVO mvo);

	int lastLogin(String email);

	List<MemberVO> getList();

	MemberVO getDetail(String email);

	MemberVO getModify(String email);

	int getEdit(MemberVO mvo);

	int getRemove(String email);

}
