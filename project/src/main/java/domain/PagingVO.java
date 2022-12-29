package domain;

public class PagingVO {
	
	private int pageNo; //현재 화면에 출력되는 페이지 네이션 번호
	private int qty; //한 페이지당 보여줄 게시글 수
	private String nick_name;
	
	public PagingVO() {//페이지네이션을 클릭하기 전 기본 리스트 출력값
		this(1,10);
	}
	
	
	public PagingVO(String nick_name) {
		super();
		this.nick_name = nick_name;
	}


	public PagingVO(int pageNo, int qty) {
		super();
		this.pageNo = pageNo;
		this.qty = qty;
	}


	public String getNick_name() {
		return nick_name;
	}


	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}


	public int getPageStart() {
		return (this.pageNo-1)*qty; //시작 페이지 번호 구하기
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	

	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}
	


}
