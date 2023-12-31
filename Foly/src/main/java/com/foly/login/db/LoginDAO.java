package com.foly.login.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.print.attribute.standard.PrinterMakeAndModel;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


/*
 *  LoginDAO : 데이터를 처리하는 객체 (DB처리)
 *  (Data Access Object )
 *  
 * */
public class LoginDAO {
	
	// 공통변수 선언
	private Connection con = null;
	private PreparedStatement pstmt =null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	
	// 디비연결 메서드 getCon();
	private Connection getCon() throws Exception {
		// Context 객체 정보 생성
		Context initCTX = new InitialContext();
		// 필요한정보(DB연결정보) 가져오기 
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/foly");
		
		// 디비연결
		con = ds.getConnection();
		System.out.println(" DAO : 디비연결 성공! " + con);
		
		return con;
	}
	// 디비연결 메서드 getCon();
	
	// 시작 - 디비 자원해제 메서드 closeDB();
	public void closeDB() {
		
		
			try {
				
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
				System.out.println(" DAO : 자원해제 완료! ");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	// 끝 - 디비 자원해제 메서드 closeDB();
	
	// 시작 - (개인)로그인 정보 체그 메서드 userCheck(us_id,us_pw);
	public int userCheck(String us_id, String us_pw) {
		
		int result = -1;
		
		try {
			
			// 1.2 디비연결
			con = getCon();

			// 3. sql 작성(select) & pstmt 객체
			sql = "select us_pw, us_out from user where us_id=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, us_id);
			
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			
			//5. 데이터 처리 
				if(rs.next()){ // 회원
					
					
					if(rs.getInt("us_out") == 0) { // 회원 = 0 
						
						
					
						System.out.println("회원정보가 있음 - DAO");
						if(us_pw.equals(rs.getString("us_pw"))){ // 본인(비밀번호 같음)
							result = 1;
							System.out.println("비밀번호 같음 - DAO");
							System.out.println("로그인 성공 - DAO");
						}
						else{ // 본인 아님(비밀번호 다름)
							result = 0;
							System.out.println("비밀번호 다름 - DAO");					
						}
					
					}
					else { // 탈퇴한 회원 = 1
						
						result = - 1;
						
					}
				}
				else{ // 비회원 
					result = - 1;
					System.out.println("회원정보가 없음 - DAO");	
					
			}	
				
			System.out.println("DAO : 로그인체크 완료!(" +result+") - DAO");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return result;
		
		
	}
	
	
	// 끝 - (개인)로그인 정보 체그 메서드 - userCheck(us_id,us_pw);
	
	// 시작 - (사업자)로그인 정보 체그 메서드 ownCheck(own_id,own_pw);
	public int ownCheck(String own_id, String own_pw) {
		
		int result = -1;

		
		try {
			
			// 1.2 디비연결
			con = getCon();

			// 3. sql 작성(select) & pstmt 객체
			sql = "select own_pw from owner where own_id=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, own_id);
			
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			
			//5. 데이터 처리 
				if(rs.next()){ // 회원
					System.out.println("회원정보가 있음 - DAO");
					if(own_pw.equals(rs.getString("own_pw"))){ // 본인(비밀번호 같음)
						result = 1;
						System.out.println("비밀번호 같음 - DAO");
						System.out.println("로그인 성공 - DAO");
					}
					else{ // 본인 아님(비밀번호 다름)
						result = 0;
						System.out.println("비밀번호 다름 - DAO");					
					}
						
				}
				else{ // 비회원 
					result = - 1;
					System.out.println("회원정보가 없음 - DAO");	
					
			}	
				
			System.out.println("DAO : 로그인체크 완료!(" +result+") - DAO");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return result;
		
		
	}
	
	
	// 끝 - (사업자)로그인 정보 체그 메서드 ownCheck(own_id,own_pw);
	
	
	// 시작 - (개인)회원가입 처리하는 메서드 insertUser();
	public void insertUser(UserLoginDTO dto) {
		
		
		try {
			
			// 1.2 디비연결
			con = getCon();
			System.out.println("디비연결");
			// 3. sql 작성 & pstmt 객체
			sql = "insert into user(us_id,us_pw,us_name,us_nick,us_tel,us_email,"
								+ "us_addr,us_regdate,us_birY,us_birM,us_birD)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getUs_id());
			pstmt.setString(2, dto.getUs_pw());
			pstmt.setString(3, dto.getUs_name());
			pstmt.setString(4, dto.getUs_nick());
			pstmt.setString(5, dto.getUs_tel());
			pstmt.setString(6, dto.getUs_email());
			pstmt.setString(7, dto.getUs_addr());
			pstmt.setDate(8, dto.getUs_regdate());
			pstmt.setString(9, dto.getUs_birY());
			pstmt.setString(10, dto.getUs_birM());
			pstmt.setString(11, dto.getUs_birD());
			
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO : 회원가입 성공! ");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		
	}
	
	// 끝 - (개인)회원가입 처리하는 메서드 insertUser();
	
	// 시작 - (사업자)회원가입 처리하는 메서드 insertOwn();
	public void insertOwn(OwnLoginDTO dto){
		
 		System.out.println("DAO : insertOwn(dto) 호출 - 시작");
		
		try {
			
			// 1.2 디비연결
			con = getCon();
			System.out.println("디비연결");
			// 3. sql 작성 & pstmt 객체
			sql = "insert into owner(own_id,own_pw,own_name,own_tel,own_email,own_regdate,"
								+ "res_name,res_tel,res_addr,res_deposit,res_type,"
								+ "res_mng,res_stH,res_stM,res_etH,res_etM,res_img,res_img_path)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getOwn_id());
			pstmt.setString(2, dto.getOwn_pw());
			pstmt.setString(3, dto.getOwn_name());
			pstmt.setString(4, dto.getOwn_tel());
			pstmt.setString(5, dto.getOwn_email());
			pstmt.setDate(6, dto.getOwn_regdate());
			
			pstmt.setString(7, dto.getRes_name());
			pstmt.setString(8, dto.getRes_tel());
			pstmt.setString(9, dto.getRes_addr());
			pstmt.setString(10, dto.getRes_deposit());
			pstmt.setString(11, dto.getRes_type());
			
			pstmt.setString(12, dto.getRes_mng());
			pstmt.setString(13, dto.getRes_stH());
			pstmt.setString(14, dto.getRes_stM());
			pstmt.setString(15, dto.getRes_etH());
			pstmt.setString(16, dto.getRes_etM());
			pstmt.setString(17, dto.getRes_img());
			pstmt.setString(18, dto.getRes_img_path());
			
			System.out.println("@@@");
			System.out.println(dto.getRes_img_path());
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO : 회원가입 성공! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		
	}
	
	// 끝 - (사업자)회원가입 처리하는 메서드 insertOwn();
	
	
	// 시작 - (개인)아이디 찾기 - 정보 체그 메서드 userFindIdCheck(us_name,us_email);
	public String userFindIdCheck(String us_name, String us_email) {


		String us_id = "찾는아이디없음";
		//int result = -1;
		
		try {
			
			// 1.2 디비연결
			con = getCon();

			// 3. sql 작성(select) & pstmt 객체
			sql = "select us_id from user where us_name=? && us_email=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, us_name);
			pstmt.setString(2, us_email);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			
			//5. 데이터 처리 
			if(rs.next()){ // 회원
				System.out.println("회원정보가 있음 - DAO");
				us_id = rs.getString("us_id");

			}
			else{ // 비회원 
				//result = - 1;
				System.out.println("회원정보가 없음 - DAO");	
					
			}	
				
			System.out.println("DAO : 정보체크 완료! - DAO");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return (us_id);
		
		
	}
	
	
	// 끝 - (개인)아이디 찾기 - 정보 체그 메서드 userFindIdCheck(us_name,us_email);
	
	
	// 시작 - (개인)비밀번호 찾기 - 정보 체그 메서드 userFindPwCheck(us_name,us_id,us_email);
	public String userFindPwCheck(String us_name,String us_id, String us_email) {
		
		
		String us_pw = "찾는비밀번호없음";
		//int result = -1;
		
		try {
			
			// 1.2 디비연결
			con = getCon();
			
			// 3. sql 작성(select) & pstmt 객체
			sql = "select us_pw from user where us_name=? && us_email=? && us_id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, us_name);
			pstmt.setString(2, us_email);
			pstmt.setString(3, us_id);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			
			//5. 데이터 처리 
			if(rs.next()){ // 회원
				System.out.println("회원정보가 있음 - DAO");
				us_pw = rs.getString("us_pw");
				
			}
			else{ // 비회원 
				//result = - 1;
				System.out.println("회원정보가 없음 - DAO");	
				
			}	
			
			System.out.println("DAO : 비밀번호 있는지 체크 완료! - DAO");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return (us_pw);
		
		
	}
	
	
	// 끝 - (개인)비밀번호 찾기 - 정보 체그 메서드 userFindPwCheck(us_name,us_id,us_email);
	
	
	// 시작 - (사업자)비밀번호 찾기 - 정보 체그 메서드 OwnFindPwCheck(own_name,own_id,own_email);
	public String OwnFindPwCheck(String own_name,String own_id, String own_email) {
		
		
		String own_pw = "찾는비밀번호없음";
		//int result = -1;
		
		try {
			
			// 1.2 디비연결
			con = getCon();
			
			// 3. sql 작성(select) & pstmt 객체
			sql = "select own_pw from owner where own_name=? && own_email=? && own_id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, own_name);
			pstmt.setString(2, own_email);
			pstmt.setString(3, own_id);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			
			//5. 데이터 처리 
			if(rs.next()){ // 회원
				System.out.println("회원정보가 있음 - DAO");
				own_pw = rs.getString("own_pw");
				
			}
			else{ // 비회원 
				//result = - 1;
				System.out.println("회원정보가 없음 - DAO");	
				
			}	
			
			System.out.println("DAO : 비밀번호 있는지 체크 완료! - DAO");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return (own_pw);
		
		
	}
	
	
	// 끝 - (사업자)비밀번호 찾기 - 정보 체그 메서드 OwnFindPwCheck(own_name,own_id,own_email);
	
	
	
	
	// 개인 회원가입 시 이메일 중복 체크 
	
	public int CheckEmail(String us_email) {
		int result = 1;
		try {
			
			// 1.2 디비연결
			con = getCon();
			
			// 3. sql 작성(select) & pstmt 객체
			sql = "select * from user where us_email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, us_email);
			
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			
			//5. 데이터 처리 
			
			 if(rs.next()) {
				 result = 0;  // 이미 존재하는 경우, 생성 불가능
	                System.out.println("DAO 내부) email 중복");
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	
	
	return result;
	// 회원가입 시 user email 중복 체크
	//리턴값을 ajax한테보내기.
	 }
	
//	
	//=========================== us_id 중복확인하는 SQL로직 ===============================
    public int CheckId(String us_id) {  // 유저가 입력한 값을 매개변수로 한다
        /*
		회원가입 JSP에서 받아온 ID값과 기존 테이블의 ID값이 일치하는지 여부를
		확인하기 위해 Select명령어를 사용해 DAO를 만들었습니다. 
		Select값이 없는 경우 0을 출력하고,
        Select값이 있는 경우 1을 출력하도록 설정했습니다.*/
        String sql = "select * from user where us_id=?"; // 입력값이 테이블에 있는지 확인
       
        int result = -1;
        try {
        	// 1.2 디비연결
			con = getCon();
            pstmt = con.prepareStatement(sql); // pstmt에 위의 query 저장 후 DB에 연결 준비
            pstmt.setString(1, us_id); //첫번째 ?에 id 변수 셋팅
            System.out.println("DAO 내부) DB에 검색하는 id값(input에 쓴 값) : "+ us_id);
            
            rs = pstmt.executeQuery(); // query 실행 후 그 결과값을 rs에 저장
            
            
            if(rs.next()) {
                result=0; // 이미 존재하는 경우, 생성 불가능
                System.out.println("DAO 내부) id 중복");
                
            } else {
            	result = 1;  // 존재하지 않는 경우, 생성 가능
                System.out.println("DAO 내부) id 사용가능");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			closeDB();
		}
        
        return result;
    }

//=========================== own_id 중복확인하는 SQL로직 ===============================
	public int OwnCheckId(String own_id) {  // 유저가 입력한 값을 매개변수로 한다
    /*
	회원가입 JSP에서 받아온 ID값과 기존 테이블의 ID값이 일치하는지 여부를
	확인하기 위해 Select명령어를 사용해 DAO를 만들었습니다. 
	Select값이 없는 경우 0을 출력하고,
    Select값이 있는 경우 1을 출력하도록 설정했습니다.*/
    String sql = "select * from owner where own_id=?"; // 입력값이 테이블에 있는지 확인
   
    int result = -1;
    try {
    	// 1.2 디비연결
		con = getCon();
        pstmt = con.prepareStatement(sql); // pstmt에 위의 query 저장 후 DB에 연결 준비
        pstmt.setString(1, own_id); //첫번째 ?에 id 변수 셋팅
        System.out.println("DAO 내부) DB에 검색하는 id값(input에 쓴 값) : "+ own_id);
        
        rs = pstmt.executeQuery(); // query 실행 후 그 결과값을 rs에 저장
        
        
        if(rs.next()) {
            result=0; // 이미 존재하는 경우, 생성 불가능
            System.out.println("DAO 내부) id 중복");
            
        } else {
        	result = 1;  // 존재하지 않는 경우, 생성 가능
            System.out.println("DAO 내부) id 사용가능");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }finally {
		closeDB();
	}
    
    return result;
}

}
