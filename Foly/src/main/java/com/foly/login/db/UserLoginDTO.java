package com.foly.login.db;

import java.sql.Date;

/*
 *   LoginDTO : 디비에 저장되는 테이블의 데이터를 저장하는 클래스
 *  (Data Transfer Object)
 *  
 *  
 *  MemberBean(자바빈) -> UserDTO
 *  
 *  
 * */
public class UserLoginDTO {
	
	// 유저정보
	private String us_id;
	private String us_pw;
	private String us_name;
	private String us_nick;
	private String us_tel;
	private String us_email;
	private String us_addr;
	
	// 생일
	private String us_birY;
	private String us_birM;
	private String us_birD;
	
	// 이용정보
	private int us_order;
	private int us_point;
	private int rvw_num;
	
	// 가입일 탈퇴여부 탍퇴일 
	private Date us_regdate;
	private int us_out;
	private Date us_od;
	
	
	// get and set 
	public String getUs_id() {
		return us_id;
	}
	public void setUs_id(String us_id) {
		this.us_id = us_id;
	}
	public String getUs_pw() {
		return us_pw;
	}
	public void setUs_pw(String us_pw) {
		this.us_pw = us_pw;
	}
	public String getUs_name() {
		return us_name;
	}
	public void setUs_name(String us_name) {
		this.us_name = us_name;
	}
	public String getUs_nick() {
		return us_nick;
	}
	public void setUs_nick(String us_nick) {
		this.us_nick = us_nick;
	}
	public String getUs_tel() {
		return us_tel;
	}
	public void setUs_tel(String us_tel) {
		this.us_tel = us_tel;
	}
	public String getUs_email() {
		return us_email;
	}
	public void setUs_email(String us_email) {
		this.us_email = us_email;
	}
	public String getUs_addr() {
		return us_addr;
	}
	public void setUs_addr(String us_addr) {
		this.us_addr = us_addr;
	}
	public String getUs_birY() {
		return us_birY;
	}
	public void setUs_birY(String us_birY) {
		this.us_birY = us_birY;
	}
	public String getUs_birM() {
		return us_birM;
	}
	public void setUs_birM(String us_birM) {
		this.us_birM = us_birM;
	}
	public String getUs_birD() {
		return us_birD;
	}
	public void setUs_birD(String us_birD) {
		this.us_birD = us_birD;
	}
	public int getUs_order() {
		return us_order;
	}
	public void setUs_order(int us_order) {
		this.us_order = us_order;
	}
	public int getUs_point() {
		return us_point;
	}
	public void setUs_point(int us_point) {
		this.us_point = us_point;
	}
	public int getRvw_num() {
		return rvw_num;
	}
	public void setRvw_num(int rvw_num) {
		this.rvw_num = rvw_num;
	}
	public Date getUs_regdate() {
		return us_regdate;
	}
	public void setUs_regdate(Date us_regdate) {
		this.us_regdate = us_regdate;
	}
	public int getUs_out() {
		return us_out;
	}
	public void setUs_out(int us_out) {
		this.us_out = us_out;
	}
	public Date getUs_od() {
		return us_od;
	}
	public void setUs_od(Date us_od) {
		this.us_od = us_od;
	}
	
	
	@Override
	public String toString() {
		return "UserLoginDTO [us_id=" + us_id + ", us_pw=" + us_pw + ", us_name=" + us_name + ", us_nick=" + us_nick
				+ ", us_tel=" + us_tel + ", us_email=" + us_email + ", us_addr=" + us_addr + ", us_birY=" + us_birY
				+ ", us_birM=" + us_birM + ", us_birD=" + us_birD + ", us_order=" + us_order + ", us_point=" + us_point
				+ ", rvw_num=" + rvw_num + ", us_regdate=" + us_regdate + ", us_out=" + us_out + ", us_od=" + us_od
				+ "]";
	}
	
	

	   
	



}
