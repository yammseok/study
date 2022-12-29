package service;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(int bno);

	BoardVO getModify(int bno);

	int getUpdate(BoardVO bvo);

	int remove(int bno);
	

	int readCount(int bno);

	int getPageCnt();

	List<BoardVO> getListPage(PagingVO pgvo);

	List<BoardVO> getMyList(String email);

	List<BoardVO> getmyListPage(PagingVO pgvo);

	String getFileName(int bno);


}
