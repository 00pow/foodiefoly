package com.foly.util;

/**
 * 	ActionForward : 페이지 이동에 필요한 정보를 저장하는 객체
 * 	(기차표)
 */
public class ActionForward {
	private String path; //이동할 페이지 주소
	private boolean isRedirect; //이동 방식
	//이동 방식 - true : sendRedirect() 
	//			- false : forward()
	
	//alt shift s + r
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	//alt shift s + s
	@Override
	public String toString() {
		return "ActionForward(기차표) [path(목적지)=" + path + ", isRedirect(이동방법)=" + isRedirect + "]";
	}
	
}
