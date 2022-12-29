package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.border.Border;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import domain.PagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	private BoardService bsv;
	private String destPage;
	private RequestDispatcher rdp;
	private int isOk;
	private String savePath; //파일경로를 저장할 변수
	private final String UTF8 = "utf-8"; //인코딩 설정시 필요함
       
    public BoardController() {
    	bsv = new BoardServiceImpl();
    }
    
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=UTF-8");
		String uri = req.getRequestURI(); //jsp에서 오는 요청 주소
		String path = uri.substring(uri.lastIndexOf("/")+1);//switch 문에 구동케이스를 나누기위함
		log.info(path);
		
		
		switch (path) {
		
		case "register":
			log.info("path>>" +path);
			destPage="/board/register.jsp";
			break;
			
		case "insert":
			try {
				//파일을 저장할 물리적인 경로 설정 (업로드 할때 설정)
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				log.info("저장위치"+savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);//저장할 위치를 file 객체로 지정
				fileItemFactory.setSizeThreshold(2*1024*1024); // 저장을 위한 임시 메모리 저장 용량 설정 : byte 단위
				
				BoardVO bvo = new BoardVO();
				// multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(req);
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "title":
						bvo.setTitle(item.getString(UTF8)); //인코딩 형식을 담아서 변환
						log.info(bvo.getTitle());
						break;
					case "writer":
						bvo.setWriter(item.getString(UTF8)); 
						break;
					case "content":
						bvo.setContent(item.getString(UTF8)); 
						break;
					case "image_file":
						//이미지가 있는지 없는지 체크
						if(item.getSize() > 0) { //데이터의 크기를 바이트 단위로 리턴
							String fileName = item.getName()//경로를 포함한 파일이름
									.substring(item.getName().lastIndexOf("/")+1); //파일 이름만 분리
							fileName = System.currentTimeMillis()+"_"+fileName;
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							log.info("파일 경로+이름"+uploadFilePath);
							
							//저장
							try {
								item.write(uploadFilePath); //자바 객체를 디스크에 쓰기
								bvo.setImage_file(fileName);
								
								//썸네일 작업 : 리스트 페이지에서 트래픽 과다사용 방지
								Thumbnails.of(uploadFilePath).size(75, 75).toFile(new File(fileDir+File.separator+"th_"+fileName));
								
							} catch (Exception e) {
								// TODO: handle exception
								log.info(">>> file writer on disk fail");
							}
						}
						break;
					}
				}
				isOk = bsv.register(bvo);
				log.info(">>> insert >" + (isOk>0? "OK":"Fail"));
				
//				String title = req.getParameter("title");
//				String writer = req.getParameter("writer");
//				String content = req.getParameter("content");
//				isOk = bsv.register(new BoardVO(title,writer,content));
				destPage = "pageList";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
//		case "list":
//			try {
//				List<BoardVO> list = bsv.getList();
//				req.setAttribute("list", list);
//				destPage="/board/list.jsp";
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			break;
//		case "pagelist2":
//			try {
//				
//				HttpSession ses = req.getSession();
//				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
//				String email = mvo.getNick_name();
//				List<BoardVO> list = bsv.getMyList(email);
//				req.setAttribute("list",list);
//				
//				destPage = "/board/list.jsp";
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			break;
	
		case "pageList2":
			try {
				HttpSession ses = req.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				String email = mvo.getNick_name();
				PagingVO pgvo = new PagingVO();
				pgvo.setNick_name(email);
				int totCount = bsv.getPageCnt();//전체 카운트 호출
				List<BoardVO> list = bsv.getmyListPage(pgvo); //limit이용한 한페이지 리스트 호출
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				req.setAttribute("list", list);//한페이지 나와야 하는 리스트 보내기
				req.setAttribute("pgh", ph);//페이지 정보 보내기
				destPage = "/board/list.jsp";
				
				
			} catch (Exception e) {
				// TODO: handle exception
				log.info("paging error");
				e.printStackTrace();
			}
			break;
			
//		case "myList":
//			try {
//				HttpSession ses = req.getSession();
//				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
//				String email = mvo.getNick_name();
//				List<BoardVO> list = bsv.getMyList(email);
//				PagingVO pgvo = new PagingVO();
//				int totCount = bsv.getPageCnt();//전체 카운트 호출
//				PagingHandler ph = new PagingHandler(pgvo, totCount);
//				req.setAttribute("list", list);//한페이지 나와야 하는 리스트 보내기
//				req.setAttribute("pgh", ph);//페이지 정보 보내기
//				destPage = "/board/list.jsp";
//				
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//				log.info("paging error");
//				e.printStackTrace();
//			}
//			break;
			
		case "pageList":
			try {
				PagingVO pgvo = new PagingVO();
				int totCount = bsv.getPageCnt();//전체 카운트 호출
				List<BoardVO> list = bsv.getListPage(pgvo); //limit이용한 한페이지 리스트 호출
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				req.setAttribute("list", list);//한페이지 나와야 하는 리스트 보내기
				req.setAttribute("pgh", ph);//페이지 정보 보내기
				destPage = "/board/list.jsp";
				
				
			} catch (Exception e) {
				// TODO: handle exception
				log.info("paging error");
				e.printStackTrace();
			}
			break;
			
			
		case "page":
			try {
				int pageNo = Integer.parseInt(req.getParameter("pageNo"));
				int qty = Integer.parseInt(req.getParameter("qty"));
				PagingVO pgvo = new PagingVO(pageNo,qty);
				int totCount = bsv.getPageCnt();//전체 카운트 호출
				List<BoardVO> list = bsv.getListPage(pgvo); //limit이용한 한페이지 리스트 호출
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				req.setAttribute("list", list);//한페이지 나와야 하는 리스트 보내기
				req.setAttribute("pgh", ph);//페이지 정보 보내기
				destPage = "/board/list.jsp";
				
				
			} catch (Exception e) {
				// TODO: handle exception
				log.info("페이지에러");
				e.printStackTrace();
			}break;
			
		case "detail":
			try {
				int bno = Integer.parseInt(req.getParameter("bno"));
				isOk = bsv.readCount(bno);
				BoardVO bvo = bsv.getDetail(bno);
				req.setAttribute("bvo", bvo);
				destPage="/board/detail.jsp";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}break;
			
		case "modify":
			try {
				int bno = Integer.parseInt(req.getParameter("bno"));
				BoardVO bvo = bsv.getModify(bno);
				req.setAttribute("bvo", bvo);
				destPage="/board/modify.jsp";
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}break;
		case "update":
			try {
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				fileItemFactory.setSizeThreshold(2*1024*1024);
				
				BoardVO bvo = new BoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				log.info("update 준비 >>");
				
				List<FileItem> itemList = fileUpload.parseRequest(req);
				
				String old_file = null;
				for(FileItem item: itemList) {
					switch(item.getFieldName()) {
					case "bno":
						bvo.setBno(Integer.parseInt(item.getString(UTF8)));
						break;
					case "title":
						bvo.setTitle(item.getString(UTF8));
						break;
					case "content":
						bvo.setContent(item.getString(UTF8));
						break;
					case "image_file":
						//old file
						old_file = item.getString(UTF8);
						break;
					case "new_file":
						if(item.getSize()>0) {//새로운 파일이 등록 됨.
							if(old_file !=null) {
								//파일 핸들러 작업 (기존 파일을 삭제)
								FileHandler fileHandler = new FileHandler();
								isOk = fileHandler.deleteFile(old_file, savePath);
							}
							//경로가 포함된 전체경로와 파일이름 생성
							String fileName = item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
							log.info("newFileNae:"+fileName);
							fileName = System.currentTimeMillis()+"_"+fileName;
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							try {
								item.write(uploadFilePath);// 자바 객체를 디스크에 쓰기
								bvo.setImage_file(fileName);
								log.info(">> upload_file"+(bvo.getImage_file()));
								
								Thumbnails.of(uploadFilePath).size(75, 75).
								toFile(new File(fileDir+File.separator+"th_"+fileName));
							} catch (Exception e) {
								// TODO: handle exception
								log.info(">>> File write on disk Fail");
								e.printStackTrace();
							}
							
						}else {
							bvo.setImage_file(old_file);
						}
						break;
					}
				}
				isOk = bsv.getUpdate(bvo);
				log.info(">>update>"+(isOk>0? "ok" : "fail"));
//				int bno = Integer.parseInt(req.getParameter("bno"));
//				String title = req.getParameter("title");
//				String content = req.getParameter("content");
//				isOk = bsv.getUpdate(new BoardVO(bno,title,content));
				
				destPage = "pageList";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}break;
			
		case "remove":
			try {
				int bno = Integer.parseInt(req.getParameter("bno"));
				String imageFileName = bsv.getFileName(bno);// 삭제할 파일 이름 호출
				FileHandler fileHandler = new FileHandler();
				isOk = fileHandler.deleteFile(imageFileName, savePath);
				log.info("fileDelete >>"+(isOk>0? "ok":"fail"));
				isOk = bsv.remove(bno);
				log.info("allDelete >>"+(isOk>0? "ok":"fail"));
				destPage="pageList";
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}break;
		
		
		
		
		
		
		
		
		
		}//스위치 마지막
		rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
	

	
	}//서비스 마지막
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
