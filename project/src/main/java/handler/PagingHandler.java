package handler;

import domain.PagingVO;

public class PagingHandler {
	
	private int startPage; // 현재 화면에서 보여줄 시작 페이지 번호
	private int endPage; //현재 화면에서 보여줄 마지막 페이지 번호
	private boolean prev, next; //이전, 다음 존재 여부
	
	private int totalCount;
	private PagingVO pgvo;; //pagingVO
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		// 페이지번호 / 한 화면의 게시글 수 * 한 화면의 게시글 수
		// (1/10)*10
		//한 화면에 보여지는  페이지네이션의 마지막번호
		this.endPage = (int) Math.ceil(pgvo.getPageNo() / (pgvo.getQty()*1.0))*pgvo.getQty(); //한 화면의 페이지네이션 마지막 번호
		//한 화면에 보여지는 페이지네이션 첫 번호
		this.startPage = this.endPage -9;//한 화면의 페이지네이션 첫 번호
		int realEndPage = (int)(Math.ceil((totalCount*1.0)/pgvo.getQty())); //페이지네이션의 전체 끝 페이지
		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		this.prev = this.startPage > 1; //존재여부
		this.next = this.endPage < realEndPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public PagingVO getPgvo() {
		return pgvo;
	}

	public void setPgvo(PagingVO pgvo) {
		this.pgvo = pgvo;
	}
	
}
