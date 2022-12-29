package repository;

import java.util.List;

import domain.CommentVO;

public interface CommentDAO {

   List<CommentVO> selectList = null;

   int insert(CommentVO cvo);

   List<CommentVO> selectList(int bno);

   int remove(int cno);

   int modify(CommentVO cvo);

}