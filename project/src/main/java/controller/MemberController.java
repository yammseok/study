package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	private RequestDispatcher rdp;
	private String destPage;
	private int isOk;
	HttpSession ses ;
	
	private MemberService msv;
	
	public MemberController() {
		msv = new MemberServiceImpl();
	}

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String uri = req.getRequestURI();// /mem/list
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>>URI>>>"+uri);
		log.info(">>>URI>>>"+path);
		switch(path) {
		case "login" :
			destPage = "/member/login.jsp";
			break;
		case "login_auth": //실제 로그인이 일어나는 케이스
			try {
				MemberVO mvo = msv.login(
						new MemberVO(
								req.getParameter("email"),
								req.getParameter("pwd")));
				log.info("login 객체 input");
				if(mvo !=null) {
					//세션 가져오기. 연결된 세션이 없다면 새로 생성
					HttpSession ses = req.getSession();
					//ses로 mvo를 바인딩
					ses.setAttribute("ses", mvo);
					ses.setMaxInactiveInterval(10*60); //로그인 유지 시간 10분 설정
					String email = req.getParameter("email");
					isOk = msv.lastLogin(email);
				}else {
					req.setAttribute("msg_login", 0);
				}
				destPage="/";
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
			
		case "join":
			destPage ="/member/join.jsp";
			break;
			
		case "register": //회원가입 db저장
			isOk = msv.register(new MemberVO(
					req.getParameter("email"),
					req.getParameter("pwd"),
					req.getParameter("nick_name")));
			log.info(">>> join > "+(isOk>0 ? "ok" : "fail"));
			destPage="login";
			break;
		case "logout":
			try {
				HttpSession ses =req.getSession();
				ses.invalidate();
				String email = req.getParameter("email");
				isOk = msv.lastLogin(email);
				//로그인정보 email 을 주고 로그인 시간 업데이트
				log.info(">>> lastLogin > "+(isOk>0 ? "ok" : "fail"));
				destPage="/";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
			
		case "list" :
			try {
				List<MemberVO> list = msv.getList();
				req.setAttribute("list", list);
				destPage = "/member/list.jsp";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		
		case "detail" :
			try {
				String email = req.getParameter("email");
				MemberVO mvo = msv.getDetail(email);
				req.setAttribute("mvo", mvo);
				destPage="/member/detail.jsp";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "modify" :
			try {
				String email = req.getParameter("email");
				MemberVO mvo = msv.getModify(email);
				req.setAttribute("mvo", mvo);
				destPage="/member/modify.jsp";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
			
		case "edit" :
			try {
				String email = req.getParameter("email");
				String pwd = req.getParameter("pwd");
				String nick_name = req.getParameter("nick_name");
				isOk = msv.getEdit(new MemberVO(email,pwd,nick_name));
				destPage = "list";
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
			
		case "remove" :
			try {
				String email = req.getParameter("email");
				isOk = msv.getRemove(email);
				destPage = "list";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}break;
			
		
		}//스위치 마지막
		//목적지주소
		rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

}
