package service;

import java.util.List;

import domain.CommentVO;
import repository.CommentDAO;
import repository.CommentDAOImpl;

public class CommentServiceImpl implements CommentService {

      private CommentDAO cdao;
      
      public CommentServiceImpl() {
         cdao = new CommentDAOImpl();
      }

      @Override
      public int post(CommentVO cvo) {
         System.out.println("2번에러");
         return cdao.insert(cvo);
      }

      @Override
      public List<CommentVO> getList(int bno) {
         return cdao.selectList(bno);
      }

      @Override
      public int remove(int cno) {
         return cdao.remove(cno);
      }

      @Override
      public int detail(CommentVO cvo) {
         return cdao.modify(cvo);
      }
}