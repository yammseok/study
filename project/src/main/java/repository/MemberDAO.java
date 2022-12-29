package repository;

import java.util.List;

import domain.MemberVO;

public interface MemberDAO {

	MemberVO selectOne(MemberVO mvo);

	int insert(MemberVO mvo);

	int update(String email);

	List<MemberVO> selectList();

	MemberVO detail(String email);

	MemberVO modify(String email);

	int getEdit(MemberVO mvo);

	int getRemove(String email);

}
