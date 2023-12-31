package com.foly.user.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.foly.res.db.UserBookingDTO;



/**
 * UserDAO : 데이터를 처리하는 객체 (DB연결)
 * (Data Access Object)
 * 
 * DAO는 메서드들의 집 (DB를 처리하는 메서드들이다)
 *
 */
public class UserDAO {
	
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
	
	//내정보 확인메서드
	
	public UserDTO getUserInfo(String us_id) {
		UserDTO dto = null;
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql작성(select) & pstmt 객체
			sql = "select * from user where us_id= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, us_id);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) {
				dto = new UserDTO();
				dto.setUs_id(rs.getString("us_id"));
				dto.setUs_pw(rs.getString("us_pw"));
				dto.setUs_name(rs.getString("us_name"));
				dto.setUs_nick(rs.getString("us_nick"));
				dto.setUs_email(rs.getString("us_email"));
				
				dto.setUs_birY(rs.getString("us_birY"));
				dto.setUs_birM(rs.getString("us_birM"));
				dto.setUs_birD(rs.getString("us_birD"));
				dto.setUs_tel(rs.getString("us_tel"));
				dto.setUs_addr(rs.getString("us_addr"));
				
			}
			System.out.println(" DAO : 회원정보 조회 완료 ");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return dto;
	}
	//내정보 확인메서드
	
	
	//회원 비밀번호 확인메서드
	
	public int userPwCheck(String us_id, String us_pw){

		int result = -1;
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql작성(select) & pstmt 객체
			sql = "select us_pw from user where us_id= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, us_id);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) {
				// 회원
				if(us_pw.equals(rs.getString("us_pw"))){ // 들고온거랑 db랑 동일한지
					// 비밀번호가 맞으면
					result = 1;
				}else {
					// 비밀번호가 틀리면
					result = 0;
				}
			}else {
				// 비회원 -1
				result = -1;
			}
			System.out.println(" DAO : 비밀번호 확인 완료 ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return result;
	}
	//내정보 확인메서드
	
	
	// 회원정보 수정 - userInfoUpdate(dto);
		public int userInfoUpdate(UserDTO dto) {
			System.out.println(" 받아온 DTO : "+dto);
			int result = -1;
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql 작성(select), pstmt 객체
				
				sql ="update user set us_pw=?, us_name=?, us_nick=?, us_birY=?, us_birM=?, us_birD=?, us_tel=?, us_addr=? where us_id = ?"; 
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getUs_pw());
				pstmt.setString(2, dto.getUs_name());
				pstmt.setString(3, dto.getUs_nick());
				pstmt.setString(4, dto.getUs_birY());
				pstmt.setString(5, dto.getUs_birM());
				pstmt.setString(6, dto.getUs_birD());
				pstmt.setString(7, dto.getUs_tel());
				pstmt.setString(8, dto.getUs_addr());
				pstmt.setString(9, dto.getUs_id());
				
				// 4. sql 실행
				result = pstmt.executeUpdate();
				System.out.println(" DAO : 정보 수정 "+result);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return result;
		//}
		// 회원정보 수정 - userInfoUpdate(dto);
		}

		
		//회원 탈퇴 - userDelete
		public int userDelete(UserDTO dto) {
			int result = -1;
			
			try {
				con = getCon();
				sql = "select us_pw from user where us_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getUs_id());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					
					if(dto.getUs_pw().equals(rs.getString("us_pw"))) {
						sql = "update user set us_out=1, us_od=? where us_id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setDate(1, dto.getUs_od());
						pstmt.setString(2, dto.getUs_id());
						result = pstmt.executeUpdate();
					}
					else {
						// 비밀번호 오류
						result = 0;
					}

				}
				else {
					//비회원
					result  = -1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
	             closeDB();
	         }
			
			return result;
			
		}
		//회원 탈퇴 - userDelete
		
		   // 유저 픽업(포장)2. 개수 계산 - getUserPickupCount(us_id);
	    public int getUserPickupCount(String us_id) {
	    	
	    	 System.out.println("DAO : getUserPickupCount() 호출");
	         System.out.println("DAO : 실행목적 : 글의 개수(int) 리턴");
	         int result = 0;

	         try {
	             // 1.2. 디비연결
	             con =  getCon();
	             // 3. SQL 작성 & pstmt 객체

	             sql = "select count(*) from booking where us_id = ? && bo_value=? ";
	             pstmt = con.prepareStatement(sql);
	             pstmt.setString(1, us_id);
	             pstmt.setString(2, "1");
	             
	             // 4. SQL 실행
	             rs = pstmt.executeQuery();

	             // 5. 데이터 처리
	             if(rs.next()) {
	                 // result = rs.getInt(1);  // 인덱스 사용으로 처리속도가 빠름
	                 result = rs.getInt("count(*)");
	             }
	             System.out.println("DAO : 글의 개수 조회 완료!");


	         }
	         catch(Exception e) {
	             e.printStackTrace();
	         }
	         finally {
	             closeDB();
	         }
	         return result;
	     }
	    // 끝 - 유저 픽업(포장)2. 개수 계산 - getUserPickupCount(us_id);
	    
	 // 시작 : 유저 픽업(포장)3. 리스트 정보 가져오기(페이징처리) - getUserPickupListPage(startRow,pageSize,us_id)
	 	public List<UserBookingDTO> getUserPickupListPage(int startRow, int pageSize, String us_id){
	 	
	 		System.out.println("DAO : getUserPickupListPage(startRow, pageSize,us_id) 호출");
	 		
	 		// 글 리스트 저장하는 객체
	 		List<UserBookingDTO> UserPickupList = new ArrayList<UserBookingDTO>();
	 		
	 		
	 		try {
	 			// 1.2. 디비연결 (커넥션풀)
	 			con = getCon();
	 			// 3. sql 구문 작성 & pstmt객체
	 			// 게시판 글 리스트 원하는 만큼만 조회
	 			// limit 시작위치, 개수 
	 			// : 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져옴
	 			// 			  정렬 re_ref(그룹번호) 내림차순, 
	 			//				   re_seq(답글순서) 오름차순
	 			sql = "select b.*, o.* from booking b join owner o on  b.own_id = o.own_id "
	 					+ "where b.us_id = ? && b.bo_value=?"
	 					+ "order by b.bo_num "
	 					+ "limit ?,?";
	 			pstmt = con.prepareStatement(sql);
	 			// ???
	 			pstmt.setString(1,us_id);  //(시작위치-1) startRow
	 			pstmt.setString(2,"1");  //포장 - 1, 예약 - 0
	 			pstmt.setInt(3,startRow-1);  //(시작위치-1) startRow
	 			pstmt.setInt(4,pageSize);  //개수 pageSize 10
	 			// 4. sql 실행
	 			rs = pstmt.executeQuery();
	 			// 5. 데이터처리 (rs -> BoardBean -> List)
	 			while(rs.next()) {
	 				// rs -> BoardBean 
	 				UserBookingDTO mm = new UserBookingDTO();
	 				
	 				mm.setUs_id(rs.getString("us_id"));
	 				mm.setOwn_id(rs.getString("own_id"));
	 				
	 				mm.setBo_value("1");
	 				mm.setPk_num(rs.getInt("pk_num"));
	 				mm.setBo_state(rs.getString("bo_state"));
	 				mm.setBo_menu(rs.getString("bo_menu"));
	 				mm.setBo_price(rs.getInt("bo_price"));
	 				mm.setBo_date(rs.getDate("bo_date"));
	 				mm.setRes_name(rs.getString("res_name"));
	 				
	 				
	 				
	 				// BoardBean -> List
	 				UserPickupList.add(mm);
	 				
	 			}//while
	 		
	 			System.out.println("DAO : 글 리스트(페이징처리된)글 리스트를 저장");
	 			System.out.println(UserPickupList);
	 			System.out.println("DAO : 리스트 사이즈 : " + UserPickupList.size());
	 			
	 			
	 		} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}finally {
	 			closeDB();
	 		}
	 		return UserPickupList;
	 	}
	 	
	 	//끝 : 유저 포장 리스트 정보 가져오기(페이징처리) - getMenuListPage(start)
	    
	 	
	 	
	 	 // 유저예약2. 예약 개수 계산 - getUserBookingCount(us_id);
	    public int getUserBookingCount(String us_id) {
	    	
	    	 System.out.println("DAO : getUserBookingCount() 호출");
	         System.out.println("DAO : 실행목적 : 글의 개수(int) 리턴");
	         int result = 0;

	         try {
	             // 1.2. 디비연결
	             con =  getCon();
	             // 3. SQL 작성 & pstmt 객체

	             sql = "select count(*) from booking where us_id = ? && bo_value=?";
	             pstmt = con.prepareStatement(sql);
	             pstmt.setString(1, us_id);
	             pstmt.setString(2, "0");
	             
	             // 4. SQL 실행
	             rs = pstmt.executeQuery();

	             // 5. 데이터 처리
	             if(rs.next()) {
	                 // result = rs.getInt(1);  // 인덱스 사용으로 처리속도가 빠름
	                 result = rs.getInt("count(*)");
	             }
	             System.out.println("DAO : 유저 예약글의 개수 조회 완료!");


	         }
	         catch(Exception e) {
	             e.printStackTrace();
	         }
	         finally {
	             closeDB();
	         }
	         return result;
	     }
	    // 유저예약2. 예약 글개수 계산 - getUserBookingCount(us_id);
	    
	 // 시작 : 유저예약3. 리스트 정보 가져오기(페이징처리) - getUserBookingListPage(startRow,pageSize,us_id)
	 	public List<UserBookingDTO> getUserBookingListPage(int startRow, int pageSize, String us_id){
	 	
	 		System.out.println("DAO : getUserBookingListPage(startRow, pageSize, us_id) 호출");
	 		
	 		// 글 리스트 저장하는 객체
	 		List<UserBookingDTO> UserBookingList = new ArrayList<UserBookingDTO>();
	 		
	 		
	 		try {
	 			// 1.2. 디비연결 (커넥션풀)
	 			con = getCon();
	 			// 3. sql 구문 작성 & pstmt객체
	 			// 게시판 글 리스트 원하는 만큼만 조회
	 			// limit 시작위치, 개수 
	 			// : 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져옴
	 			// 			  정렬 re_ref(그룹번호) 내림차순, 
	 			//				   re_seq(답글순서) 오름차순
	 			sql = "select b.*, o.* from booking b join owner o on b.own_id = o.own_id "
	 					+ "where b.us_id = ? && b.bo_value='0'"
	 					+ "order by b.bo_num "
	 					+ "limit ?,?";
	 			
	 			pstmt = con.prepareStatement(sql);
	 			// ???
	 			pstmt.setString(1,us_id);  //(시작위치-1) startRow
	 			// pstmt.setString(2,"0");  //예약 - 0 
	 			pstmt.setInt(2,startRow-1);  //(시작위치-1) startRow
	 			pstmt.setInt(3,pageSize);  //개수 pageSize 10
	 			// 4. sql 실행

	 			rs = pstmt.executeQuery();

	 			// 5. 데이터처리 (rs -> BoardBean -> List)
	 			while(rs.next()) {
	 				// rs -> BoardBean 

	 				UserBookingDTO mm = new UserBookingDTO();
	 				
	 				mm.setUs_id(rs.getString("us_id"));
	 				mm.setOwn_id(rs.getString("own_id"));
	 				
	 				mm.setBo_value("0");
	 				
	 				mm.setBo_num(rs.getInt("bo_num"));
	 				mm.setBo_state(rs.getString("bo_state"));
	 				mm.setRes_name(rs.getString("res_name"));
	 				mm.setRes_deposit(rs.getString("res_deposit"));;
	 				mm.setBo_date(rs.getDate("bo_date"));
	 				mm.setRes_name(rs.getString("res_name"));
	 				
	 				// BoardBean -> List
	 				UserBookingList.add(mm);
	 				
	 			}//while
	 		
	 			System.out.println("DAO : 글 리스트(페이징처리된)글 리스트를 저장");
	 			System.out.println(UserBookingList);
	 			System.out.println("DAO : 리스트 사이즈 : " + UserBookingList.size());
	 			
	 			
	 		} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}finally {
	 			closeDB();
	 		}
	 		return UserBookingList;
	 	}
	 	
	 	//끝 : 유저 예약3. 예약 리스트 정보 가져오기(페이징처리) - getUserBookingListPage(startRow,pageSize,us_id)
	 	
		   // 유저 리뷰2. 리뷰 글 개수 계산 - getUserReviewCount(us_id);
	    public int getUserReviewCount(String us_id) {
	    	
	    	 System.out.println("DAO : getUserReviewCount() 호출");
	         System.out.println("DAO : 실행목적 : 글의 개수(int) 리턴");
	         int result = 0;

	         try {
	             // 1.2. 디비연결
	             con =  getCon();
	             // 3. SQL 작성 & pstmt 객체

	             sql = "select count(*) from review where us_id = ? ";
	             pstmt = con.prepareStatement(sql);
	             pstmt.setString(1, us_id);
	             
	             // 4. SQL 실행
	             rs = pstmt.executeQuery();

	             // 5. 데이터 처리
	             if(rs.next()) {
	                 // result = rs.getInt(1);  // 인덱스 사용으로 처리속도가 빠름
	                 result = rs.getInt("count(*)");
	             }
	             System.out.println("DAO : 글의 개수 조회 완료!");


	         }
	         catch(Exception e) {
	             e.printStackTrace();
	         }
	         finally {
	             closeDB();
	         }
	         return result;
	     }
	    // 끝 - 유저 리뷰2. 리뷰글 개수 계산 - getUserReviewCount(us_id);
	    
	 // 시작 : 유저 픽업(포장)3. 리스트 정보 가져오기(페이징처리) - getUserReviewListPage(startRow,pageSize,us_id)
	 	public List<UserReviewDTO> getUserReviewListPage(int startRow, int pageSize, String us_id){
	 	
	 		System.out.println("DAO : getUserPickupListPage(startRow, pageSize,us_id) 호출");
	 		
	 		// 글 리스트 저장하는 객체
	 		List<UserReviewDTO> UserReviewList = new ArrayList<UserReviewDTO>();
	 		
	 		
	 		try {
	 			// 1.2. 디비연결 (커넥션풀)
	 			con = getCon();
	 			// 3. sql 구문 작성 & pstmt객체
	 			// 게시판 글 리스트 원하는 만큼만 조회
	 			// limit 시작위치, 개수 
	 			// : 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져옴
	 			// 			  정렬 re_ref(그룹번호) 내림차순, 
	 			//				   re_seq(답글순서) 오름차순
	 			sql = "select r.*, o.* from review r join owner o on  r.own_id = o.own_id "
	 					+ "where r.us_id = ?"
	 					+ "order by r.rvw_num "
	 					+ "limit ?,?";
	 			pstmt = con.prepareStatement(sql);
	 			// ???
	 			pstmt.setString(1,us_id);  //(시작위치-1) startRow
	 			pstmt.setInt(2,startRow-1);  //(시작위치-1) startRow
	 			pstmt.setInt(3,pageSize);  //개수 pageSize 10
	 			// 4. sql 실행
	 			rs = pstmt.executeQuery();
	 			// 5. 데이터처리 (rs -> BoardBean -> List)
	 			while(rs.next()) {
	 				// rs -> BoardBean 
	 				UserReviewDTO mm = new UserReviewDTO();
	 				
	 				mm.setUs_id(rs.getString("r.us_id"));
	 				mm.setOwn_id(rs.getString("r.own_id"));
	 				
	 				mm.setRvw_cont(rs.getString("r.rvw_cont"));
	 				mm.setRvw_star(rs.getInt("r.rvw_star"));
	 				mm.setRvw_num(rs.getInt("r.rvw_num"));
	 				mm.setRes_name(rs.getString("o.res_name"));
	 				
	 				
	 				
	 				// BoardBean -> List
	 				UserReviewList.add(mm);
	 				
	 			}//while
	 		
	 			System.out.println("DAO : 글 리스트(페이징처리된)글 리스트를 저장");
	 			System.out.println(UserReviewList);
	 			System.out.println("DAO : 리스트 사이즈 : " + UserReviewList.size());
	 			
	 			
	 		} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}finally {
	 			closeDB();
	 		}
	 		return UserReviewList;
	 	}
	 	
	 	//끝 : 유저 포장 리스트 정보 가져오기(페이징처리) - getMenuListPage(start)
	    
	    
		


}//DAO

