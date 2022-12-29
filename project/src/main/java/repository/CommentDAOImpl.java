package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {
   
   private SqlSession sql;
   private final String NS = "CommentMapper.";
   private int isOk;
   
   public CommentDAOImpl() {
      new DatabaseBuilder();
      sql = DatabaseBuilder.getFactory().openSession();
   }

@Override
public int insert(CommentVO cvo) {
   System.out.println(">>> comment > post > check3");
   isOk = sql.insert(NS+"add", cvo);
   if(isOk > 0) {
      sql.commit();
   } return isOk;
}

@Override
public List<CommentVO> selectList(int bno) {
   return sql.selectList(NS+"list", bno);
}

@Override
public int remove(int cno) {
   int isOk = sql.delete(NS+"remove",cno);
   if(isOk > 0) {
      sql.commit();
   }
   return isOk;
}

@Override
public int modify(CommentVO cvo) {
   int isOk = sql.update(NS+"modify", cvo);
   if(isOk > 0) {
      sql.commit();
   }
   return isOk;
}
   
   
}