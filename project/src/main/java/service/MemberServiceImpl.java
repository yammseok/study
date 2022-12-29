package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao= new MemberDAOImpl();
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login check2");
		return mdao.selectOne(mvo);
	}

	@Override
	public int register(MemberVO mvo) {
		// TODO Auto-generated method stub
		log.info("join check2");
		return mdao.insert(mvo);
	}

	@Override
	public int lastLogin(String email) {
		// TODO Auto-generated method stub
		log.info("lastLogin check2");
		return mdao.update(email);
	}

	@Override
	public List<MemberVO> getList() {
		// TODO Auto-generated method stub
		log.info("list check2");
		return mdao.selectList();
	}

	@Override
	public MemberVO getDetail(String email) {
		// TODO Auto-generated method stub
		log.info("detail check2");
		return mdao.detail(email);
	}

	@Override
	public MemberVO getModify(String email) {
		// TODO Auto-generated method stub
		return mdao.modify(email);
	}

	@Override
	public int getEdit(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.getEdit(mvo);
	}

	@Override
	public int getRemove(String email) {
		// TODO Auto-generated method stub
		return mdao.getRemove(email);
	}

}
