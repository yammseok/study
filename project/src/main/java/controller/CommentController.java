package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import handler.FileHandler;
import service.CommentService;
import service.CommentServiceImpl;

   @WebServlet("/cmt/*" )
   public class CommentController extends HttpServlet {
      private static final long serialVersionUID = 1L;
       private static Logger log = LoggerFactory.getLogger(CommentController.class);
       private CommentService csv;
       private int isOk;
       
       public CommentController() {
          csv = new CommentServiceImpl();
       }


   protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      res.setCharacterEncoding("UTF-8");
      
      String uri = req.getRequestURI(); // /cmt/list/10
      String pathUri = uri.substring("/cmt/".length());
      String path=pathUri;
      String pathVar=null;
      if(pathUri.contains("/")) {
         path = pathUri.substring(0, pathUri.lastIndexOf("/"));
         pathVar = pathUri.substring(pathUri.lastIndexOf("/")+1);
         
      }
      log.info(">>> uri >>>>"+uri);
      log.info(">>> pathVar >>>"+pathUri);
      log.info(">>> path >>>"+path);
      log.info(">>> pathVar >>>"+pathVar);
      
      switch (path) {
      case "post":
         try {
            //js에서 보낸 데이터를 StringBuffer로 읽어들이는 작업.
         StringBuffer sb = new StringBuffer();
         String line = null;
         BufferedReader br = req.getReader(); // 댓글 객체
         while((line = br.readLine())!=null) { //값이 널이 아니라면 (값이 남아있다면)
            sb.append(line);
         }
         log.info(line);
         //객체로 생성
         JSONParser parser = new JSONParser();
         JSONObject jsonObj = (JSONObject)parser.parse(sb.toString());
         int bno = Integer.parseInt(jsonObj.get("bno").toString());
         String writer = jsonObj.get("writer").toString();                                                 
         String content = jsonObj.get("content").toString();                                                 
         isOk =csv.post(new CommentVO(bno,writer,content));
         PrintWriter out = res.getWriter();
         out.print(isOk);
      } catch (Exception e) {
         log.info(">>> Comment > post > error");
         e.printStackTrace(); 
      }break;
    
      case "list":
         try {
         List<CommentVO> list = csv.getList(Integer.parseInt(pathVar));
         log.info(">>> Comment > list > DB ok");
         //json 형태로 변환
         JSONObject[] jsonObjArr = new JSONObject[list.size()];
         JSONArray jsonObjList = new JSONArray();
         for(int i=0; i<list.size(); i++) {
                  jsonObjArr[i] = new JSONObject(); //key : value
                  jsonObjArr[i].put("cno", list.get(i).getCno());
                  jsonObjArr[i].put("bno", list.get(i).getBno());
                  jsonObjArr[i].put("writer", list.get(i).getWriter());
                  jsonObjArr[i].put("content", list.get(i).getContent());
                  jsonObjArr[i].put("reg_at", list.get(i).getReg_at());
                  
                  jsonObjList.add(jsonObjArr[i]);
         }
         String jsonData = jsonObjList.toString();
         
         PrintWriter out = res.getWriter();
         out.print(jsonData);
         
      } catch (Exception e) {
         log.info(">>> Comment > list > error");
         e.printStackTrace(); 
      }break;
      
      case "modify":
         try {
               StringBuffer sb = new StringBuffer();
            String line = null;
            BufferedReader br = req.getReader(); // 댓글 객체
            while((line = br.readLine())!=null) { //값이 널이 아니라면 (값이 남아있다면)
               sb.append(line);
            }
            log.info(">>> sb : "+sb.toString());
            //객체로 생성
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject)parser.parse(sb.toString());
            int cno = Integer.parseInt(jsonObj.get("cno").toString());                                               
            String content = jsonObj.get("content").toString();                                                 
            isOk =csv.detail(new CommentVO(cno,content));
            PrintWriter out = res.getWriter();
            out.print(isOk);
         } catch (Exception e) {
            e.printStackTrace();
         }
      
      case "remove":
         try {
            isOk = csv.remove(Integer.parseInt(pathVar));
            log.info("삭제"+(isOk>0?"성공":"실패"));
            PrintWriter out = res.getWriter();
            out.print(isOk);
      } catch (Exception e) {
         log.info("요청이 올바르지 않습니다.");
         e.printStackTrace(); 
      }break;
   }
}
   protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      service(req, res);
   }

   
   protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

      doGet(req, res);
   }

}