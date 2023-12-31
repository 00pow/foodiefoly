package com.foly.res.db;

public class CartDTO {
	private int cart_num; //카트번호
	private int cart_price; //카트가격
	private String cart_menu; //카트메뉴
	private int me_num; //메뉴번호
	private int cart_price_total; //총 주문금액
	private int cart_amount; //카트수량
	private int own_num; //사업자번호
	private String res_name; //식당이름
	private String us_id; // 사용자아이디
	private String own_id; //사업자아이디
	private String me_img; //이미지
	private String me_name;
	private int me_price;
	private int me_amount;
	
	public int getCart_num() {
		return cart_num;
	}
	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}
	public int getCart_price() {
		return cart_price;
	}
	public void setCart_price(int cart_price) {
		this.cart_price = cart_price;
	}
	public String getCart_menu() {
		return cart_menu;
	}
	public void setCart_menu(String cart_menu) {
		this.cart_menu = cart_menu;
	}
	public int getMe_num() {
		return me_num;
	}
	public void setMe_num(int me_num) {
		this.me_num = me_num;
	}
	public int getCart_price_total() {
		return cart_price_total;
	}
	public void setCart_price_total(int cart_price_total) {
		this.cart_price_total = cart_price_total;
	}
	public int getCart_amount() {
		return cart_amount;
	}
	public void setCart_amount(int cart_amount) {
		this.cart_amount = cart_amount;
	}
	public int getOwn_num() {
		return own_num;
	}
	public void setOwn_num(int own_num) {
		this.own_num = own_num;
	}
	public String getRes_name() {
		return res_name;
	}
	public void setRes_name(String res_name) {
		this.res_name = res_name;
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
	
	public int getMe_amount() {
		return me_amount;
	}
	public void setMe_amount(int me_amount) {
		this.me_amount = me_amount;
	}
	@Override
	public String toString() {
		return "CartDTO [cart_num=" + cart_num + ", cart_price=" + cart_price + ", cart_menu=" + cart_menu + ", me_num="
				+ me_num + ", cart_price_total=" + cart_price_total + ", cart_amount=" + cart_amount + ", own_num="
				+ own_num + ", res_name=" + res_name + ", us_id=" + us_id + ", own_id=" + own_id + ", me_img=" + me_img
				+ ", me_name=" + me_name + ", me_price=" + me_price + ", me_amount=" + me_amount + "]";
	}
	
	
}