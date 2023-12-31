package com.foly.user.db;

import java.sql.Date;

/**
 *UserDTO : 디비에 저장되는 테이블의 데이터를 저장하는 클래스
 * (Data Transfer Object)
 * 
 *
 * 
 */
public class UserReviewDTO {
	
	// 유저정보	
	private int rvw_num;
	private String own_id;
	private String us_id;
	private String rvw_cont;
	private int rvw_star;
	
	private String res_name;
	

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

	public String getRes_name() {
		return res_name;
	}

	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}

	@Override
	public String toString() {
		return "ReviewDTO [rvw_num=" + rvw_num + ", own_id=" + own_id + ", us_id=" + us_id + ", rvw_cont=" + rvw_cont
				+ ", rvw_star=" + rvw_star + ", res_name=" + res_name + "]";
	}
	
	
	
	
	
	
}