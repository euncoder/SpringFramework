package mul.cam.a.dto;

import java.io.Serializable;
//페이지랑 검색만들기
public class BbsParam implements Serializable{
		
	private String choice;   //제목/내용/작성자
	private String search;	 //검색어
	private int pageNumber;  //[1][2][3] 프론트엔드에서 들어옴
	
	private int start;
	private int end; //~부터~까지
	
	public BbsParam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BbsParam(String choice, String search, int pageNumber, int start, int end) {
		super();
		this.choice = choice;
		this.search = search;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "BbsParam [choice=" + choice + ", search=" + search + ", pageNumber=" + pageNumber + ", start=" + start
				+ ", end=" + end + ", getChoice()=" + getChoice() + ", getSearch()=" + getSearch()
				+ ", getPageNumber()=" + getPageNumber() + ", getStart()=" + getStart() + ", getEnd()=" + getEnd()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
