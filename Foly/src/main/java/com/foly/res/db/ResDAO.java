package com.foly.res.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.foly.own.db.OwnMainDTO;
/**
 * MemberDAO : 데이터를 처리하는 객체 (DB처리) [박스를 갖다주면 처리해주는애들] (Data Access Object) ->
 * sql구문 들어가는 것들은 DAO로 처리
 * 
 */
import com.foly.user.db.UserDTO;
public class ResDAO {

	// 공통변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 디비연결 메서드 - getCon()
	private Connection getCon() throws Exception {
		// Context 객체정보 생성
		Context initCTX = new InitialContext(); // 업캐스팅
		// 필요한정보(DB연결정보) 가져오기
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/foly");
		// 디비 연결
		con = ds.getConnection();
		System.out.println(" DAO : 디비연결 성공! " + con);

		return con;
	}
	// 디비연결 메서드 - getCon()

	// 디비 자원 해제 메서드 - closeDB()
	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

			System.out.println(" DAO : 자원해제 완료! ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 디비 자원 해제 메서드 - closeDB()
	
	// 시작 : 식당 검색
	public ArrayList<SearchDTO> serchRes(String str) {
		ArrayList<SearchDTO> serch = new ArrayList<>();
		
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql작성 & pstmtm 객체
			sql = "select distinct o.* from owner o join restaurant r on o.own_id = r.own_id where o.res_name like ? or o.res_type like ? or r.me_name like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+str+"%");
			pstmt.setString(2, "%"+str+"%");
			pstmt.setString(3, "%"+str+"%");

			// 4. sql실행
			rs = pstmt.executeQuery();
			// 5. 데이터처리
		
			
		while(rs.next()) {
				SearchDTO dto = new SearchDTO();
				
				
				dto.setOwn_email(rs.getString("own_email"));
				dto.setOwn_id(rs.getString("own_id"));
				dto.setOwn_name(rs.getString("own_name"));
				dto.setOwn_od(rs.getDate("own_od"));
				dto.setOwn_out(rs.getInt("own_out"));
				dto.setOwn_pw(rs.getString("own_pw"));
				dto.setOwn_regdate(rs.getDate("own_regdate"));
				dto.setOwn_tel(rs.getString("own_tel"));
				dto.setRes_deposit(rs.getString("res_deposit"));
				dto.setRes_mng(rs.getString("res_mng"));
				dto.setRes_type(rs.getString("res_type"));
				dto.setRes_name(rs.getString("res_name"));
				dto.setRes_addr(rs.getString("res_addr"));
				dto.setRes_tel(rs.getString("res_tel"));
				dto.setRes_etH(rs.getString("res_etH"));
				dto.setRes_etM(rs.getString("res_etM"));
				dto.setRes_stH(rs.getString("res_stH"));
				dto.setRes_stM(rs.getString("res_stM"));
				dto.setRes_img(rs.getString("res_img"));
				
				serch.add(dto);
				
				
			}
			System.out.println("DAO : 검색 - 식당정보 조회 완료!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return serch;
	}
	
	
	// 끝 : 식당 검색 - 
	
	// 시작 : 카테고리에 맞는 정보 가져오기
		public ArrayList<SearchDTO> getCateRes(String res_type) {
			ArrayList<SearchDTO> ResCateSearch = new ArrayList<>();
			
			
				
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql작성 & pstmtm 객체
				sql = "select * from owner where res_type=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, res_type);	
				// 4. sql실행
				rs = pstmt.executeQuery();
				// 5. 데이터처리
				while(rs.next()) {
					SearchDTO dto = new SearchDTO();
					
					dto.setOwn_email(rs.getString("own_email"));
					dto.setOwn_id(rs.getString("own_id"));
					dto.setOwn_name(rs.getString("own_name"));
					dto.setOwn_od(rs.getDate("own_od"));
					dto.setOwn_out(rs.getInt("own_out"));
					dto.setOwn_pw(rs.getString("own_pw"));
					dto.setOwn_regdate(rs.getDate("own_regdate"));
					dto.setOwn_tel(rs.getString("own_tel"));
					dto.setRes_deposit(rs.getString("res_deposit"));
					dto.setRes_mng(rs.getString("res_mng"));
					dto.setRes_type(rs.getString("res_type"));
					dto.setRes_name(rs.getString("res_name"));
					dto.setRes_addr(rs.getString("res_addr"));
					dto.setRes_tel(rs.getString("res_tel"));
					dto.setRes_etH(rs.getString("res_etH"));
					dto.setRes_etM(rs.getString("res_etM"));
					dto.setRes_stH(rs.getString("res_stH"));
					dto.setRes_stM(rs.getString("res_stM"));
					dto.setRes_img(rs.getString("res_img"));
					
					ResCateSearch.add(dto);
					
				}
				System.out.println("DAO : 카테고리에 맞는 식당정보 조회 완료!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return ResCateSearch;
		}
		// 끝 : 카테고리에 맞는 정보 가져오기
		
		// 시작 - resDetial - 식당메뉴 목록 불러오기
		public ArrayList<ResDTO> getMenuInfo(String own_id) {
			ArrayList<ResDTO> menuInfo = new ArrayList<>();

				
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql작성 & pstmtm 객체
				sql = "select * from restaurant where own_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, own_id);

				// 4. sql실행
				rs = pstmt.executeQuery();
				// 5. 데이터처리
				while(rs.next()) {
					
					ResDTO dto = new ResDTO();

					dto.setOwn_id(rs.getString("own_id"));
					dto.setMe_num(rs.getInt("me_num"));
					dto.setMe_img(rs.getString("me_img"));
					dto.setMe_name(rs.getString("me_name"));
					dto.setMe_price(rs.getInt("me_price"));
					dto.setMe_exp(rs.getString("me_exp"));
					
					
					menuInfo.add(dto);
					
				}
				System.out.println("DAO : 식당 메뉴정보 조회 완료!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return menuInfo;
		}
		
		// (선빈) 끝 - 식당메뉴 목록 불러오기
		
		
		// (선빈) 시작 - resDetail - 식당상세 정보 불러오기 
				public OwnMainDTO getResInfo(String own_id) {
					OwnMainDTO ResInfo = new OwnMainDTO();

						
					try {
						// 1.2. 디비연결
						con = getCon();
						// 3. sql작성 & pstmtm 객체
						sql = "select * from owner where own_id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, own_id);

						// 4. sql실행
						rs = pstmt.executeQuery();
						// 5. 데이터처리
						while(rs.next()) {

							ResInfo.setOwn_id(rs.getString("own_id"));
							ResInfo.setOwn_name(rs.getString("own_name"));
							
							ResInfo.setRes_name(rs.getString("res_name"));
							ResInfo.setRes_addr(rs.getString("res_addr"));
							ResInfo.setRes_tel(rs.getString("res_tel"));
							
							ResInfo.setRes_stH(rs.getString("res_stH"));
							ResInfo.setRes_stM(rs.getString("res_stM"));
							ResInfo.setRes_etH(rs.getString("res_etH"));
							ResInfo.setRes_etM(rs.getString("res_etM"));

							ResInfo.setRes_mng(rs.getString("res_mng"));
							ResInfo.setRes_deposit(rs.getString("res_deposit"));
							
							ResInfo.setRes_la(rs.getString("res_la"));
							ResInfo.setRes_lo(rs.getString("res_lo"));
		
							
						}
						System.out.println("DAO : 식당 메뉴정보 조회 완료!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						closeDB();
					}
					
					return ResInfo;
				}
				
				// (선빈) 끝 - 식당상세 정보 불러오기
				
		

	// 시작 : 식당 상세페이지에서 메뉴정보 가져오는 메서드 - getMenuDetail()
	public ResDTO getMenuDetail(String own_id, int me_num) {
		ResDTO menuDetail = new ResDTO();

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성(select) & pstmt 객체
			sql = "select * from restaurant where own_id=? && me_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, own_id);
			pstmt.setInt(2, me_num);

			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) { // 반복문.
				// rs -(한명의정보)-> DTO -> ArrayList
				System.out.println("메뉴정보 불러오기 성공");
				menuDetail.setMe_num(rs.getInt("me_num"));
				menuDetail.setMe_img(rs.getString("me_img"));
				menuDetail.setMe_name(rs.getString("me_name"));
				menuDetail.setMe_price(rs.getInt("me_price"));
				menuDetail.setMe_exp(rs.getString("me_exp"));
				

				
			} // if

			System.out.println(menuDetail);
			System.out.println(" DAO : 식당메뉴 상세정보 저장완료! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return menuDetail;
	}
	// 끝 : 식당 상세페이지에서 메뉴정보 가져오는 메서드 - getMenuDetail()

	// 시작 : 장바구니에 메뉴를 추가하는 메서드 - addMenu()
	public int addMenu(UserBookingDTO dto, String us_id, String own_id) {

		System.out.println("addMenu() 실행");

		String own_id_check = "-1";
		int result = -1;
		
		int me_num = dto.getMe_num();
		int me_num_check = 0;
		int me_num_result = 0;
		int me_amount = 0;
		
		int cart_num = 0;
		int cart_amount = 0;
		int cart_price = 0;
		int cart_price_total = 0;

		try {
			// 1.2 디비연결
			con = getCon();

			// 3.sql 작성 & pstmt객체 - max(cart_num)
			sql = "select max(cart_num) from cart where us_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, us_id);
			//
			rs = pstmt.executeQuery();

			// 장바구니에 물건 확인후 cart_num 결정, 있으면 max(cart_num)+1 , 없으면 cart_num = 1
			if (rs.next()) {
				cart_num = rs.getInt(1) + 1;
				System.out.println(" @@@@@@@ cart_num 확인 :" + cart_num);
			}
			
			// result = 1;  장바구니에 물건이 없으면 cart_num == 1
			if(cart_num == 1) { // 처음 담는것이므로 바로 insert
				
				System.out.println("cart_num == 0 데이터 넣기");
			
				
				// cart_num 이 0번인 컬럼에 데이터 넣기
				// 3.sql 작성 & pstmt객체
				sql = "insert into cart(res_name,own_id,us_id,cart_num,cart_amount,cart_price_total)"
						+ "values(?,?,?,?,?,?)";

				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, dto.getRes_name());
				pstmt.setString(2, dto.getOwn_id());
				pstmt.setString(3, us_id);
				
				pstmt.setInt(4, 0);
				
				cart_amount = cart_amount + dto.getMe_amount();
				pstmt.setInt(5, cart_amount);

				cart_price_total = dto.getMe_price() * dto.getMe_amount();
				pstmt.setInt(6, cart_price_total);
				
				
				// 4. sql 실행
				pstmt.executeUpdate();
				
				
			///////////////////////////////////
			System.out.println("첫 물건");
			// 3.sql 작성 & pstmt객체
			sql = "insert into cart(me_num,me_name,me_price,me_img,me_amount,res_name,own_id,us_id,cart_num,cart_price)"
			      + "values(?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getMe_num());
			pstmt.setString(2, dto.getMe_name());
			pstmt.setInt(3, dto.getMe_price());
			pstmt.setString(4, dto.getMe_img());
			pstmt.setInt(5, dto.getMe_amount());
			pstmt.setString(6, dto.getRes_name());
			pstmt.setString(7, dto.getOwn_id());
			pstmt.setString(8, us_id);
			pstmt.setInt(9, cart_num);
			cart_price = dto.getMe_price() * dto.getMe_amount();
			pstmt.setInt(10, cart_price);
			
			// 4. sql 실행
			pstmt.executeUpdate();
			System.out.println("DAO : insert - 처음 & 장바구니 담기 성공! ");
			
			result = 1;
			
			
			}
			
			// if 끝 : 장바구니에 물건이 없으면 cart_num == 1
			// 장바구니에 물건이 있으면 cart_num > 1
			else if(cart_num > 1) { // 처음 담는것이 아니라면 own_id로 가게 비교 및 수량과 가격 +
				
				// 3.sql 작성 & pstmt객체 - own_id,cart_amount,cart_price,cart_price_total
				
				sql = "select own_id,cart_amount,cart_price_total from cart where us_id=? and cart_num=0";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, us_id);
				// 실행
				rs = pstmt.executeQuery();
	
				// cart_num==0인 줄 정보 확인 
				if (rs.next()) { // cart_num == 0
					System.out.println(" @@@@@@@ 장바구니에 물건이 있음, onw_id, 총 수량과 총가격을 들고옴@@@@");
					
					own_id_check = rs.getString("own_id");
					cart_amount = rs.getInt("cart_amount");
					cart_price_total = rs.getInt("cart_price_total");
	
				}// 마지막 줄 정보 확인
				
				
				// result = 1; 같은 가게면  
				if(own_id.equals(own_id_check)) {
					
					System.out.println("같은 가게임을 확인 완료");
					//////////////////////////////
					// 같은 메뉴가 있는지 확인
					sql = "select me_num,me_amount from cart where us_id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, us_id);
					// 실행
					rs = pstmt.executeQuery();
		
					// 
					while(rs.next()) {
						System.out.println(" @@@@@@@ 같은 메뉴가 있는지 확인 (While) @@@@");
						
						me_num_check = rs.getInt("me_num");
						System.out.println(me_num_check + "번의 메뉴 확인중");
						if(me_num == me_num_check) {
							
							me_amount = rs.getInt("me_amount");
							me_num_result = 1;
							System.out.println("같은 메뉴가 있음"+me_num_check+"번 메뉴, 현재 수량 : " + me_amount );
							
						}
						else {
							System.out.println("같은 메뉴가 없음, 메뉴번호 : " + me_num );
						}
		
					}// 같은 메뉴가 있는지 확인
					//////////////////////////////////////
					
					// 같은 메뉴가 있을 시
					if(me_num_result == 1) {
						
						System.out.println("같은 메뉴가 있을시");
						
						// 카트에 담긴 해당 메뉴 수량 변경
						// 3.sql 작성 & pstmt객체
						sql = "update cart set me_amount=?,cart_price=? where us_id =? and me_num =?";

						pstmt = con.prepareStatement(sql);

						me_amount = me_amount + dto.getMe_amount();
						pstmt.setInt(1, me_amount);
						
						cart_price = dto.getMe_price() * me_amount;
						pstmt.setInt(2, cart_price);
						
						pstmt.setString(3, us_id);
						pstmt.setInt(4, dto.getMe_num());
						

						// 4. sql 실행
						pstmt.executeUpdate();
						
						// cart_num ==0 의 amount와 price_total 변경
						// 3.sql 작성 & pstmt객체
						sql = "update cart set cart_amount=?, cart_price_total=? where us_id =? and cart_num =0";

						pstmt = con.prepareStatement(sql);
						
						cart_amount = cart_amount + dto.getMe_amount();
						pstmt.setInt(1, cart_amount);
						
						cart_price_total = cart_price_total + (dto.getMe_amount() * dto.getMe_price()) ;
						pstmt.setInt(2, cart_price_total);
						
						pstmt.setString(3, us_id);
						
						// 4. sql 실행
						pstmt.executeUpdate();

					
						System.out.println("DAO : update - 가게 같음 & 메뉴 같음 & 장바구니 담기 성공! ");
						
						result = 1;
							
					}
					else { // else 시작 : 가게는 같지만 같은 메뉴가 없음
						
						
						// 3.sql 작성 & pstmt객체
						sql = "insert into cart(me_num,me_name,me_price,me_img,me_amount,res_name,own_id,us_id,cart_num,cart_price)"
								+ "values(?,?,?,?,?,?,?,?,?,?)";

						pstmt = con.prepareStatement(sql);

						pstmt.setInt(1, dto.getMe_num());
						pstmt.setString(2, dto.getMe_name());
						pstmt.setInt(3, dto.getMe_price());
						pstmt.setString(4, dto.getMe_img());
						pstmt.setInt(5, dto.getMe_amount());
						pstmt.setString(6, dto.getRes_name());
						pstmt.setString(7, dto.getOwn_id());
						pstmt.setString(8, us_id);
						
						pstmt.setInt(9, cart_num);
						
						cart_price = dto.getMe_price() * dto.getMe_amount();
						pstmt.setInt(10, cart_price);

						// 4. sql 실행
						pstmt.executeUpdate();
						
						
						
						// cart_num ==0 의 amount와 price_total 변경
						// 3.sql 작성 & pstmt객체
						sql = "update cart set cart_amount=?, cart_price_total=? where us_id =? and cart_num =0";

						pstmt = con.prepareStatement(sql);
						
						cart_amount = cart_amount + dto.getMe_amount();
						pstmt.setInt(1, cart_amount);
						
						cart_price_total = cart_price_total + cart_price;
						pstmt.setInt(2, cart_price_total);
						
						pstmt.setString(3, us_id);

						// 4. sql 실행
						pstmt.executeUpdate();
						
						System.out.println("DAO : insert - 가게 같음 & 같은 메뉴 없음 & 장바구니 담기 성공! ");
						
						result = 1;
					}// else 끝 : 가게는 같지만 같은 메뉴가 없음
					
				}// if끝 : 같은 가게면  result = 1;  
				else { // else 시작 : 다른 가게면
					 // result = -1; 
				} // else 끝 : 다른 가게면 result = -1; 
				
			}// if 끝 : 장바구니에 물건이 있으면 cart_num >= 1
			else {
				System.out.println(" ??? ");
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;

	}
	// 끝 : 장바구니에 메뉴를 추가하는 메서드 - addMenu()
	
	


    // (선빈)유저 - 장바구니2. 정보 계산 - getUserCartCount(us_id);
    public int getUserCartCount(String us_id) {
    	
    	 System.out.println("DAO : getUserCartCount() 호출");
         System.out.println("DAO : 실행목적 : 글의 개수(int) 리턴");
         int result = 0;

         try {
             // 1.2. 디비연결
             con =  getCon();
             // 3. SQL 작성 & pstmt 객체

             sql = "select max(cart_num) from cart where us_id = ?";
             pstmt = con.prepareStatement(sql);
             pstmt.setString(1, us_id);
             System.out.print(us_id);
             // 4. SQL 실행
             rs = pstmt.executeQuery();

             // 5. 데이터 처리
             if(rs.next()) {

            	 result = rs.getInt("max(cart_num)");
	             System.out.print(result);
            	 
             }
             System.out.println("DAO : 글의 개수 조회 완료!" + result);


         }
         catch(Exception e) {
             e.printStackTrace();
         }
         finally {
             closeDB();
         }
         return result;
     }
    // (선빈)끝 : 유저 - 장바구니2. 정보 계산 - getOwnBookCount(own_id);
    
 // (선빈)시작 : 유저 - 장바구니3. 정보 리스트 정보 가져오기(페이징처리) - getOwnBookListPage(startRow,pageSize,own_id)
 	public List<UserBookingDTO> getUserCartListPage(int startRow, int pageSize, String us_id){
 	
 		System.out.println("DAO : getUserCartListPage(startRow, pageSize, us_id) 호출");
 		
 		// 글 리스트 저장하는 객체
 		List<UserBookingDTO> CartList = new ArrayList<UserBookingDTO>();

 		
 		try {
 			// 1.2. 디비연결 (커넥션풀)
 			con = getCon();
 			// 3. sql 구문 작성 & pstmt객체
 			// 게시판 글 리스트 원하는 만큼만 조회
 			// limit 시작위치, 개수 
 			// : 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져옴
 			// 			  정렬 re_ref(그룹번호) 내림차순, 
 			//				   re_seq(답글순서) 오름차순
 			
 			sql = "select c.*, o.* from cart c join owner o on c.own_id = o.own_id "
 					+ "where c.us_id = ?"
 					+ "order by c.cart_num "
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

 		 		UserBookingDTO mm = new UserBookingDTO();

	 				
	 				mm.setOwn_id(rs.getString("c.own_id"));
	 				mm.setUs_id(rs.getString("c.us_id"));
	 				
	 				System.out.println("@@@@"+rs.getInt("c.cart_num"));
	 				mm.setCart_num(rs.getInt("c.cart_num"));
	 				mm.setRes_name(rs.getString("c.res_name"));
	 				mm.setMe_num(rs.getInt("c.me_num"));
	 				mm.setMe_img(rs.getString("c.me_img"));
	 				mm.setMe_price(rs.getInt("c.me_price"));
	 				mm.setMe_name(rs.getString("c.me_name"));
	 				mm.setMe_price(rs.getInt("c.me_price"));
	 				mm.setMe_amount(rs.getInt("c.me_amount"));
	 				mm.setCart_price(rs.getInt("c.cart_price"));
	 				
 	 				mm.setCart_amount(rs.getInt("c.cart_amount"));
 	 				mm.setCart_price_total(rs.getInt("c.cart_price_total"));

 			
 	 				CartList.add(mm);
 				
 				
 			}//while
 			
 			System.out.println("DAO : 장바구니 리스트(페이징처리된)글 리스트를 저장");
 			System.out.println(CartList);
 			System.out.println("DAO : 리스트 사이즈 : " + CartList.size());
 			
 			
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}finally {
 			closeDB();
 		}
 		return CartList;
 	}
 	
 	//(선빈) 끝 : 장바구니3. 리스트 정보 가져오기(페이징처리) - getOwnBookListPage(start)


 // 시작 : 유저 - 장바구니4. 모두 삭제 - cartDelete(us_id)
   	public void cartDelete(String us_id){
   	
   		System.out.println("DAO : cartDelete(us_id) 호출");
   		
   		
   		try {
   			// 1.2. 디비연결 (커넥션풀)
   			con = getCon();
   			// 3. sql 구문 작성 & pstmt객체
   			// 게시판 글 리스트 원하는 만큼만 조회
   			// limit 시작위치, 개수 
   			// : 위치(인덱스)에서 개수만큼 데이터를 짤라서 가져옴
   			// 			  정렬 re_ref(그룹번호) 내림차순, 
   			//				   re_seq(답글순서) 오름차순
   			
   			sql = "Delete from cart where us_id=?";
   			pstmt = con.prepareStatement(sql);
   			// ???
   			pstmt.setString(1,us_id); 
   			
   			// 4. sql 실행
   			pstmt.executeUpdate();
  				
   		
   			System.out.println("DAO : 장바구니 모두 삭제 완료");
   			
   			
   		} catch (Exception e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}finally {
   			closeDB();
   		}
   	}
	
	// 식당 예약 페이지로 이동
	public UserBookingDTO getBookingPage(String own_id) {

		System.out.println("getBookingPage 실행");
		UserBookingDTO dto = new UserBookingDTO();

		try {
			// 1.2 디비연결
			con = getCon();

			// 3.sql 작성 & pstmt객체
			sql = "select * from owner where own_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, own_id);

			//
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setOwn_id(rs.getString("own_id"));
				dto.setRes_name(rs.getString("res_name"));
				dto.setRes_tel(rs.getString("res_tel"));
				dto.setRes_addr(rs.getString("res_addr"));
				dto.setRes_deposit(rs.getString("res_deposit"));
				dto.setRes_stH(rs.getString("res_stH"));
				dto.setRes_stM(rs.getString("res_stM"));
				dto.setRes_etH(rs.getString("res_etH"));
				dto.setRes_etM(rs.getString("res_etM"));
				dto.setRes_img(rs.getString("res_img"));

			}

			System.out.println("DAO : 식당정보 가져오기 성공! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return dto;
	}
	
	// 식당예약 user 정보
	public UserDTO bookingUser(String us_id) {
		UserDTO usDto = null;
		try {
			con = getCon();
			sql = "select * from user where us_id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, us_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				usDto = new UserDTO();
				usDto.setUs_name(rs.getString("us_name"));
				usDto.setUs_tel(rs.getString("us_tel"));
				usDto.setUs_email(rs.getString("us_email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return usDto;
	}
	

	
}// DAO
