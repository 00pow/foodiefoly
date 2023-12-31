package com.foly.pay.db;

import java.sql.Date;

public class PayDTO {
	
	private String pay_num;
	private int pk_num;
	private int bo_num;
	private String pay_card;
	private String card_num;
	private String pay_state;
	private Date pay_date;
	private String pay_memo;
	private String us_id;
	private String own_id;
	private int pay_total;
	private String res_name;
	private String us_name;
	private String us_tel;
	private String pay_id;
	
	public String getPay_num() {
		return pay_num;
	}
	public void setPay_num(String pay_num) {
		this.pay_num = pay_num;
	}
	public int getPk_num() {
		return pk_num;
	}
	public void setPk_num(int pk_num) {
		this.pk_num = pk_num;
	}
	public int getBo_num() {
		return bo_num;
	}
	public void setBo_num(int bo_num) {
		this.bo_num = bo_num;
	}
	public String getPay_card() {
		return pay_card;
	}
	public void setPay_card(String pay_card) {
		this.pay_card = pay_card;
	}
	public String getCard_num() {
		return card_num;
	}
	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}
	public String getPay_state() {
		return pay_state;
	}
	public void setPay_state(String pay_state) {
		this.pay_state = pay_state;
	}
	public Date getPay_date() {
		return pay_date;
	}
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	public String getPay_memo() {
		return pay_memo;
	}
	public void setPay_memo(String pay_memo) {
		this.pay_memo = pay_memo;
	}
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
	public int getPay_total() {
		return pay_total;
	}
	public void setPay_total(int pay_total) {
		this.pay_total = pay_total;
	}
	public String getRes_name() {
		return res_name;
	}
	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}
	public String getUs_name() {
		return us_name;
	}
	public void setUs_name(String us_name) {
		this.us_name = us_name;
	}
	public String getUs_tel() {
		return us_tel;
	}
	public void setUs_tel(String us_tel) {
		this.us_tel = us_tel;
	}
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	@Override
	public String toString() {
		return "PayDTO [pay_num=" + pay_num + ", pk_num=" + pk_num + ", bo_num=" + bo_num + ", pay_card=" + pay_card
				+ ", card_num=" + card_num + ", pay_state=" + pay_state + ", pay_date=" + pay_date + ", pay_memo="
				+ pay_memo + ", us_id=" + us_id + ", own_id=" + own_id + ", pay_total=" + pay_total + ", res_name="
				+ res_name + ", us_name=" + us_name + ", us_tel=" + us_tel + ", pay_id=" + pay_id + "]";
	}
	
}
