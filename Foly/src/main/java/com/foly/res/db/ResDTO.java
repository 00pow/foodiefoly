package com.foly.res.db;


import java.sql.Date;

/*
 *   UserBookingDTO : 디비에 저장되는 테이블의 데이터를 저장하는 클래스
 *  (Data Transfer Object)
 *  
 *  
 *  
 *  
 * */
public class ResDTO {
	
	// user 테이블
	private String us_id;
	
	// owner 테이블
	private String own_id;
	private String own_name;
	private String res_name;
	private String res_exp;
	private String res_tel;
	private String res_addr;
	private String res_deposit;
	private String res_img;
	
	
	private String res_stH;
	private String res_stM;
	private String res_etH;
	private String res_etM;
	
	
	// booking 테이블
	
	// 예약
    private String bo_value;
    private int bo_num;
    private Date bo_date;
    private Date bo_dateH;
    private String bo_per;
    
    // 포장
    private int pk_num;
    private String bo_state;
    private String bo_menu;
    private int bo_count;
    private int bo_price;
    private String pay_num;
    
    
    // restaurant
    private int me_num;
    private String me_img;
    private String me_name;
    private int me_price;
    private String me_exp;
    private String me_img_path;
    
    
    // cart
    private int cart_num;
    private int me_amount;
    
    private String cart_menu;
    private int cart_amount;
    private int cart_price;
    private int cart_total;
    
    
    // 리뷰
	private int rvw_num;


	public String getUs_id() {
		return us_id;
	}


	public void setUs_id(String us_id) {
		this.us_id = us_id;
	}


	public String getOwn_id() {
		return own_id;
	}


	public void setOwn_id(String own_id) {
		this.own_id = own_id;
	}


	public String getOwn_name() {
		return own_name;
	}


	public void setOwn_name(String own_name) {
		this.own_name = own_name;
	}


	public String getRes_name() {
		return res_name;
	}


	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}


	public String getRes_exp() {
		return res_exp;
	}


	public void setRes_exp(String res_exp) {
		this.res_exp = res_exp;
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


	public String getRes_img() {
		return res_img;
	}


	public void setRes_img(String res_img) {
		this.res_img = res_img;
	}


	public String getBo_value() {
		return bo_value;
	}


	public void setBo_value(String bo_value) {
		this.bo_value = bo_value;
	}


	public int getBo_num() {
		return bo_num;
	}


	public void setBo_num(int bo_num) {
		this.bo_num = bo_num;
	}


	public Date getBo_date() {
		return bo_date;
	}


	public void setBo_date(Date bo_date) {
		this.bo_date = bo_date;
	}


	public Date getBo_dateH() {
		return bo_dateH;
	}


	public void setBo_dateH(Date bo_dateH) {
		this.bo_dateH = bo_dateH;
	}


	public String getBo_per() {
		return bo_per;
	}


	public void setBo_per(String bo_per) {
		this.bo_per = bo_per;
	}


	public int getPk_num() {
		return pk_num;
	}


	public void setPk_num(int pk_num) {
		this.pk_num = pk_num;
	}


	public String getBo_state() {
		return bo_state;
	}


	public void setBo_state(String bo_state) {
		this.bo_state = bo_state;
	}


	public String getBo_menu() {
		return bo_menu;
	}


	public void setBo_menu(String bo_menu) {
		this.bo_menu = bo_menu;
	}


	public int getBo_count() {
		return bo_count;
	}


	public void setBo_count(int bo_count) {
		this.bo_count = bo_count;
	}


	public int getBo_price() {
		return bo_price;
	}


	public void setBo_price(int bo_price) {
		this.bo_price = bo_price;
	}


	public String getPay_num() {
		return pay_num;
	}


	public void setPay_num(String pay_num) {
		this.pay_num = pay_num;
	}


	public int getMe_num() {
		return me_num;
	}


	public void setMe_num(int me_num) {
		this.me_num = me_num;
	}


	public String getMe_img() {
		return me_img;
	}


	public void setMe_img(String me_img) {
		this.me_img = me_img;
	}


	public String getMe_name() {
		return me_name;
	}


	public void setMe_name(String me_name) {
		this.me_name = me_name;
	}


	public int getMe_price() {
		return me_price;
	}


	public void setMe_price(int me_price) {
		this.me_price = me_price;
	}


	public String getMe_exp() {
		return me_exp;
	}


	public void setMe_exp(String me_exp) {
		this.me_exp = me_exp;
	}


	public int getCart_num() {
		return cart_num;
	}


	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}


	public int getMe_amount() {
		return me_amount;
	}


	public void setMe_amount(int me_amount) {
		this.me_amount = me_amount;
	}


	public String getCart_menu() {
		return cart_menu;
	}


	public void setCart_menu(String cart_menu) {
		this.cart_menu = cart_menu;
	}


	public int getCart_amount() {
		return cart_amount;
	}


	public void setCart_amount(int cart_amount) {
		this.cart_amount = cart_amount;
	}


	public int getCart_price() {
		return cart_price;
	}


	public void setCart_price(int cart_price) {
		this.cart_price = cart_price;
	}


	public int getCart_total() {
		return cart_total;
	}


	public void setCart_total(int cart_total) {
		this.cart_total = cart_total;
	}


	public int getRvw_num() {
		return rvw_num;
	}


	public void setRvw_num(int rvw_num) {
		this.rvw_num = rvw_num;
	}


	public String getMe_img_path() {
		return me_img_path;
	}


	public void setMe_img_path(String me_img_path) {
		this.me_img_path = me_img_path;
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


	@Override
	public String toString() {
		return "ResDTO [us_id=" + us_id + ", own_id=" + own_id + ", own_name=" + own_name + ", res_name=" + res_name
				+ ", res_exp=" + res_exp + ", res_tel=" + res_tel + ", res_addr=" + res_addr + ", res_deposit="
				+ res_deposit + ", res_img=" + res_img + ", res_stH=" + res_stH + ", res_stM=" + res_stM + ", res_etH="
				+ res_etH + ", res_etM=" + res_etM + ", bo_value=" + bo_value + ", bo_num=" + bo_num + ", bo_date="
				+ bo_date + ", bo_dateH=" + bo_dateH + ", bo_per=" + bo_per + ", pk_num=" + pk_num + ", bo_state="
				+ bo_state + ", bo_menu=" + bo_menu + ", bo_count=" + bo_count + ", bo_price=" + bo_price + ", pay_num="
				+ pay_num + ", me_num=" + me_num + ", me_img=" + me_img + ", me_name=" + me_name + ", me_price="
				+ me_price + ", me_exp=" + me_exp + ", me_img_path=" + me_img_path + ", cart_num=" + cart_num
				+ ", me_amount=" + me_amount + ", cart_menu=" + cart_menu + ", cart_amount=" + cart_amount
				+ ", cart_price=" + cart_price + ", cart_total=" + cart_total + ", rvw_num=" + rvw_num + "]";
	}





    
    
    
    
	
    
  



}
