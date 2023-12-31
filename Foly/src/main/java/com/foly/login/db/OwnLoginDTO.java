package com.foly.login.db;

import java.sql.Date;

/*
 *   OwnLoginDTO : 디비에 저장되는 테이블의 데이터를 저장하는 클래스
 *  (Data Transfer Object)
 *  
 *  
 *  
 *  
 * */
public class OwnLoginDTO {
	
	// 사업자 정보
	private String own_id;
	private String own_pw;
	private String own_name;
	private String own_tel;
	private String own_email;
	
	// 가입일 탈퇴여부 탈퇴일
	private Date own_regdate;
	private int own_out;
	private Date own_od;
	
	// 식당정보
	private String res_name;
	private String res_tel;
	private String res_addr;
	private String res_deposit;
	private String res_type;
	
	// 영업정보
	private String res_mng;
	private String res_stH;
	private String res_stM;
	private String res_etH;
	private String res_etM;

	private String res_img;
	private String res_img_path;
	
	
	
	
	
	// get and set 
	public String getOwn_id() {
		return own_id;
	}
	public void setOwn_id(String own_id) {
		this.own_id = own_id;
	}
	public String getOwn_pw() {
		return own_pw;
	}
	public void setOwn_pw(String own_pw) {
		this.own_pw = own_pw;
	}
	public String getOwn_name() {
		return own_name;
	}
	public void setOwn_name(String own_name) {
		this.own_name = own_name;
	}
	public String getOwn_tel() {
		return own_tel;
	}
	public void setOwn_tel(String own_tel) {
		this.own_tel = own_tel;
	}
	public String getOwn_email() {
		return own_email;
	}
	public void setOwn_email(String own_email) {
		this.own_email = own_email;
	}
	public Date getOwn_regdate() {
		return own_regdate;
	}
	public void setOwn_regdate(Date own_regdate) {
		this.own_regdate = own_regdate;
	}
	public int getOwn_out() {
		return own_out;
	}
	public void setOwn_out(int own_out) {
		this.own_out = own_out;
	}
	public Date getOwn_od() {
		return own_od;
	}
	public void setOwn_od(Date own_od) {
		this.own_od = own_od;
	}
	public String getRes_name() {
		return res_name;
	}
	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}
	public String getRes_tel() {
		return res_tel;
	}
	public void setRes_tel(String res_tel) {
		this.res_tel = res_tel;
	}
	public String getRes_addr() {
		return res_addr;
	}
	public void setRes_addr(String res_addr) {
		this.res_addr = res_addr;
	}
	public String getRes_deposit() {
		return res_deposit;
	}
	public void setRes_deposit(String res_deposit) {
		this.res_deposit = res_deposit;
	}
	public String getRes_type() {
		return res_type;
	}
	public void setRes_type(String res_type) {
		this.res_type = res_type;
	}
	public String getRes_mng() {
		return res_mng;
	}
	public void setRes_mng(String res_mng) {
		this.res_mng = res_mng;
	}
	
	
	public String getRes_stH() {
		return res_stH;
	}
	public void setRes_stH(String res_stH) {
		this.res_stH = res_stH;
	}
	public String getRes_stM() {
		return res_stM;
	}
	public void setRes_stM(String res_stM) {
		this.res_stM = res_stM;
	}
	public String getRes_etH() {
		return res_etH;
	}
	public void setRes_etH(String res_etH) {
		this.res_etH = res_etH;
	}
	public String getRes_etM() {
		return res_etM;
	}
	public void setRes_etM(String res_etM) {
		this.res_etM = res_etM;
	}
	public String getRes_img() {
		return res_img;
	}
	public void setRes_img(String res_img) {
		this.res_img = res_img;
	}
	public String getRes_img_path() {
		return res_img_path;
	}
	public void setRes_img_path(String res_img_path) {
		this.res_img_path = res_img_path;
	}
	@Override
	public String toString() {
		return "OwnLoginDTO [own_id=" + own_id + ", own_pw=" + own_pw + ", own_name=" + own_name + ", own_tel="
				+ own_tel + ", own_email=" + own_email + ", own_regdate=" + own_regdate + ", own_out=" + own_out
				+ ", own_od=" + own_od + ", res_name=" + res_name + ", res_tel=" + res_tel + ", res_addr=" + res_addr
				+ ", res_deposit=" + res_deposit + ", res_type=" + res_type + ", res_mng=" + res_mng + ", res_stH="
				+ res_stH + ", res_stM=" + res_stM + ", res_etH=" + res_etH + ", res_etM=" + res_etM + ", res_img="
				+ res_img + ", res_img_path=" + res_img_path + "]";
	}
	
	
	
	





}
