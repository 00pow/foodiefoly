package com.foly.user.db;

import java.sql.Date;


public class UserqDTO {
	
	   private int qna_num; // 문의 번호
	   private String us_nick;
	   private String qna_sub; // 문의 제목
	   private String qna_cont; // 문의 내용
	   private String qna_re; // 문의 답글
	   private Date date; // 작성 날짜
	   private String us_id;
	  
	   
	   
	   
	   
	public String getUs_id() {
		return us_id;
	}
	public void setUs_id(String us_id) {
		this.us_id = us_id;
	}
	public int getQna_num() {
		return qna_num;
	}
	public String getUs_nick() {
		return us_nick; 
	}
	public void setUs_nick(String us_nick) {
		this.us_nick = us_nick;
	}
	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}
	public String getQna_sub() {
		return qna_sub;
	}
	public void setQna_sub(String qna_sub) {
		this.qna_sub = qna_sub;
	}
	public String getQna_cont() {
		return qna_cont;
	}
	public void setQna_cont(String qna_cont) {
		this.qna_cont = qna_cont;
	}
	public String getQna_re() {
		return qna_re;
	}
	public void setQna_re(String qna_re) {
		this.qna_re = qna_re;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "UserqDTO [qna_num=" + qna_num + ", us_nick=" + us_nick + ", qna_sub=" + qna_sub + ", qna_cont="
				+ qna_cont + ", qna_re=" + qna_re + ", date=" + date + ", us_id=" + us_id + "]";
	}

	   
}
