package com.foly.own.db;

/*
 *   ReviewDTO : 디비에 저장되는 테이블의 데이터를 저장하는 클래스
 *  (Data Transfer Object)
 *  
 *  
 *  
 *  
 * */
public class ReviewDTO {
	
	private int rvw_num;
	private String own_id;
	private String us_id;
	private String rvw_cont;
	private int rvw_star;
	private String rvw_re;
	
	
	public int getRvw_num() {
		return rvw_num;
	}
	public void setRvw_num(int rvw_num) {
		this.rvw_num = rvw_num;
	}
	public String getOwn_id() {
		return own_id;
	}
	public void setOwn_id(String own_id) {
		this.own_id = own_id;
	}
	public String getUs_id() {
		return us_id;
	}
	public void setUs_id(String us_id) {
		this.us_id = us_id;
	}
	public String getRvw_cont() {
		return rvw_cont;
	}
	public void setRvw_cont(String rvw_cont) {
		this.rvw_cont = rvw_cont;
	}
	public int getRvw_star() {
		return rvw_star;
	}
	public void setRvw_star(int rvw_star) {
		this.rvw_star = rvw_star;
	}
	public String getRvw_re() {
		return rvw_re;
	}
	public void setRvw_re(String rvw_re) {
		this.rvw_re = rvw_re;
	}
	
	
	@Override
	public String toString() {
		return "ReviewDTO [rvw_num=" + rvw_num + ", own_id=" + own_id + ", us_id=" + us_id + ", rvw_cont=" + rvw_cont
				+ ", rvw_star=" + rvw_star + ", rvw_re=" + rvw_re + "]";
	}

	


}
