package repository;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectList();

	BoardVO selectOne(int bno);

	BoardVO selectModify(int bno);

	int update(BoardVO bvo);

	int remove(int bno);

	int readCount(int bno);

	int selectCount();

	List<BoardVO> selectList(PagingVO pgvo);

	List<BoardVO> selectList(String email);

	List<BoardVO> myList(PagingVO pgvo);

	String selectImageFile(int bno);


}

