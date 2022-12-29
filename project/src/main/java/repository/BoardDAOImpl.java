package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	private SqlSession sql;
	private final String NS = "BoardMapper.";
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		int isOk = sql.insert(NS+"register",bvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public List<BoardVO> selectList() {
		// TODO Auto-generated method stub
		return sql.selectList(NS+"list");
	}

	@Override
	public BoardVO selectOne(int bno) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"detail",bno);
	}
	


	@Override
	public BoardVO selectModify(int bno) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"modify",bno);
	}

	@Override
	public int update(BoardVO bvo) {
		// TODO Auto-generated method stub
		int isOk = sql.update(NS+"update",bvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int remove(int bno) {
		// TODO Auto-generated method stub
		int isOk =sql.delete(NS+"remove",bno);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int readCount(int bno) {
		// TODO Auto-generated method stub
		int isOk =sql.update(NS+"readCount",bno);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"cnt");
	}

	@Override
	public List<BoardVO> selectList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return sql.selectList(NS+"pagingList",pgvo);
	}

	@Override
	public List<BoardVO> selectList(String email) {
		// TODO Auto-generated method stub
		return sql.selectList(NS+"myList",email);
	}

	@Override
	public List<BoardVO> myList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return sql.selectList(NS+"myList2",pgvo);
	}

	@Override
	public String selectImageFile(int bno) {
		return sql.selectOne(NS+"imgFile",bno);
		
	}

}
