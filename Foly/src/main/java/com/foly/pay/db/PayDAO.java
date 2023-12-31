package com.foly.pay.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.foly.own.db.OwnMainDTO;
import com.foly.res.db.BoDTO;
import com.foly.res.db.CartDTO;
import com.foly.user.db.UserDTO;


public class PayDAO {
	int cart_num=0;
	
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
	
	
	// 시작 : 가게 정보 - getOptionRes()
	public OwnMainDTO  getOptionRes(String own_id) {
			OwnMainDTO ownDto = null;
			System.out.println("아이디 있음" +own_id);
		try {
			con = getCon();
			sql = "select * from owner where own_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, own_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ownDto = new OwnMainDTO();
				ownDto.setOwn_id(own_id);
				ownDto.setRes_name(rs.getString("res_name"));
				ownDto.setRes_addr(rs.getString("res_addr"));
				
				System.out.println("값이 있음" + rs.getString("own_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return ownDto;
	}
	// 끝 : 가게 정보 - getOptionRes()
	
	// 시작 : 회원 전화번호 - getOptionUser()
	public UserDTO getOptionUser(String us_id) {
		UserDTO userList = null;
		
		try {
			con = getCon();
			sql = "select * from user where us_id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, us_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				userList = new UserDTO();
				userList.setUs_tel(rs.getString("us_tel"));
				userList.setUs_name(rs.getString("us_name"));
				userList.setUs_email(rs.getString("us_email"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return userList;
	}
	
	// 끝 : 회원 정보 - getOptionUser()
	
	// 시작 : 장바구니 정보 가져오기 - getCartList()
	public ArrayList<CartDTO> getCartList(String us_id) {
		ArrayList<CartDTO> cartList = new ArrayList<>();
		try {
			con = getCon();
			sql = "select * from cart where us_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, us_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setMe_name(rs.getString("me_name"));
				dto.setRes_name(rs.getString("res_name"));
				dto.setMe_amount(rs.getInt("me_amount"));
				dto.setMe_price(rs.getInt("me_price"));
				dto.setCart_price(rs.getInt("cart_price"));
				
				cartList.add(dto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return cartList;
	}
	// 끝 : 장바구니 정보 가져오기 - getCartList()
	
	// 시작 : 장바구니 총합 가져오기 - getCartTotalPrice()
	public CartDTO getCartTotalPrice(String us_id) {
		CartDTO cartTotalPrice = null;
		try {
			con = getCon();
			sql = "select cart_price_total from cart where cart_num = 0 and us_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, us_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cartTotalPrice = new CartDTO();
				cartTotalPrice.setCart_price_total(rs.getInt("cart_price_total"));
				}
//			if(rs.next()) {
//				cart_num = rs.getInt("max(cart_num)");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
//		try {
//			sql ="select cart_price_total from cart where cart_num=? and us_id=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, cart_num);
//			pstmt.setString(2, us_id);
//			rs = pstmt.executeQuery();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return cartTotalPrice;
	}
	// 끝 : 장바구니 총합 가져오기 - getCartTotalPrice()
	
	// 시작 : pay 정보저장 - payInsert(pay_num,card_num,pay_card,res_name,pay_total,us_name,pay_date,us_tel,pay_memo,pay_id)
	public void payInsert(PayDTO dto) {
		System.out.println(dto+"@@@@@@@@@@@@@@@@@@@@@@@@");
		int pk_num	= 0;
		try {
			con = getCon();
			sql ="select max(pk_num) from pay";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pk_num = rs.getInt(1) + 1;
				System.out.println(" @@@@@@@ pk_num 확인 :" + pk_num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			con = getCon();
			sql = "insert into pay(pay_num,card_num,pay_card,res_name,pay_total,us_name,us_tel,pay_memo,pay_id,pay_date,us_id, own_id, pk_num, pay_state) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,1)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getPay_num());
			pstmt.setString(2, dto.getCard_num());
			pstmt.setString(3, dto.getPay_card());
			pstmt.setString(4, dto.getRes_name());
			pstmt.setInt(5, dto.getPay_total());
			pstmt.setString(6, dto.getUs_name());
			pstmt.setString(7, dto.getUs_tel());
			pstmt.setString(8, dto.getPay_memo());
			pstmt.setString(9, dto.getPay_id());
			pstmt.setDate(10, dto.getPay_date());
			pstmt.setString(11, dto.getUs_id());
			pstmt.setString(12, dto.getOwn_id());
			pstmt.setInt(13, pk_num);
			pstmt.executeUpdate();
			System.out.println(" DAO : 결제테이블 정보 insert");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	// 끝 : pay 정보저장 - payInsert(pay_num,card_num,pay_card,res_name,pay_total,us_name,pay_date,us_tel,pay_memo,pay_id)
	
	// 시작 : pay정보 출력 - payInfo(us_id)
	public PayDTO payInfo(String us_id, String pay_num) {
		PayDTO dto = null;
		
		try {
			con = getCon();
			sql = "select * from pay where us_id=? and pay_num =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, us_id);
			pstmt.setString(2, pay_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new PayDTO();
				dto.setUs_name(rs.getString("us_name"));
				dto.setPay_num(rs.getString("pay_num"));
				dto.setPay_date(rs.getDate("pay_date"));
				dto.setPay_total(rs.getInt("pay_total"));
				dto.setRes_name(rs.getString("res_name"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return dto;
	}
	// 끝 : pay정보 출력 - payInfo(pay_num)
	
	// 시작 : cart정보 삭제 - cartDelete
	public CartDTO cartDelete(String us_id) {
		try {
			con = getCon();
			sql = "delete from cart where us_id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, us_id);
			pstmt.executeUpdate();
			System.out.println(" DAO : 카트정보 삭제");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return null;
	}
	// 끝 : cart정보 삭제 - cartDelete
	
	
	// 시작 : pay정보 출력 - payBoInfo(us_id)
		public PayDTO payBoInfo(String us_id, String pay_num) {
			PayDTO dto = null;
			
			try {
				con = getCon();
				sql = "select * from pay where us_id=? and pay_num =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, us_id);
				pstmt.setString(2, pay_num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					dto = new PayDTO();
					dto.setUs_name(rs.getString("us_name"));
					dto.setPay_num(rs.getString("pay_num"));
					dto.setPay_date(rs.getDate("pay_date"));
					dto.setPay_total(rs.getInt("pay_total"));
					dto.setRes_name(rs.getString("res_name"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return dto;
		}
		// 끝 : pay정보 출력 - payBoInfo
		
		// 시작 : booking정보 출력 - BoInfo(us_id)
		public BoDTO boInfo(String us_id, String pay_num) {
			BoDTO boDto = null;
			
			try {
				con = getCon();
				sql = "select * from booking where us_id=? and pay_num =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, us_id);
				pstmt.setString(2, pay_num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					boDto = new BoDTO();
					boDto.setBo_date(rs.getString("bo_date"));
					boDto.setBo_dateH(rs.getString("bo_dateH"));
					boDto.setBo_per(rs.getString("bo_per"));
					boDto.setRes_deposit(rs.getInt("res_deposit"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return boDto;
		}
		// 끝 : booking정보 출력 - BoInfo
	
		// 시작 : 예약결제 정보저장 - payBoInsert()
		public void payBoInsert(PayDTO dto) {
			System.out.println(dto+"@@@@@@@@@@@@@@@@@@@@@@@@");
			int bo_num	= 0;
			try {
				con = getCon();
				sql ="select max(bo_num) from pay";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					bo_num = rs.getInt(1) + 1;
					System.out.println(" @@@@@@@ bo_num 확인 :" + bo_num);
				}
			
				sql = "insert into pay(pay_num,card_num,pay_card,res_name,pay_total,us_name,us_tel,pay_memo,pay_id,pay_date,us_id, own_id, bo_num,pay_state) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,1)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getPay_num());
				pstmt.setString(2, dto.getCard_num());
				pstmt.setString(3, dto.getPay_card());
				pstmt.setString(4, dto.getRes_name());
				pstmt.setInt(5, dto.getPay_total());
				pstmt.setString(6, dto.getUs_name());
				pstmt.setString(7, dto.getUs_tel());
				pstmt.setString(8, dto.getPay_memo());
				pstmt.setString(9, dto.getPay_id());
				pstmt.setDate(10, dto.getPay_date());
				pstmt.setString(11, dto.getUs_id());
				pstmt.setString(12, dto.getOwn_id());
				pstmt.setInt(13, bo_num);
				pstmt.executeUpdate();
				System.out.println(" DAO : 결제테이블 정보 insert");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
		}
		// 끝 : 예약결제 정보저장 - payBoInsert()
	
		// 시작 : 예약내역 정보저장(booking) - boInsert 
		public void boInsert(BoDTO boDto) {
			System.out.println(boDto+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			int bo_num =0;
			
			try {
				con = getCon();
				sql ="select max(bo_num) from booking";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					bo_num = rs.getInt(1) + 1;
					System.out.println(" @@@@@@@ bo_num 확인 :" + bo_num);
				}
				
				sql = "insert into booking(us_id,own_id,bo_date,bo_dateH,bo_per,bo_state,res_deposit,pay_num,bo_value,bo_num) "
						+ "values(?,?,?,?,?,1,?,?,0,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, boDto.getUs_id());
				pstmt.setString(2, boDto.getOwn_id());
				pstmt.setString(3, boDto.getBo_date());
				pstmt.setString(4, boDto.getBo_dateH());
				pstmt.setString(5, boDto.getBo_per());
				pstmt.setInt(6, boDto.getRes_deposit());
				pstmt.setString(7, boDto.getPay_num());
				pstmt.setInt(8, bo_num);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
		}
		// 끝 : 예약내역 정보저장(booking) - boInsert
		
		// 시작 : 포장내역 정보저장(booking) - boPayInsert
		public void boPayInsert(BoDTO boDto,String us_id) {
			System.out.println(boDto+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			int pk_num =0;
			int cart_amount =0;
			
			try {
				con = getCon();
				sql ="select max(pk_num) from booking";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					pk_num = rs.getInt(1) + 1;
					System.out.println(" @@@@@@@ pk_num 확인 :" + pk_num);
				}
				
				sql ="select cart_amount from cart where us_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, us_id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					cart_amount = rs.getInt("cart_amount");
					System.out.println(" @@@@@@@ Cart_amount 확인 :" + cart_amount);
				}

				if(cart_amount>1) {
					
					sql = "insert into booking(us_id,own_id,bo_date,bo_state,bo_price,pay_num,bo_value,pk_num,bo_menu,bo_count) "
							+ "values(?,?,date(now()),1,?,?,1,?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, boDto.getUs_id());
					pstmt.setString(2, boDto.getOwn_id());
					pstmt.setInt(3, boDto.getBo_price());
					pstmt.setString(4, boDto.getPay_num());
					pstmt.setInt(5, pk_num);
					pstmt.setString(6, boDto.getBo_menu()+" 외");
					pstmt.setInt(7, cart_amount);
					pstmt.executeUpdate();
				}else {
					sql = "insert into booking(us_id,own_id,bo_date,bo_state,bo_price,pay_num,bo_value,pk_num,bo_menu,bo_count) "
							+ "values(?,?,date(now()),1,?,?,1,?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, boDto.getUs_id());
					pstmt.setString(2, boDto.getOwn_id());
					pstmt.setInt(3, boDto.getBo_price());
					pstmt.setString(4, boDto.getPay_num());
					pstmt.setInt(5, pk_num);
					pstmt.setString(6, boDto.getBo_menu());
					pstmt.setInt(7, cart_amount);
					pstmt.executeUpdate();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
		}
		// 끝 : 포장내역 정보저장(booking) - boPayInsert
	
	
}//PayDAO
