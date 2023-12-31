package com.foly.own.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.foly.res.db.ResDTO;
import com.foly.res.db.UserBookingDTO;






/*
 *  LoginDAO : 데이터를 처리하는 객체 (DB처리)
 *  (Data Access Object )
 *  
 * */
public class OwnMainDAO {
	
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
	
	// 가게 정보 조회 - getOwnRestInfo(own_id);
    public OwnMainDTO getOwnRestInfo(String own_id) {
    	OwnMainDTO dto = null;
        try {
            // 1.2. 디비연결
            con = getCon();
            // 3. sql작성(select) &  pstmt 객체
            sql = "select * from owner where own_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, own_id);
            // 4. sql 실행
            rs = pstmt.executeQuery();
            // 5. 데이터 처리
            if(rs.next()) {
                dto = new OwnMainDTO();

                dto.setRes_img(rs.getString("res_img"));
                dto.setRes_img_path(rs.getString("res_img_path"));
                dto.setRes_name(rs.getString("res_name"));
                dto.setRes_tel(rs.getString("res_tel"));
                dto.setRes_addr(rs.getString("res_addr"));
                dto.setRes_deposit(rs.getString("res_deposit"));
                dto.setRes_type(rs.getString("res_type"));
                
                dto.setRes_stH(rs.getString("res_stH"));
                dto.setRes_stM(rs.getString("res_stM"));
                dto.setRes_etH(rs.getString("res_etH"));
                dto.setRes_etM(rs.getString("res_etM"));
            }
            System.out.println(" DAO : 가게정보 조회 완료 ");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeDB();
        }
        return dto;
    }
    // 가게 정보 조회 - getOwnRestInfo(ow_id);
    
    
    // 사업자 정보 조회 - getOwnInfo(own_id);
    public OwnMainDTO getOwnInfo(String own_id) {
    	OwnMainDTO dto = null;
    	try {
    		// 1.2. 디비연결
    		con = getCon();
    		// 3. sql작성(select) &  pstmt 객체
    		sql = "select * from owner where own_id=?";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, own_id);
    		// 4. sql 실행
    		rs = pstmt.executeQuery();
    		// 5. 데이터 처리
    		if(rs.next()) {
    			dto = new OwnMainDTO();
    			dto.setOwn_id(rs.getString("own_id"));
    			dto.setOwn_pw(rs.getString("own_pw"));
    			dto.setOwn_name(rs.getString("own_name"));
    			dto.setOwn_tel(rs.getString("own_tel"));
    			dto.setOwn_email(rs.getString("own_email"));
    			
    		}
    		System.out.println(" DAO : 사업자 정보 조회 완료 ");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}finally {
    		closeDB();
    	}
    	return dto;
    }
    // 가게 정보 조회 - getOwnInfo(ow_id);
    
    
    // 가게메뉴2. 개수 계산 - getMenuCount(own_id);
    public int getMenuCount(String own_id) {
    	
    	 System.out.println("DAO : getMenuCount() 호출");
         System.out.println("DAO : 실행목적 : 글의 개수(int) 리턴");
         int result = 0;

         try {
             // 1.2. 디비연결
             con =  getCon();
             // 3. SQL 작성 & pstmt 객체

             sql = "select count(*) from restaurant where own_id = ?";
             pstmt = con.prepareStatement(sql);
             pstmt.setString(1, own_id);
             
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
    // 가게메뉴2. 개수 계산 - getMenuCount(own_id);
    
 // 시작 : 메뉴3. 리스트 정보 가져오기(페이징처리) - getMenuListPage(startRow,pageSize,own_id)
 	public List<ResDTO> getMenuListPage(int startRow, int pageSize, String own_id){
 	
 		System.out.println("DAO : getMenuListPage(startRow, pageSize) 호출");
 		
 		// 글 리스트 저장하는 객체
 		List<ResDTO> menuList = new ArrayList<ResDTO>();
 		
 		
 		try {
 			// 1.2. 디비연결 (커넥션풀)
 			con = getCon();
 			// 3. sql 구문 작성 & pstmt객체
 			// 게시판 글 리스트 원하는 만큼만 조회
 			// limit 시작위치, 개수 
 			// : 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져옴
 			// 			  정렬 re_ref(그룹번호) 내림차순, 
 			//				   re_seq(답글순서) 오름차순
 			sql = "select * from restaurant "
 					+ "where own_id = ?"
 					+ "order by me_num "
 					+ "limit ?,?";
 			pstmt = con.prepareStatement(sql);
 			// ???
 			pstmt.setString(1,own_id);  //(시작위치-1) startRow
 			pstmt.setInt(2,startRow-1);  //(시작위치-1) startRow
 			pstmt.setInt(3,pageSize);  //개수 pageSize 10
 			// 4. sql 실행
 			rs = pstmt.executeQuery();
 			// 5. 데이터처리 (rs -> BoardBean -> List)
 			while(rs.next()) {
 				// rs -> BoardBean 
 				ResDTO dto = new ResDTO();
 				
 				dto.setOwn_id(rs.getString("own_id"));
 				dto.setMe_num(rs.getInt("me_num"));
 				dto.setMe_name(rs.getString("me_name"));
 				dto.setMe_price(rs.getInt("me_price"));
 				dto.setMe_exp(rs.getString("me_exp"));
 				
 				
 				// BoardBean -> List
 				menuList.add(dto);
 				
 			}//while
 		
 			System.out.println("DAO : 글 리스트(페이징처리된)글 리스트를 저장");
 			System.out.println(menuList);
 			System.out.println("DAO : 리스트 사이즈 : " + menuList.size());
 			
 			
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}finally {
 			closeDB();
 		}
 		return menuList;
 	}
 	
 	//끝 : 메뉴3. 리스트 정보 가져오기(페이징처리) - getMenuListPage(start)
    
 	
	// 시작 : 메뉴 정보(1개) 가져오기 - getMenuContent(own_id, me_num)
	public ResDTO getMenuContent(String own_id,int me_num) {
		System.out.println("DAO : 글정보 (1개) 가져오기 - getMenuContent(own_id, me_num) 호출");
		
		// 객체 레퍼런스만 준비, 미리 생성하지 않음
		ResDTO dto = null;
		try {
			// 1,2 디비연결
			con = getCon();
			// 3. sql 작성(select) & pstmt 객체
			sql = "select * from restaurant where own_id=? && me_num =? ";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1,own_id);
			pstmt.setInt(2,me_num);
			System.out.println(own_id);
			System.out.println(me_num);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리(rs -> 객체(MenuBean))
			if(rs.next()) {
				dto = new ResDTO();
				System.out.println("if문에 들어옴");
				// rs -> mm 저장

				dto.setOwn_id(own_id);
				dto.setMe_num(rs.getInt("me_num"));
				dto.setMe_name(rs.getString("me_name"));
				dto.setMe_price(rs.getInt("me_price"));
				dto.setMe_exp(rs.getString("me_exp"));
				System.out.println(rs.getString("me_img"));
			
			
			} //if
			System.out.println("DAO : " + me_num + "번 글정보 가져오기 완료!");
			System.out.println("DAO : " + dto );
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		// 메뉴정보 리턴
		return dto;
	}
	
	
	
	// 끝 : 메뉴글 정보(1개) 가져오기 - getMenuContent(own_id, me_num)
	
    
    
    // 가게메뉴1. 정보 조회 - getOwnMenuInfo(own_id);
    public ResDTO getOwnMenuInfo(String own_id) {
    	ResDTO dto = null;
    	try {
    		// 1.2. 디비연결
    		con = getCon();
    		// 3. sql작성(select) &  pstmt 객체
    		sql = "select * from restaurant where own_id=?";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, own_id);
    		// 4. sql 실행
    		rs = pstmt.executeQuery();
    		// 5. 데이터 처리
    		if(rs.next()) {
    			dto = new ResDTO();
    			dto.setMe_num(rs.getInt("me_num"));
    			dto.setMe_name(rs.getString("me_name"));
    			dto.setMe_price(rs.getInt("me_price"));
    			dto.setMe_exp(rs.getString("me_exp"));
    		}
    		System.out.println(" DAO : 가게 메뉴정보 조회 완료 ");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}finally {
    		closeDB();
    	}
    	return dto;
    }
    // 가게메뉴1. 정보 조회 - getOwnMenuInfo(own_id);
    
    // 메뉴 추가글쓰기 - insertMenu();
 	public void insertMenu(ResDTO dto) throws Exception{
 		
 		// 글 번호 저장하는 변수
 		int me_num = 0;
 		
 		// 사용자가 입력한 데이터를 DB에 저장
 		// System.out.println("전달정보 : " + bb);
 		System.out.println("DAO : insertMenu(dto) 호출 - 시작");

	      // 1.2 디비연결
	      Connection con = getCon();
 			
	      // 3. sql구문 작성(select) & pstmt 객체	
	      // 글번호(bno) 계산하기(int)
	      // 첫글 1 글2 글3... 글10 글11
	      // 작성글 직전의 번호(작성된 가장 큰 글번호) + 1 => 최신글 번호
 			sql = "select max(me_num) from restaurant where own_id=?";

    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, dto.getOwn_id());
 		// 4. sql 구문실행
 			rs = pstmt.executeQuery();
 		
 		// 5. 데이터 처리
 			if(rs.next()) {	// select문의 실행결과 커서가 있을 때 (게시판 작성된 글이 있음)
 				System.out.println("@@@게시판 글이 있음@@@");
 				
 				// bno = rs.getInt("bno"); (x)
 				//rs.getInt() => 실행결과가 SQL NULL일때, 0을 리턴
 				//bno = rs.getInt("max(bno)")+1;  // select문의 결과 + 1
 				// 1번 인덱스 컬럼의 값을 가져오기
 				me_num = rs.getInt(1) + 1;
 			
 			}
 			
 		System.out.println("DAO : 글번호 - " + me_num);
 		
 		
 		// 글쓰기 동작 처리(insert)
 		// 3. sql작성 & pstmt 객체
 			sql = "insert into restaurant(own_id,me_num, me_name, me_price, me_exp, me_img,me_img_path)"
 					+ "values(?,?,?,?,?,?,?)";
 		
 			pstmt= con.prepareStatement(sql);
 		 
 		// 물음표에 값 채우기
 			pstmt.setString(1, dto.getOwn_id());
 			
 			pstmt.setInt(2, me_num);
 			pstmt.setString(3, dto.getMe_name());
 			pstmt.setInt(4, dto.getMe_price());
 			pstmt.setString(5, dto.getMe_exp());
 			pstmt.setString(6, dto.getMe_img());
 			pstmt.setString(7, dto.getMe_img_path());

 		 
 		// 4. sql 구문실행
 		 	pstmt.executeUpdate();
 		 	System.out.println("메뉴 등록 완료!");
 		 	
 		 	closeDB();
 		
 		System.out.println("DAO : insertMenu(mm) 호출 - 끝");
 		
 	}
	
	// 시작 : 메뉴 정보 삭제하기 - OwnMenuDelete(mm)
	
	public int OwnMenuDelete(ResDTO mm) {
		int result = -1; // -1(에러-계정) 0(에러-비밀번호) 1(정상)
		
		try {
			// 1.2 디비연결
			con = getCon();
			// 3. sql구문 작성(기존의 회원여부확인) & pstmt 객체
			sql = "select * from restaurant where own_id =? and me_num =?";
			
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1,mm.getOwn_id());
			pstmt.setInt(2,mm.getMe_num());
			// 4. sql 실행
			 rs = pstmt.executeQuery();
			// 5. 데이터 처리
				// 3.sql 구문 실행(update)
			 if(rs.next()) { // 글이 있으면
					
					sql = "delete from restaurant where own_id=? and me_num=?";
					pstmt = con.prepareStatement(sql);
					// ???
					pstmt.setString(1,mm.getOwn_id());
					pstmt.setInt(2,mm.getMe_num());
					
					//4. sql 구문 실행
					pstmt.executeUpdate();
					System.out.println("DAO : 메뉴 삭제완료!");
					
					result = 1;
			}
			else { // 글이 없다
				 result = -1;
			}
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		
		return result;
	}
	
	
	// 끝 : 메뉴 정보 삭제하기 - OwnMenuDelete(mm)
	
	// 사업자 비밀번호 조회
	
	public int ownPwCheck(String own_id, String own_pw) {
		int result = 0;
		try {
			// 1.2. 디비연결
			con = getCon();
		// 3. sql 작성(select) & pstmt 객체
			sql = "select own_pw from owner where own_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, own_id);			
		// 4. sql 실행
			rs = pstmt.executeQuery();			
		// 5. 데이터처리
			if(rs.next()) {
				// 회원
				if(own_pw.equals(rs.getString("own_pw"))) {
					// 본인
					result = 1;
				}else {
					// 비밀번호 오류
					result = 0;
				}
				
			}
			System.out.println("DAO : 로그인체크 완료 ("+result+")");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}	
		return result;
	}
	
	// 가게 정보 수정 업데이트
	
	public void resUpdate(OwnMainDTO dto, String own_id) {
			
			
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql구문작성(select) & pstmt객체
				sql = "update owner set res_name=? , res_tel=?,res_addr=?,res_deposit=?,res_type=?,res_mng=?,res_stH=?,res_stM=?,res_etH=?,res_etM=? where own_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getRes_name());
				pstmt.setString(2, dto.getRes_tel());
				pstmt.setString(3, dto.getRes_addr());
				pstmt.setString(4, dto.getRes_deposit());
				pstmt.setString(5, dto.getRes_type());
				pstmt.setString(6, dto.getRes_mng());
				pstmt.setString(7, dto.getRes_stH());
				pstmt.setString(8, dto.getRes_stM());
				pstmt.setString(9, dto.getRes_etH());
				pstmt.setString(10, dto.getRes_etM());
				pstmt.setString(11, own_id);
				
				
				// 4. sql 구문실행
				pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
		
	}
	
	// 가게 정보 업데이트 끝
	
	// 시작 : 메뉴 정보 수정하기 - OwnMenuUpdate(mm)
	
		public int OwnMenuUpdate(ResDTO mm) {
			int result = -1; // -1(글 X) 1(수정)
			
			try {
				// 1.2 디비연결
				con = getCon();
				// 3. sql구문 작성(기존의 회원여부확인) & pstmt 객체
				sql = "select * from restaurant where own_id =? and me_num =?";
				
				pstmt = con.prepareStatement(sql);
				// ???
				pstmt.setString(1,mm.getOwn_id());
				pstmt.setInt(2,mm.getMe_num());
				// 4. sql 실행
				 rs = pstmt.executeQuery();
				// 5. 데이터 처리
					// 3.sql 구문 실행(update)
				 if(rs.next()) { // 글이 있으면
					
						sql = "update restaurant set me_name=?,me_price=?,me_exp=? "
								+ "where own_id =? and me_num =?";
						pstmt = con.prepareStatement(sql);
						// ???
						pstmt.setString(1,mm.getMe_name());
						pstmt.setInt(2,mm.getMe_price());
						pstmt.setString(3,mm.getMe_exp());

						pstmt.setString(4,mm.getOwn_id());
						pstmt.setInt(5,mm.getMe_num());
						
						//4. sql 구문 실행
						pstmt.executeUpdate();
						System.out.println("DAO : 메뉴 정보 수정완료!");
						
						result = 1;
					}
				else { // 글이 없다
					result = -1;
				}
					
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			
			return result;
		}
		
		// 끝 : 메뉴 정보 수정하기 - OwnMenuUpdate(mm)
		
		
		// 시작 : 내정보 수정하기 - OwnInfoUpdate
		public int OwnInfoUpdate(OwnMainDTO om) {
			int result = -1; // -1(글 X) 1(수정)
			
			try {
				// 1.2 디비연결
				con = getCon();
				// 3. sql구문 작성(기존의 회원여부확인) & pstmt 객체
				sql = "select * from owner where own_id =?";
				
				pstmt = con.prepareStatement(sql);
				// ???
				pstmt.setString(1,om.getOwn_id());
				// 4. sql 실행
				 rs = pstmt.executeQuery();
				// 5. 데이터 처리
					// 3.sql 구문 실행(update)
				 if(rs.next()) { // 글이 있으면
					
						sql = "update owner set own_pw =? ,own_name=?,own_tel=?,own_email=? where own_id=?" ;
						pstmt = con.prepareStatement(sql);
						// ???
						pstmt.setString(1, om.getOwn_pw());
						pstmt.setString(2,om.getOwn_name());
						pstmt.setString(3,om.getOwn_tel());
						pstmt.setString(4,om.getOwn_email());
						pstmt.setString(5,om.getOwn_id());
						
						
						//4. sql 구문 실행
						pstmt.executeUpdate();
						System.out.println("DAO : 정보 수정완료!");
						
						result = 1;
					}
				else { // 글이 없다
					result = -1;
				}
					
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			
			return result;
		}
		
		// 끝 : 내정보 수정하기 - OwnInfoUpdate
		
		//시작 : 사업 비밀번호 확인메서드

	    public int ownInfoPwCheck(String own_id, String own_pw){

	        int result = 0;
	        try {
	            // 1.2. 디비연결
	            con = getCon();
	            // 3. sql작성(select) & pstmt 객체
	            sql = "select own_pw from owner where own_id= ?";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, own_id);
	            // 4. sql 실행
	            rs = pstmt.executeQuery();
	            // 5. 데이터 처리
	            if(rs.next()) {
	                // 회원
	                if(own_pw.equals(rs.getString("own_pw"))){ // 들고온거랑 db랑 동일한지
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
	    // 끝 : 내정보 확인메서드	
	    
		// 포장1. 정보 조회 - getOwnPickup(own_id);
	    public UserBookingDTO getOwnPickup(String own_id) {
	    	UserBookingDTO dto = null;
	        try {
	            // 1.2. 디비연결
	            con = getCon();
	            // 3. sql작성(select) &  pstmt 객체
	            sql = "select * from booking where own_id=?";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, own_id);
	            // 4. sql 실행
	            rs = pstmt.executeQuery();
	            // 5. 데이터 처리
	            if(rs.next()) {
	            	 if(rs.getInt("pk_num")>=1) {
		                dto = new UserBookingDTO();
		                dto.setOwn_id(rs.getString("own_id"));
		                dto.setUs_id(rs.getString("us_id"));
		                dto.setBo_value(rs.getString("bo_value"));
		                dto.setPk_num(Integer.parseInt(rs.getString("pk_num")));
		                dto.setBo_state(rs.getString("bo_state"));
		                
		                dto.setBo_menu(rs.getString("bo_menu"));
		                dto.setBo_count(Integer.parseInt(rs.getString("bo_count")));
		                dto.setBo_price(Integer.parseInt(rs.getString("bo_price")));
		                dto.setPay_num(rs.getString("pay_num"));
	            	 }
	            }
	            System.out.println(" DAO : 포장 정보 조회 완료 ");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            closeDB();
	        }
	        return dto;
	    }
	    // 사업자 - 포장1. 정보 조회 - getOwnPickup(own_id);
	    
	    
	    // 사업자 - 포장2. 정보 계산 - getOwnPickupCount(own_id);
	    public int getOwnPickupCount(String own_id) {
	    	
	    	 System.out.println("DAO : getOwnPickupCount() 호출");
	         System.out.println("DAO : 실행목적 : 글의 개수(int) 리턴");
	         int result = 0;

	         try {
	             // 1.2. 디비연결
	             con =  getCon();
	             // 3. SQL 작성 & pstmt 객체

	             sql = "select * from booking where own_id = ?";
	             pstmt = con.prepareStatement(sql);
	             pstmt.setString(1, own_id);
	             
	             // 4. SQL 실행
	             rs = pstmt.executeQuery();

	             // 5. 데이터 처리
	             if(rs.next()) {
	            	 if(rs.getInt("pk_num")>=1) {
	                 // result = rs.getInt(1);  // 인덱스 사용으로 처리속도가 빠름
	            		 result = result + 1;
	             }
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
	    // 끝 : 사업자 - 포장2. 정보 계산 - getOwnPickupCount(own_id);
	    
	 // 시작 : 사업자 - 포장3. 정보 리스트 정보 가져오기(페이징처리) - getOwnPickupListPage(startRow,pageSize,own_id)
	 	public List<UserBookingDTO> getOwnPickupListPage(int startRow, int pageSize, String own_id){
	 	
	 		System.out.println("DAO : getOwnPickupListPage(startRow, pageSize) 호출");
	 		
	 		// 글 리스트 저장하는 객체
	 		List<UserBookingDTO> PickupList = new ArrayList<UserBookingDTO>();
	 		
	 		
	 		try {
	 			// 1.2. 디비연결 (커넥션풀)
	 			con = getCon();
	 			// 3. sql 구문 작성 & pstmt객체
	 			// 게시판 글 리스트 원하는 만큼만 조회
	 			// limit 시작위치, 개수 
	 			// : 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져옴
	 			// 			  정렬 re_ref(그룹번호) 내림차순, 
	 			//				   re_seq(답글순서) 오름차순
	 			sql = "select * from booking "
	 					+ "where own_id = ?"
	 					+ "order by pk_num "
	 					+ "limit ?,?";
	 			pstmt = con.prepareStatement(sql);
	 			// ???
	 			pstmt.setString(1,own_id);  //(시작위치-1) startRow
	 			pstmt.setInt(2,startRow-1);  //(시작위치-1) startRow
	 			pstmt.setInt(3,pageSize);  //개수 pageSize 10
	 			// 4. sql 실행
	 			rs = pstmt.executeQuery();
	 			// 5. 데이터처리 (rs -> BoardBean -> List)
	 			while(rs.next()) {
	 				
	 				if(rs.getInt("pk_num")>=1) {
	 				// rs -> BoardBean 
 					UserBookingDTO mm = new UserBookingDTO();
	 				
	 				mm.setOwn_id(rs.getString("own_id"));
	 				mm.setUs_id(rs.getString("us_id"));
	 				mm.setBo_value(rs.getString("bo_value"));
	 				mm.setPk_num(Integer.parseInt(rs.getString("pk_num")));
	 				mm.setBo_state(rs.getString("bo_state"));
	                
	 				mm.setBo_menu(rs.getString("bo_menu"));
	 				mm.setBo_count(Integer.parseInt(rs.getString("bo_count")));
	 				mm.setBo_price(Integer.parseInt(rs.getString("bo_price")));
	 				mm.setPay_num(rs.getString("pay_num"));
	 				
	 				// BoardBean -> List
	 				PickupList.add(mm);
	 				}
	 				
	 			}//while
	 		
	 			System.out.println("DAO : 글 리스트(페이징처리된)글 리스트를 저장");
	 			System.out.println(PickupList);
	 			System.out.println("DAO : 리스트 사이즈 : " + PickupList.size());
	 			
	 			
	 		} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}finally {
	 			closeDB();
	 		}
	 		return PickupList;
	 	}
	 	
	 	//끝 : 사업자 - 포장3. 리스트 정보 가져오기(페이징처리) - getMenuListPage(start)
	 	
	 	
		// 사업자 예약1. 정보 조회 - getOwnBook(own_id);
	    public UserBookingDTO getOwnBook(String own_id) {
	    	UserBookingDTO dto = null;
	        try {
	            // 1.2. 디비연결
	            con = getCon();
	            // 3. sql작성(select) &  pstmt 객체
	            sql = "select * from booking where own_id=?";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, own_id);
	            // 4. sql 실행
	            rs = pstmt.executeQuery();
	            // 5. 데이터 처리
	            if(rs.next()) {
	            	
	            	if(rs.getInt("bo_num")>=1) {
		                dto = new UserBookingDTO();
		                dto.setOwn_id(rs.getString("own_id"));
		                dto.setUs_id(rs.getString("us_id"));
		                dto.setBo_value(rs.getString("bo_value"));
		                dto.setPk_num(Integer.parseInt(rs.getString("pk_num")));
		                dto.setBo_state(rs.getString("bo_state"));
		 				//mm.setBo_date(rs.getString("bo_date"));
		                
		                dto.setBo_menu(rs.getString("bo_menu"));
		                dto.setBo_count(Integer.parseInt(rs.getString("bo_count")));
		                dto.setBo_price(Integer.parseInt(rs.getString("bo_price")));
		                dto.setPay_num(rs.getString("pay_num"));

	            	}
            	}
	            System.out.println(" DAO : 포장 정보 조회 완료 ");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            closeDB();
	        }
	        return dto;
	    }
	    // 사업자 - 예약1. 정보 조회 - getOwnBook(own_id);
	    
	    
	    // 사업자 - 예약2. 정보 계산 - getOwnBookCount(own_id);
	    public int getOwnBookCount(String own_id) {
	    	
	    	 System.out.println("DAO : getOwnBookCount() 호출");
	         System.out.println("DAO : 실행목적 : 글의 개수(int) 리턴");
	         int result = 0;

	         try {
	             // 1.2. 디비연결
	             con =  getCon();
	             // 3. SQL 작성 & pstmt 객체

	             sql = "select * from booking where own_id = ?";
	             pstmt = con.prepareStatement(sql);
	             pstmt.setString(1, own_id);
	             System.out.print(own_id);
	             // 4. SQL 실행
	             rs = pstmt.executeQuery();

	             // 5. 데이터 처리
	             if(rs.next()) {
	            	 System.out.println("@@@@@@@@");
	            	 if(rs.getInt("bo_num")>=1) {
	                 // result = rs.getInt(1);  // 인덱스 사용으로 처리속도가 빠름
	                 result = result + 1;
	                 System.out.print(result);
	             }
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
	    // 끝 : 사업자 - 예약2. 정보 계산 - getOwnBookCount(own_id);
	    
	 // 시작 : 사업자 - 예약3. 정보 리스트 정보 가져오기(페이징처리) - getOwnBookListPage(startRow,pageSize,own_id)
	 	public List<UserBookingDTO> getOwnBookListPage(int startRow, int pageSize, String own_id){
	 	
	 		System.out.println("DAO : getOwnBookListPage(startRow, pageSize) 호출");
	 		
	 		// 글 리스트 저장하는 객체
	 		List<UserBookingDTO> BookingList = new ArrayList<UserBookingDTO>();
	 		
	 		
	 		try {
	 			// 1.2. 디비연결 (커넥션풀)
	 			con = getCon();
	 			// 3. sql 구문 작성 & pstmt객체
	 			// 게시판 글 리스트 원하는 만큼만 조회
	 			// limit 시작위치, 개수 
	 			// : 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져옴
	 			// 			  정렬 re_ref(그룹번호) 내림차순, 
	 			//				   re_seq(답글순서) 오름차순
	 			
	 			sql = "select * from booking "
	 					+ "where own_id = ?"
	 					+ "order by bo_num "
	 					+ "limit ?,?";
	 			pstmt = con.prepareStatement(sql);
	 			// ???
	 			pstmt.setString(1,own_id);  //(시작위치-1) startRow
	 			pstmt.setInt(2,startRow-1);  //(시작위치-1) startRow
	 			pstmt.setInt(3,pageSize);  //개수 pageSize 10
	 			
	 			// 4. sql 실행
	 			rs = pstmt.executeQuery();
	 			// 5. 데이터처리 (rs -> BoardBean -> List)
	 			while(rs.next()) {
	 				// rs -> BoardBean
	 				if(rs.getInt("bo_num")>=1) {
	 					
	 					UserBookingDTO mm = new UserBookingDTO();
		 				
		 				mm.setOwn_id(rs.getString("own_id"));
		 				mm.setUs_id(rs.getString("us_id"));
		 				mm.setBo_value(rs.getString("bo_value"));
		 				mm.setBo_num(Integer.parseInt(rs.getString("bo_num")));
		 				mm.setBo_per(rs.getString("bo_per"));
		 				//mm.setBo_date(rs.getString("bo_date"));
		 				mm.setBo_state(rs.getString("bo_state"));
		                
		 				mm.setRes_deposit(rs.getString("res_deposit"));
		 				mm.setPay_num(rs.getString("pay_num"));
		 				
		 				// BoardBean -> List
		 				BookingList.add(mm);
		 				
	 				}
	 			}//while
	 		
	 			System.out.println("DAO : 글 리스트(페이징처리된)글 리스트를 저장");
	 			System.out.println(BookingList);
	 			System.out.println("DAO : 리스트 사이즈 : " + BookingList.size());
	 			
	 			
	 		} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}finally {
	 			closeDB();
	 		}
	 		return BookingList;
	 	}
	 	
	 	//끝 : 예약3. 리스트 정보 가져오기(페이징처리) - getOwnBookListPage(start)
	 	
		// 사업자 리뷰1. 정보 조회 - getOwnReview(own_id);
	    public ReviewDTO getOwnReview(String own_id) {
	    	ReviewDTO dto = null;
	        try {
	            // 1.2. 디비연결
	            con = getCon();
	            // 3. sql작성(select) &  pstmt 객체
	            sql = "select * from review where own_id=?";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, own_id);
	            // 4. sql 실행
	            rs = pstmt.executeQuery();
	            // 5. 데이터 처리
	            if(rs.next()) {
	                dto = new ReviewDTO();
	                dto.setRvw_num(rs.getInt("rvw_num"));
	                dto.setOwn_id(rs.getString("own_id"));
	                dto.setUs_id(rs.getString("us_id"));
	                dto.setRvw_cont(rs.getString("rvw_cont"));
	                dto.setRvw_star(rs.getInt("rvw_star"));
	                dto.setRvw_re(rs.getString("rvw_re"));

            	}
	            System.out.println(" DAO : 리뷰 정보 조회 완료 ");
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            closeDB();
	        }
	        return dto;
	    }
	    // 사업자 리뷰1. 정보 조회 - getOwnReview(own_id);
	    
	    
	    // 사업자 - 리뷰2. 정보 계산 - getOwnReviewCount(own_id);
	    public int getOwnReviewCount(String own_id) {
	    	
	    	 System.out.println("DAO : getOwnReviewCount() 호출");
	         System.out.println("DAO : 실행목적 : 글의 개수(int) 리턴");
	         int result = 0;

	         try {
	             // 1.2. 디비연결
	             con =  getCon();
	             // 3. SQL 작성 & pstmt 객체

	             sql = "select count(*) from review where own_id = ?";
	             pstmt = con.prepareStatement(sql);
	             pstmt.setString(1, own_id);
	             
	             // 4. SQL 실행
	             rs = pstmt.executeQuery();

	             // 5. 데이터 처리
	             if(rs.next()) {
	            	
	                 // result = rs.getInt(1);  // 인덱스 사용으로 처리속도가 빠름
	            	 result = rs.getInt("count(*)");
	             }
	       
	             System.out.println("DAO : 리뷰글의 개수 조회 완료!");


	         }
	         catch(Exception e) {
	             e.printStackTrace();
	         }
	         finally {
	             closeDB();
	         }
	         return result;
	     }
	    // 끝 : 사업자 - 리뷰2. 정보 계산 - getOwnReviewCount(own_id);
	    
	 // 시작 : 사업자 - 리뷰3. 정보 리스트 정보 가져오기(페이징처리) - getOwnReviewListPage(startRow,pageSize,own_id)
	 	public List<ReviewDTO> getOwnReviewListPage(int startRow, int pageSize, String own_id){
	 	
	 		System.out.println("DAO : getOwnReviewListPage(startRow, pageSize) 호출");
	 		
	 		// 글 리스트 저장하는 객체
	 		List<ReviewDTO> ReviewList = new ArrayList<ReviewDTO>();
	 		
	 		
	 		try {
	 			// 1.2. 디비연결 (커넥션풀)
	 			con = getCon();
	 			// 3. sql 구문 작성 & pstmt객체
	 			// 게시판 글 리스트 원하는 만큼만 조회
	 			// limit 시작위치, 개수 
	 			// : 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져옴
	 			// 			  정렬 re_ref(그룹번호) 내림차순, 
	 			//				   re_seq(답글순서) 오름차순
	 			sql = "select * from review "
	 					+ "where own_id = ?"
	 					+ "order by rvw_num "
	 					+ "limit ?,?";
	 			pstmt = con.prepareStatement(sql);
	 			// ???
	 			pstmt.setString(1,own_id);  //(시작위치-1) startRow
	 			pstmt.setInt(2,startRow-1);  //(시작위치-1) startRow
	 			pstmt.setInt(3,pageSize);  //개수 pageSize 10
	 			// 4. sql 실행
	 			rs = pstmt.executeQuery();
	 			// 5. 데이터처리 (rs -> BoardBean -> List)
	 			while(rs.next()) {
	 				// rs -> BoardBean
	 			
	 					ReviewDTO mm = new ReviewDTO();
		 				
		 				mm.setRvw_num(rs.getInt("rvw_num"));
		 				mm.setOwn_id(rs.getString("own_id"));
		 				mm.setUs_id(rs.getString("us_id"));
		 				mm.setRvw_cont(rs.getString("rvw_cont"));
		 				mm.setRvw_star(rs.getInt("rvw_star"));
		 				mm.setRvw_re(rs.getString("rvw_re"));
		 				
		 				// BoardBean -> List
		 				ReviewList.add(mm);
	 				
	 				
	 			}//while
	 		
	 			System.out.println("DAO : 글 리스트(페이징처리된)글 리스트를 저장");
	 			System.out.println(ReviewList);
	 			System.out.println("DAO : 리스트 사이즈 : " + ReviewList.size());
	 			
	 			
	 		} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}finally {
	 			closeDB();
	 		}
	 		return ReviewList;
	 	}
	 	
	 	//끝 : 리뷰3. 리스트 정보 가져오기(페이징처리) - getOwnReviewListPage(start)
	 	
	 	// 시작 : 사업자 탈퇴 - ownDelete
	 	public int ownDelete(OwnMainDTO dto) {
	 		int result = -1;
	 		
	 		try {
				con = getCon();
				sql = "select own_pw from owner where own_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getOwn_id());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					if(dto.getOwn_pw().equals(rs.getString("own_pw"))) {
						sql = "update owner set own_out=1, own_od=? where own_id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setDate(1, dto.getOwn_od());
						pstmt.setString(2, dto.getOwn_id());
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
	 	// 끝 : 사업자 탈퇴 - ownDelete
	 	
	 	// 시작 : 사업자 정보 가져오기 - getOwnIntoContent
	 	public OwnMainDTO getOwnIntoContent(String own_id) {
	 		OwnMainDTO dto = null;
	 		try {
				con = getCon();
				sql = "select * from owner where own_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, own_id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					dto = new OwnMainDTO();
					dto.setOwn_id(rs.getString("own_id"));
					dto.setOwn_pw(rs.getString("own_pw"));
					dto.setOwn_name(rs.getString("own_name"));
					dto.setOwn_tel(rs.getString("own_tel"));
					dto.setOwn_email(rs.getString("own_email"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
	             closeDB();
	         }
	 		return dto;
	 	}
	 	// 끝 : 사업자 정보 가져오기 - getOwnIntoContent
	 	
}




