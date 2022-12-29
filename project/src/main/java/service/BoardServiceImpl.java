package service;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public int register(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return bdao.selectList();
	}

	@Override
	public BoardVO getDetail(int bno) {
		// TODO Auto-generated method stub
		return bdao.selectOne(bno);
	}

	@Override
	public BoardVO getModify(int bno) {
		// TODO Auto-generated method stub
		return bdao.selectModify(bno);
	}

	@Override
	public int getUpdate(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		// TODO Auto-generated method stub
		return bdao.remove(bno);
	}

	@Override
	public int readCount(int bno) {
		// TODO Auto-generated method stub
		return bdao.readCount(bno);
	}

	@Override
	public int getPageCnt() {
		// TODO Auto-generated method stub
		return bdao.selectCount();
	}

	@Override
	public List<BoardVO> getListPage(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.selectList(pgvo);
	}

	@Override
	public List<BoardVO> getMyList(String email) {
		// TODO Auto-generated method stub
		return bdao.selectList(email);
	}

	@Override
	public List<BoardVO> getmyListPage(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.myList(pgvo);
	}

	@Override
	public String getFileName(int bno) {
		// TODO Auto-generated method stub
		return bdao.selectImageFile(bno);
	}

}
