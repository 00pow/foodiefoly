package com.foly.user.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;






	/**
	 * UserDAO : 데이터를 처리하는 객체 (DB연결)
	 * (Data Access Object)
	 * 
	 * DAO는 메서드들의 집 (DB를 처리하는 메서드들이다)
	 *
	 */
	public class UserqDAO {
		
		// 공통변수 선언
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql = "";
		
		// 디비연결 메서드 - getCon()
		private Connection getCon() throws Exception{
			// Context 객체정보 생성
			Context initCTX = new InitialContext(); //InitialContext 프로젝트 정보 초기화
			// 필요한 정보 (DB연결정보) 가져오기
			DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/foly"); // java:comp/env/ 여기까지는 고정주소
			// 디비 연결
			con = ds.getConnection();
			System.out.println(" DAO : 디비연결 성공 "+con);
			return con;
		}
		// 디비연결 메서드 - getCon()
		
		
		// 디비 자원해제 메서드 - closeDB()
		public void closeDB() {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
				System.out.println(" DAO : 자원해제 완료 ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 디비 자원해제 메서드 - closeDB()
		
		//내 문의내역조회
		public UserqDTO getUserQna(String us_id) {
			UserqDTO dto = null;
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql작성(select) &  pstmt 객체
				sql = "select c.*, u.* from cs c join user u on c.us_id = u.us_id";
				pstmt = con.prepareStatement(sql);
				// 4. sql 실행
				//executeQuery() select 일때만 사용 한다
				//delete, insert, update 일때는 executeUpdate를 사용한다
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				if(rs.next()) {
				
					dto.setQna_num(rs.getInt("qna_num"));
					dto.setUs_nick(rs.getString("us_nick"));
					dto.setQna_sub(rs.getString("qna_sub"));
					dto.setQna_cont(rs.getString("qna_cont"));
					// dto.setQna_re(rs.getString("qna_re"));
					dto.setDate(null);
						
			
					
				}
				System.out.println(" DAO : 내 고객센터 조회 완료 ");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			// System.out.println(dto);
			// sql 잘 적었는지 실행하려면 위에 출력해보기
			return dto;
			
		}
		//내 문의내역 조회
		
		
		// 시작 - 문의글쓰기 - insertQna1();
				public void insertQna1(UserqDTO dto) throws Exception {
					// 글번호 저장하는 변수
					//bb 는 UserqDTO 타입의 매개변수
					int qna_num = 0;
					
					// 사용자가 입력한 데이터를 DB에 저장
					//System.out.println(" 전달정보 : "+bb);
					System.out.println(" DAO : insertQna1 호출");
					
					// 1.2. 디비연결s
					con = getCon();
					
					// 3. SQL 구문 작성 & pstmt 객체
					// 글번호 (qna_num) 계산하기
					sql = "select max(qna_num) from cs";
					pstmt = con.prepareStatement(sql);
					// 4. SQL 구문 실행
					rs = pstmt.executeQuery();
					// 5. 데이터 처리
					if(rs.next()) { // select문의 실행결과 커서가 있을때 게시판 작성된 글이 있음
						System.out.println("@@@@@@@@@문의내역 글 있음@@@@@@@@@@@");
						//qna_num = rs.getInt("qna_num"); (X)
						// rs.getInt("max(qna_num)") => 실행결과가 SQL NULL일때, 0을 리턴
						//qna_num = rs.getInt("max(qna_num)")+1;
						// 1번 인덱스 컬럼의 값을 가져오기
						qna_num = rs.getInt(1)+1;
						
					}
//					else { // 게시판에 작성된 글이 없음
//						System.out.println("@@@@@@@@@게시판 글 없음@@@@@@@@@@@");
//						qna_num = 1;
//					}
					
					System.out.println(" DAO : 글번호 - "+ qna_num);
					// 글쓰기 동작 처리(insert)
					// 3. sql 작성 & pstmt 객체
					
					sql= "insert into cs(us_id,qna_num,qna_sub,qna_cont,date) values(?,?,?,?,?)";
					
					pstmt = con.prepareStatement(sql);
					//???
					pstmt.setString(1, dto.getUs_id());
					pstmt.setInt(2, qna_num);
					pstmt.setString(3, dto.getQna_sub());
					pstmt.setString(4, dto.getQna_cont());
					pstmt.setDate(5, dto.getDate());
					
					
					// 4. sql실행
					pstmt.executeUpdate();
					System.out.println(" 글쓰기 완료 ");
					
					closeDB();
					
					System.out.println(" DAO : insertQna1 호출-끝");
					
				}
				// 끝 - 문의글쓰기 - insertQna1();
		
		
		// 문의글 출력 - getQuestionList
		public ArrayList<UserqDTO> getQuestionList(int startRow, int pageSize, String us_id){
			ArrayList<UserqDTO> qList = new ArrayList<>();
			try {
				con = getCon();
				sql = "select * from cs where us_id=?"
						+ "order by qna_num "
	 					+ "limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, us_id);
				pstmt.setInt(2,startRow-1);  //(시작위치-1) startRow
	 			pstmt.setInt(3,pageSize); 
				rs = pstmt.executeQuery();
				while(rs.next()) {
					UserqDTO dto = new UserqDTO();
					dto.setUs_id(rs.getString("us_id"));
					dto.setQna_num(rs.getInt("qna_num"));
					dto.setQna_sub(rs.getString("qna_sub"));
					dto.setQna_re(rs.getString("qna_re"));
					dto.setDate(rs.getDate("date"));
					
					qList.add(dto);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
	             closeDB();
	         }
			
			return qList;
		}
		// 문의글 출력 - getQuestionList
		
		
		// 비밀번호 체크 - usPwCheck
		public int usPwCheck(String us_id, String us_pw) {
			int result = -1;
			try {
				con = getCon();
				sql = "select us_pw from user where us_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, us_id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					// 회원
					if(us_pw.equals(rs.getString("us_pw"))) {
						// 본인
						result = 1;
					}else {
						// 비밀번호 오류
						result = -1;
					}
				}	
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}	
			return result;
		}
		// 비밀번호 체크 - getPwCheck
		
		// 글의 개수 계산 - getBoardcount()
		public int getQnaCount(String us_id) {
			System.out.println(" DAO : getQnaCount() 호출 ");
			System.out.println(" DAO : 실행목적 : 글의 개수(int) 리턴 ");
			int result = 0;
			
			try {
			// 1.2. 디비연결
				con = getCon();
				// 3. SQL 작성 & pstmt 객체
				sql = "select count(*) from cs where qna_num is not null and us_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, us_id);
				// 4. SQL 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				if(rs.next()) {
					//result = rs.getInt(1); //아래 코드랑 동일하나 숫자로 표현하는게 처리속도가 더 빠름
					result = rs.getInt("count(*)");
				}//if
				System.out.println(" DAO : 글의 개수 조회 완료 ");
				
			}//try
			catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
				/*
				 * try { // 작업처리가 완료 후(성공, 실패) 자원해제 if(con != null) con.close(); if(pstmt !=
				 * null) pstmt.close(); if(rs != null) rs.close(); } catch (SQLException e) { //
				 * TODO Auto-generated catch block e.printStackTrace(); }
				 */
			}// finally
			
			
			return result;
		}
		// 글의 개수 계산 - getQnacount()
		
		// 글 정보 1개 가져오기 - getQna()
		public UserqDTO getQna(String us_id) {
			UserqDTO dto = null;
			try {
				con = getCon();
				sql = "select * from cs where us_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, us_id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					dto = new UserqDTO();
					dto.setUs_id(rs.getString("us_id"));
					dto.setQna_sub(rs.getString("qna_sub"));
					dto.setQna_cont(rs.getString("qna_cont"));
					dto.setQna_num(rs.getInt("qna_num"));
					dto.setDate(rs.getDate("date"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
	             closeDB();
	         }
			return dto;
		}
		
		// 글 정보 1개 가져오기 - getQna()
		
		// 글 정보 수정하기 - usInfoUpdate
		public int usInfoUpdate(UserqDTO dto) {
			
			int result = -1;
			
			try {
				con = getCon();
				sql="select * from cs where us_id=? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getUs_id());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					sql = "update cs set date=?,qna_sub=?,qna_cont=? where us_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setDate(1, dto.getDate());
					pstmt.setString(2, dto.getQna_sub());
					pstmt.setString(3, dto.getQna_cont());
					pstmt.setString(4, dto.getUs_id());
					pstmt.executeUpdate();
					System.out.println("DAO : 정보 수정완료");
					
					result = 1;
				}
				else {//글없음
					result = -1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
	             closeDB();
	         }
			
			return result;
		}
		// 글 정보 수정하기 - usInfoUpdate
		
		// 글 삭제하기 - UserInfoDelete()
		public int UserInfoDelete(UserqDTO dto) {
			int result = -1;
			try {
				con = getCon();
				sql = "select * from cs where us_id=? and qna_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getUs_id());
				pstmt.setInt(2, dto.getQna_num());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					sql = "delete from cs where us_id=? and qna_num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getUs_id());
					pstmt.setInt(2, dto.getQna_num());
					pstmt.executeUpdate();
					System.out.println("DAO : 삭제완료");
					result = 1;
				}else {
					 result = -1;
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
	             closeDB();
	         }
			return result;
		}
		// 글 삭제하기 - UserInfoDelete()
}

