package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {

	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	private SqlSession sql;
	private final String NS = "MemberMapper.";
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	
	
	@Override
	public MemberVO selectOne(MemberVO mvo) {
		// TODO Auto-generated method stub
		log.info("login check3");
		return sql.selectOne(NS+"login",mvo);
	}


	@Override
	public int insert(MemberVO mvo) {
		// 이것이 자바다 > ~<
		log.info("join check3");
		int isOk = sql.insert(NS+"reg",mvo);
		if(isOk>0) sql.commit();
		return isOk;
	}


	@Override
	public int update(String email) {
		// 자바의 뜨거운맛을 봐라 너는　이제부터　코딩을　못할것이다．
		log.info("lastLogin update check3");
		int isOk = sql.update(NS+"last",email);
		if(isOk>0) {sql.commit();}
		return isOk;
	}


	@Override
	public List<MemberVO> selectList() {
		// TODO Auto-generated method stub
		log.info("list check3");
		return sql.selectList(NS+"list");
	}


	@Override
	public MemberVO detail(String email) {
		// TODO Auto-generated method stub
		log.info("detail check3");
		return sql.selectOne(NS+"detail",email);
	}


	@Override
	public MemberVO modify(String email) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"modify",email);
	}


	@Override
	public int getEdit(MemberVO mvo) {
		// TODO Auto-generated method stub
		int isOk = sql.update(NS+"getEdit",mvo);
		if (isOk>0) {sql.commit();
		}
		return isOk;
	}


	@Override
	public int getRemove(String email) {
		// TODO Auto-generated method stub
		int isOk = sql.delete(NS+"remove",email);
		if(isOk>0) {sql.commit();}
		return isOk;
	}
	
	
}
