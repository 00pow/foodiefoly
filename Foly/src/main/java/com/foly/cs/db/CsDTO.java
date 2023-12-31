package com.foly.cs.db;

import java.sql.Date;

public class CsDTO {
	
	private int noti_num; // 공지사항 번호
	private String noti_sub; // 공지사항 제목
	private String noti_cont; // 공지사항 내용
	private int faq_num; // 자주 묻는 질문번호
	private String faq_sub; // 자주 묻는 질문제목
	private String faq_cont; // 자주 묻는 질문내용
	private String us_id; // 회원 아이디
	private String own_id; // 사업자 아이디
	private int qna_num; // 문의 번호 
	private String qna_sub; // 문의 제목
	private String qna_cont; // 문의 내용
	private String qna_re; // 문의 답글 
	private Date date; // 작성 날짜
	private Date qna_redate; // 문의 답글 작성일
	
	
	
	public int getNoti_num() {
		return noti_num;
	}
	public void setNoti_num(int noti_num) {
		this.noti_num = noti_num;
	}
	public String getNoti_sub() {
		return noti_sub;
	}
	public void setNoti_sub(String noti_sub) {
		this.noti_sub = noti_sub;
	}
	public String getNoti_cont() {
		return noti_cont;
	}
	public void setNoti_cont(String noti_cont) {
		this.noti_cont = noti_cont;
	}
	public int getFaq_num() {
		return faq_num;
	}
	public void setFaq_num(int faq_num) {
		this.faq_num = faq_num;
	}
	public String getFaq_sub() {
		return faq_sub;
	}
	public void setFaq_sub(String faq_sub) {
		this.faq_sub = faq_sub;
	}
	public String getFaq_cont() {
		return faq_cont;
	}
	public void setFaq_cont(String faq_cont) {
		this.faq_cont = faq_cont;
	}
	public String getUs_id() {
		return us_id;
	}
	public void setUs_id(String us_id) {
		this.us_id = us_id;
	}
	public int getQna_num() {
		return qna_num;
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
	public Date getQna_redate() {
		return qna_redate;
	}
	public void setQna_redate(Date qna_redate) {
		this.qna_redate = qna_redate;
	}
	public String getOwn_id() {
		return own_id;
	}
	public void setOwn_id(String own_id) {
		this.own_id = own_id;
	}
	@Override
	public String toString() {
		return "CsDTO [noti_num=" + noti_num + ", noti_sub=" + noti_sub + ", noti_cont=" + noti_cont + ", faq_num="
				+ faq_num + ", faq_sub=" + faq_sub + ", faq_cont=" + faq_cont + ", us_id=" + us_id + ", own_id="
				+ own_id + ", qna_num=" + qna_num + ", qna_sub=" + qna_sub + ", qna_cont=" + qna_cont + ", qna_re="
				+ qna_re + ", date=" + date + ", qna_redate=" + qna_redate + "]";
	}

	

	
}