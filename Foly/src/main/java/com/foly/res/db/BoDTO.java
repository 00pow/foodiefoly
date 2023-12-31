package com.foly.res.db;

import java.sql.Date;


public class BoDTO {
	
	private String us_id; //사용자아이디
    private String own_id; //사업자아이디-가게이름갖고오기위함
    private String bo_value; //분류
    private int bo_num; //예약번호 자동으로+되게
    private String bo_date; //예약일자
    private String bo_dateH; //예약시간
    private String bo_per; //예약인원
    private int pk_num; //포장번호
    private String bo_state; //주문상태->결제되야확인가능?
    private String bo_menu; //메뉴
    private int bo_count;
    private int bo_price;
    private String pay_num; //결제번호
    private int own_num;
    private int res_deposit;
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
	public String getBo_date() {
		return bo_date;
	}
	public void setBo_date(String bo_date) {
		this.bo_date = bo_date;
	}
	public String getBo_dateH() {
		return bo_dateH;
	}
	public void setBo_dateH(String bo_dateH) {
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
	
	public int getOwn_num() {
		return own_num;
	}
	public void setOwn_num(int own_num) {
		this.own_num = own_num;
	}
	public int getRes_deposit() {
		return res_deposit;
	}
	public void setRes_deposit(int res_deposit) {
		this.res_deposit = res_deposit;
	}
	public String getPay_num() {
		return pay_num;
	}
	public void setPay_num(String pay_num) {
		this.pay_num = pay_num;
	}
	@Override
	public String toString() {
		return "BoDTO [us_id=" + us_id + ", own_id=" + own_id + ", bo_value=" + bo_value + ", bo_num=" + bo_num
				+ ", bo_date=" + bo_date + ", bo_dateH=" + bo_dateH + ", bo_per=" + bo_per + ", pk_num=" + pk_num
				+ ", bo_state=" + bo_state + ", bo_menu=" + bo_menu + ", bo_count=" + bo_count + ", bo_price="
				+ bo_price + ", pay_num=" + pay_num + ", own_num=" + own_num + ", res_deposit=" + res_deposit + "]";
	}
	
}