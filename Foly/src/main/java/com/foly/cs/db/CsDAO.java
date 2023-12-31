package com.foly.cs.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.foly.own.db.OwnMainDTO;
import com.foly.user.db.UserDTO;

public class CsDAO {

	// 공통변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String sql = "";

	// 디비연결 메서드 getCon();
	private Connection getCon() throws Exception {
		// Context 객체 정보 생성
		Context initCTX = new InitialContext();
		// 필요한정보(DB연결정보) 가져오기
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/foly");

		// 디비연결
		con = ds.getConnection();
		System.out.println(" DAO : 디비연결 성공! " + con);

		return con;
	}
	// 디비연결 메서드 getCon();

	// 시작 - 디비 자원해제 메서드 closeDB();
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
		}finally {
			
		}

	}

	// 끝 - 디비 자원해제 메서드 closeDB();

	// 유저인원 계산 - getUserCount(us_id);
	public int getUserCount(String us_id) {

		System.out.println("DAO : getUserCount() 호출");
		System.out.println("DAO : 실행목적 : 글의 개수(int) 리턴");
		int result = 0;

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. SQL 작성 & pstmt 객체

			sql = "select count(*) from user where us_id != ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, us_id);

			// 4. SQL 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				// result = rs.getInt(1); // 인덱스 사용으로 처리속도가 빠름
				result = rs.getInt("count(*)");
			}
			System.out.println("DAO : 글의 개수 조회 완료!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// 유저인원 계산 - getMenuCount(own_id);

	// 유저정보가져오기(페이징처리)
	public ArrayList<UserDTO> userList(int startRow, int pageSize, String us_id) {

		ArrayList<UserDTO> userList = new ArrayList<>();

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql작성 & pstmtm 객체
			sql = "select * from user where us_id!=? " + "order by us_num " + "limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, us_id);
			pstmt.setInt(2, startRow - 1); // (시작위치-1) startRow
			pstmt.setInt(3, pageSize); // 개수 pageSize 10
			// 4. sql실행
			rs = pstmt.executeQuery();
			// 5. 데이터처리
			while (rs.next()) {
				UserDTO dto = new UserDTO();

				dto.setRvw_num(rs.getInt("rvw_num"));
				dto.setUs_num(rs.getInt("us_num"));
				dto.setUs_order(rs.getInt("us_order"));
				dto.setUs_addr(rs.getString("us_addr"));
				dto.setUs_birD(rs.getString("us_birD"));
				dto.setUs_birM(rs.getString("us_birM"));
				dto.setUs_birY(rs.getString("us_birY"));
				dto.setUs_email(rs.getString("us_email"));
				dto.setUs_id(rs.getString("us_id"));
				dto.setUs_name(rs.getString("us_name"));
				dto.setUs_nick(rs.getString("us_nick"));
				dto.setUs_od(rs.getDate("us_od"));
				dto.setUs_pw(rs.getString("us_pw"));
				dto.setUs_tel(rs.getString("us_tel"));
				dto.setUs_regdate(rs.getDate("us_regdate"));

				userList.add(dto);

			}
			System.out.println("DAO : 식당정보 조회 완료!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return userList;
	}
	// 유저정보가져오기(페이징처리)

	// 사업자인원 계산 - getOwnrCount(us_id);
	public int getOwnCount() {

		System.out.println("DAO : getOwnCount() 호출");
		System.out.println("DAO : 실행목적 : 글의 개수(int) 리턴");
		int result = 0;

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. SQL 작성 & pstmt 객체

			sql = "select count(*) from owner";
			pstmt = con.prepareStatement(sql);

			// 4. SQL 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				// result = rs.getInt(1); // 인덱스 사용으로 처리속도가 빠름
				result = rs.getInt("count(*)");
			}
			System.out.println("DAO : 글의 개수 조회 완료!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// 사업자인원 계산 - getOwnCount(own_id);

	// 사업자정보 가져오기 (페이징처리)
	public ArrayList<OwnMainDTO> ownList(int startRow, int pageSize) {

		ArrayList<OwnMainDTO> ownList = new ArrayList<>();

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql작성 & pstmtm 객체
			sql = "select * from owner order by own_id*1 " + "limit ?,?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, startRow - 1); // (시작위치-1) startRow
			pstmt.setInt(2, pageSize); // 개수 pageSize 10

			// 4. sql실행
			rs = pstmt.executeQuery();
			// 5. 데이터처리
			while (rs.next()) {
				OwnMainDTO dto = new OwnMainDTO();

				dto.setOwn_id(rs.getString("own_id"));
				dto.setOwn_email(rs.getString("own_email"));
				dto.setOwn_name(rs.getString("own_name"));
				dto.setOwn_tel(rs.getString("own_tel"));
				dto.setOwn_regdate(rs.getDate("own_regdate"));
				dto.setRes_name(rs.getString("res_name"));
				dto.setRes_addr(rs.getString("res_addr"));

				ownList.add(dto);

			}
			System.out.println("DAO : 식당정보 조회 완료!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return ownList;
	}
	// 사업자정보 가져오기 (페이징처리)

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 게시판 글 수 계산
	public int getListCount() {

		System.out.println("DAO : getListCount() 호출");
		System.out.println("DAO : 실행목적 : 글의 개수(int) 리턴");
		int result = 0;

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. SQL 작성 & pstmt 객체

			sql = "select count(noti_num) from cs";
			pstmt = con.prepareStatement(sql);

			// 4. SQL 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				// result = rs.getInt(1); // 인덱스 사용으로 처리속도가 빠름
				result = rs.getInt("count(noti_num)");
			}
			System.out.println("DAO : 글의 개수 조회 완료!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}

	// 글 리스트조회 - notiList()
	public ArrayList<CsDTO> notiList(int startRow, int pageSize) {
		ArrayList<CsDTO> notiList = new ArrayList<>();
		// List csList = new ArrayList();

		System.out.println(" DAO : notiList() 실행");

		/*
		 * // 1. 드라이버로드 Class.forName(DRIVER); System.out.println(" 드라이버 로드 성공! "); //
		 * 2. 디비연결 Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
		 * System.out.println(" 디비연결 성공! ");
		 */

		// 1.2. 디비 연결
		try {
			con = getCon();
			sql = "select * from cs where noti_num is not NULL order by noti_num " + "limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow - 1); // (시작위치-1) startRow
			pstmt.setInt(2, pageSize); // 개수 pageSize 10
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터처리
			// rs(select문의 결과) -> 글 하나의 정보를 저장 객체 -> ArrayList 저장
			// BoardBean
			while (rs.next()) {
				// rs -> BoardBean 저장
				CsDTO bb = new CsDTO();

				bb.setNoti_num(rs.getInt("noti_num"));
				bb.setNoti_sub(rs.getString("noti_sub"));
				bb.setNoti_cont(rs.getString("noti_cont"));
				bb.setDate(rs.getDate("date"));

				// BoardBean -> ArrayList 한칸에 저장
				notiList.add(bb);

			} // while

			System.out.println(" 게시판 목록 조회 성공! ");
			System.out.println(notiList.size());

			System.out.println(" DAO : boardList() 실행");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}

		// 3. sql 구문 (select)& pstmt 객체
		return notiList;
	}

	// 글 리스트조회 - boardList()
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	// 공지사항 글쓰기 - insertCs();
	public void insertCs(CsDTO cb) throws Exception {
		// 글번호 저장하는 변수
		int noti_num = 0;

		// 사용자가 입력한 데이터를 DB에 저장
		// System.out.println(" 전달정보 : "+bb);
		System.out.println(" DAO : insertCs(cb) 호출-시작 ");

		/*
		 * // 1. 드라이버 로드 Class.forName(DRIVER); System.out.println(" 드라이버 로드 성공! "); //
		 * 2. 디비연결 Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
		 * System.out.println(" 디비연결 성공!! ");
		 */
		// 1.2. 디비연결
		con = getCon();

		// 3. SQL 구문 작성 & pstmt 객체
		// 글번호(bno) 계산하기
		sql = "select max(noti_num) from cs";
		pstmt = con.prepareStatement(sql);
		// 4. SQL 구문 실행
		rs = pstmt.executeQuery();
		// 5. 데이터 처리
		if (rs.next()) { // select문의 실행결과 커서가 있을때 게시판 작성된 글이 있음
			System.out.println(" @@@@@@@@@@ 게시판 글 있음 @@@@@@@");
			// bno = rs.getInt("bno"); (x)
			// rs.getInt() => 실행결과가 SQL NULL일때, 0을 리턴
			// bno = rs.getInt("max(bno)") + 1;
			// 1번 인덱스 컬럼의 값을 가져오기
			noti_num = rs.getInt(1) + 1;
		} else { // 게시판 작성된 글이 없음
			System.out.println(" @@@@@@@@@@ 게시판 글 없음 @@@@@@@");
			noti_num = 1;
		}
		System.out.println(" DAO : 글번호 - " + noti_num);

		// 글쓰기 동작 처리 (insert)
		// 3. sql 작성 & pstmt 객체
		sql = "insert into cs (noti_num,noti_sub,noti_cont,date) " + "values(?,?,?,now())";
		pstmt = con.prepareStatement(sql);
		// ???
		pstmt.setInt(1, noti_num);
		pstmt.setString(2, cb.getNoti_sub());
		pstmt.setString(3, cb.getNoti_cont());

		// 4. sql 실행
		pstmt.executeUpdate();
		System.out.println(" 글쓰기 완료! ");

		closeDB();

		System.out.println(" DAO : insertCs(bb) 호출-끝 ");
	} // 글쓰기 - insertCs();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// FAQ 글쓰기 - insertFaq();
	public void insertFaq(CsDTO dto) throws Exception {
		// 글번호 저장하는 변수
		int faq_num = 0;

		// 사용자가 입력한 데이터를 DB에 저장
		// System.out.println(" 전달정보 : "+bb);
		System.out.println(" DAO : insertFaq(bb) 호출-시작 ");

		/*
		 * // 1. 드라이버 로드 Class.forName(DRIVER); System.out.println(" 드라이버 로드 성공! "); //
		 * 2. 디비연결 Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
		 * System.out.println(" 디비연결 성공!! ");
		 */
		// 1.2. 디비연결
		con = getCon();

		// 3. SQL 구문 작성 & pstmt 객체
		// 글번호(bno) 계산하기
		sql = "select max(faq_num) from cs";
		pstmt = con.prepareStatement(sql);
		// 4. SQL 구문 실행
		rs = pstmt.executeQuery();
		// 5. 데이터 처리
		if (rs.next()) { // select문의 실행결과 커서가 있을때 게시판 작성된 글이 있음
			System.out.println(" @@@@@@@@@@ 게시판 글 있음 @@@@@@@");
			// bno = rs.getInt("bno"); (x)
			// rs.getInt() => 실행결과가 SQL NULL일때, 0을 리턴
			// bno = rs.getInt("max(bno)") + 1;
			// 1번 인덱스 컬럼의 값을 가져오기
			faq_num = rs.getInt(1) + 1;
		} else { // 게시판 작성된 글이 없음
			System.out.println(" @@@@@@@@@@ 게시판 글 없음 @@@@@@@");
			faq_num = 1;
		}
		System.out.println(" DAO : 글번호 - " + faq_num);

		// 글쓰기 동작 처리 (insert)
		// 3. sql 작성 & pstmt 객체
		sql = "insert into cs (faq_num,faq_sub,faq_cont,date) " + "values(?,?,?,now())";
		pstmt = con.prepareStatement(sql);
		// ???
		pstmt.setInt(1, faq_num);
		pstmt.setString(2, dto.getFaq_sub());
		pstmt.setString(3, dto.getFaq_cont());

		// 4. sql 실행
		pstmt.executeUpdate();
		System.out.println(" 글쓰기 완료! ");

		closeDB();

		System.out.println(" DAO : insertBoard(bb) 호출-끝 ");
	} // 글쓰기 - insertFaq();

//////////////////////////////////////////////////////////////////////////////////////////////////////

	// FAQ 글쓰기 - insertQna();
	public void insertQna(CsDTO dto) throws Exception {
		// 글번호 저장하는 변수
		int qna_num = 0;

		// 사용자가 입력한 데이터를 DB에 저장
		// System.out.println(" 전달정보 : "+qb);
		System.out.println(" DAO : insertQna(qb) 호출-시작 ");

		/*
		 * // 1. 드라이버 로드 Class.forName(DRIVER); System.out.println(" 드라이버 로드 성공! "); //
		 * 2. 디비연결 Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
		 * System.out.println(" 디비연결 성공!! ");
		 */
		// 1.2. 디비연결
		con = getCon();

		// 3. SQL 구문 작성 & pstmt 객체
		// 글번호(bno) 계산하기
		sql = "select max(qna_num) from cs";
		pstmt = con.prepareStatement(sql);
		// 4. SQL 구문 실행
		rs = pstmt.executeQuery();
		// 5. 데이터 처리
		if (rs.next()) { // select문의 실행결과 커서가 있을때 게시판 작성된 글이 있음
			System.out.println(" @@@@@@@@@@ 게시판 글 있음 @@@@@@@");
			// bno = rs.getInt("bno"); (x)
			// rs.getInt() => 실행결과가 SQL NULL일때, 0을 리턴
			// bno = rs.getInt("max(bno)") + 1;
			// 1번 인덱스 컬럼의 값을 가져오기
			qna_num = rs.getInt(1) + 1;
		} else { // 게시판 작성된 글이 없음
			System.out.println(" @@@@@@@@@@ 게시판 글 없음 @@@@@@@");
			qna_num = 1;
		}
		System.out.println(" DAO : 글번호 - " + qna_num);

		// 글쓰기 동작 처리 (insert)
		// 3. sql 작성 & pstmt 객체
		sql = "insert into cs (us_id,qna_num,qna_sub,qna_cont,date) " + "values(?,?,?,?,now())";
		pstmt = con.prepareStatement(sql);
		// ???
		pstmt.setString(1, dto.getUs_id());
		pstmt.setInt(2, qna_num);
		pstmt.setString(3, dto.getQna_sub());
		pstmt.setString(4, dto.getQna_cont());
//			pstmt.setDate(4, qb.getDate()); 

		// 4. sql 실행
		pstmt.executeUpdate();
		System.out.println(" 글쓰기 완료! ");

		closeDB();

		System.out.println(" DAO : insertQna(qb) 호출-끝 ");
	} // 글쓰기 - insertQna();

//////////////////////////////////////////////////////////////////////////////////////////////////

	// 자주묻는질문 글 개수 조회
	public int getFaqListCount() {

		System.out.println("DAO : getListCount() 호출");
		System.out.println("DAO : 실행목적 : 글의 개수(int) 리턴");
		int result = 0;

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. SQL 작성 & pstmt 객체

			sql = "select count(faq_num) from cs";
			pstmt = con.prepareStatement(sql);

			// 4. SQL 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				// result = rs.getInt(1); // 인덱스 사용으로 처리속도가 빠름
				result = rs.getInt("count(faq_num)");
			}
			System.out.println("DAO : 글의 개수 조회 완료!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}

	// 글 리스트조회 - faqList()
	public ArrayList<CsDTO> faqList(int startRow, int pageSize) throws Exception {
		ArrayList<CsDTO> faqList = new ArrayList<>();
		// List csList = new ArrayList();

		System.out.println(" DAO : faqList() 실행");

		/*
		 * // 1. 드라이버로드 Class.forName(DRIVER); System.out.println(" 드라이버 로드 성공! "); //
		 * 2. 디비연결 Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
		 * System.out.println(" 디비연결 성공! ");
		 */

		// 1.2. 디비 연결
		con = getCon();

		// 3. sql 구문 (select)& pstmt 객체
		sql = "select * from cs where faq_num is not NULL order by faq_num limit ?,?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, startRow - 1); // (시작위치-1) startRow
		pstmt.setInt(2, pageSize); // 개수 pageSize 10
		// 4. sql 실행
		rs = pstmt.executeQuery();
		// 5. 데이터처리
		// rs(select문의 결과) -> 글 하나의 정보를 저장 객체 -> ArrayList 저장
		// BoardBean
		while (rs.next()) {
			// rs -> BoardBean 저장
			CsDTO bb = new CsDTO();

			bb.setFaq_num(rs.getInt("faq_num"));
			bb.setFaq_sub(rs.getString("faq_sub"));
			bb.setFaq_cont(rs.getString("faq_cont"));
			bb.setDate(rs.getDate("date"));

			// BoardBean -> ArrayList 한칸에 저장
			faqList.add(bb);

		} // while

		System.out.println(" 게시판 목록 조회 성공! ");
		System.out.println(faqList.size());

		System.out.println(" DAO : boardList() 실행");
		return faqList;
	}
	// 글 리스트조회 - faqList()

//////////////////////////////////////////////////////////////////////////////////////////////////

	// qna 게시글 수 조회
	public int getQnaListCount() {

		System.out.println("DAO : getListCount() 호출");
		System.out.println("DAO : 실행목적 : 글의 개수(int) 리턴");
		int result = 0;

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. SQL 작성 & pstmt 객체

			sql = "select count(qna_num) from cs";
			pstmt = con.prepareStatement(sql);

			// 4. SQL 실행
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				// result = rs.getInt(1); // 인덱스 사용으로 처리속도가 빠름
				result = rs.getInt("count(qna_num)");
			}
			System.out.println("DAO : 글의 개수 조회 완료!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	
	
	// 글 리스트조회 - qnaList()
	public ArrayList<CsDTO> qnaList(int startRow, int pageSize) throws Exception {
		ArrayList<CsDTO> qnaList = new ArrayList<>();
		// List csList = new ArrayList();

		System.out.println(" DAO : qnaList() 실행");

		/*
		 * // 1. 드라이버로드 Class.forName(DRIVER); System.out.println(" 드라이버 로드 성공! "); //
		 * 2. 디비연결 Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
		 * System.out.println(" 디비연결 성공! ");
		 */

		// 1.2. 디비 연결
		con = getCon();

		// 3. sql 구문 (select)& pstmt 객체
		sql = "select * from cs where qna_num is not NULL order by qna_num limit ?,?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, startRow - 1); // (시작위치-1) startRow
		pstmt.setInt(2, pageSize); // 개수 pageSize 10
		// 4. sql 실행
		rs = pstmt.executeQuery();
		// 5. 데이터처리
		// rs(select문의 결과) -> 글 하나의 정보를 저장 객체 -> ArrayList 저장
		// BoardBean
		while (rs.next()) {
			// rs -> BoardBean 저장
			CsDTO bb = new CsDTO();
			
			bb.setUs_id(rs.getString("us_id"));
			bb.setQna_num(rs.getInt("qna_num"));
			bb.setQna_sub(rs.getString("qna_sub"));
			bb.setQna_cont(rs.getString("qna_cont"));
			bb.setDate(rs.getDate("date"));

			// BoardBean -> ArrayList 한칸에 저장
			qnaList.add(bb);

		} // while

		System.out.println(" 게시판 목록 조회 성공! ");
		System.out.println(qnaList.size());

		System.out.println(" DAO : boardList() 실행");
		return qnaList;
	}
	// 글 리스트조회 - faqList()

//////////////////////////////////////////////////////////////////////////////////////////////////

	// 글정보 (1개) 가져오기 - getNoti(noti_num)
	public CsDTO getNoti(int noti_num) {
		System.out.println(" DAO : 글정보 (1개) 가져오기 - getBoard(bno) 호출 ");
		CsDTO bb = null;
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성(select) & pstmt객체
			// sql -> bno에 해당하는 글정보만 조회
			sql = "select * from cs " + " where noti_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, noti_num);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 (rs -> 객체(BoardBean))
			if (rs.next()) {
				bb = new CsDTO();

				// rs -> bb 저장
				bb.setNoti_num(rs.getInt("noti_num"));
				bb.setNoti_sub(rs.getString("noti_sub"));
				bb.setNoti_cont(rs.getString("noti_cont"));
				bb.setDate(rs.getDate("date"));

			} // if
			System.out.println(" DAO : " + noti_num + "번 글정보 저장완료!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		// 글정보 리턴
		return bb;
	}
	// 글정보 (1개) 가져오기 - getNoti(noti_num)

///////////////////////////////////////////////////////////////////////////////////////		

	// 글정보 (1개) 가져오기 - getFaq(noti_num)
	public CsDTO getFaq(int faq_num) {
		System.out.println(" DAO : 글정보 (1개) 가져오기 - getBoard(bno) 호출 ");
		CsDTO bb = null;
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성(select) & pstmt객체
			// sql -> bno에 해당하는 글정보만 조회
			sql = "select * from cs " + " where faq_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, faq_num);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 (rs -> 객체(BoardBean))
			if (rs.next()) {
				bb = new CsDTO();

				// rs -> bb 저장
				bb.setFaq_num(rs.getInt("faq_num"));
				bb.setFaq_sub(rs.getString("faq_sub"));
				bb.setFaq_cont(rs.getString("faq_cont"));
				bb.setDate(rs.getDate("date"));

			} // if
			System.out.println(" DAO : " + faq_num + "번 글정보 저장완료!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		// 글정보 리턴
		return bb;
	}
	// 글정보 (1개) 가져오기 - getFaq(faq_num)
//////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////		

	// 글정보 (1개) 가져오기 - getQna(noti_num)
	public CsDTO getQna(int qna_num) {
		System.out.println(" DAO : 글정보 (1개) 가져오기 - getBoard(bno) 호출 ");
		CsDTO bb = null;
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성(select) & pstmt객체
			// sql -> bno에 해당하는 글정보만 조회
			sql = "select * from cs " + " where qna_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 (rs -> 객체(BoardBean))
			if (rs.next()) {
				bb = new CsDTO();

				// rs -> bb 저장
				bb.setQna_num(rs.getInt("qna_num"));
				bb.setQna_sub(rs.getString("qna_sub"));
				bb.setQna_cont(rs.getString("qna_cont"));
				bb.setDate(rs.getDate("date"));
				bb.setQna_re(rs.getString("qna_re"));
				bb.setQna_redate(rs.getDate("qna_redate"));
				bb.setUs_id(rs.getString("us_id")); 
				bb.setOwn_id(rs.getString("own_id"));

			} // if
			System.out.println(" DAO : " + qna_num + "번 글정보 저장완료!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		// 글정보 리턴
		return bb;
	}
	// 글정보 (1개) 가져오기 - getFaq(faq_num)
//////////////////////////////////////////////////////////////////////////////////////////////

	// 글 정보 삭제하기 - deleteQna(bb)
	public int deleteQna(int qna_num) {
		int result = -1; // -1은 초기값, 1은 성공적으로 삭제, 0은 해당 글이 없을 때를 나타냅니다.

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			// 1.2. 디비연결
			con = getCon(); // getConnection() 메서드를 통해 DB에 연결합니다.

			// 3. sql 구문 작성 및 pstmt 객체 생성
			sql = "delete from cs where qna_num=?";
			pstmt = con.prepareStatement(sql);

			// qna_num 값을 설정
			pstmt.setInt(1, qna_num);

			// 4. sql문 실행
			int count = pstmt.executeUpdate(); // executeUpdate는 성공적으로 변경된 행의 수를 반환합니다.

			if (count > 0) { // 성공적으로 행이 삭제되었다면
				System.out.println(" DAO : 글 정보 삭제완료! ");
				result = 1;
			} else { // 해당 번호의 게시글이 없는 경우
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// DB 자원 해제
			closeDB();
		}

		return result;
	}
	// 글 정보 삭제하기 - deleteQna(bb)

//////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////

	// 답글 쓰기 - QnaReply(bb)

	public boolean QnaReply(int qna_num, String qna_re) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getCon();
			String sql = "UPDATE cs SET qna_re=?, qna_redate=NOW() WHERE qna_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qna_re);
			pstmt.setInt(2, qna_num);

			int rowCount = pstmt.executeUpdate();

			if (rowCount > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}

	// 답글 쓰기 - reInsertBoard(bb)

////////////////////////////////////////////////////////////////////////////////////////////////////////			

	// 글 정보 삭제하기 - deleteFaq(bb)
	public int deleteFaq(int faq_num) {
		int result = -1; // -1은 초기값, 1은 성공적으로 삭제, 0은 해당 글이 없을 때를 나타냅니다.

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			// 1.2. 디비연결
			con = getCon(); // getConnection() 메서드를 통해 DB에 연결합니다.

			// 3. sql 구문 작성 및 pstmt 객체 생성
			sql = "delete from cs where faq_num=?";
			pstmt = con.prepareStatement(sql);

			// qna_num 값을 설정
			pstmt.setInt(1, faq_num);

			// 4. sql문 실행
			int count = pstmt.executeUpdate(); // executeUpdate는 성공적으로 변경된 행의 수를 반환합니다.

			if (count > 0) { // 성공적으로 행이 삭제되었다면
				System.out.println(" DAO : 글 정보 삭제완료! ");
				result = 1;
			} else { // 해당 번호의 게시글이 없는 경우
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			closeDB();
			
			
		}

		return result;
	}
	// 글 정보 삭제하기 - deleteQna(bb)

//////////////////////////////////////////////////////////////////////////////////////////////////

	// 글 정보 수정하기 - faqEdit(bb)
	public int faqEdit(CsDTO bb) {
		int result = 0; // 0(에러) 1(정상)

		try {
			// 1.2. 디비연결
			con = getCon();

			// 3. sql 구문 실행(update)
			sql = "update cs set faq_sub=?, faq_cont=? where faq_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bb.getFaq_sub());
			pstmt.setString(2, bb.getFaq_cont());
			pstmt.setInt(3, bb.getFaq_num());

			// 4. sql문 실행
			int updatedRows = pstmt.executeUpdate();

			if (updatedRows > 0) {
				System.out.println(" DAO : 글 정보 수정완료! ");
				result = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}
	// 글 정보 수정하기 - faqEdit(bb)

//////////////////////////////////////////////////////////////////////////////////

	// 글 정보 삭제하기 - deleteNoti(bb)
	public int deleteNoti(int noti_num) {
		int result = -1; // -1은 초기값, 1은 성공적으로 삭제, 0은 해당 글이 없을 때를 나타냅니다.

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			// 1.2. 디비연결
			con = getCon(); // getConnection() 메서드를 통해 DB에 연결합니다.

			// 3. sql 구문 작성 및 pstmt 객체 생성
			sql = "delete from cs where noti_num=?";
			pstmt = con.prepareStatement(sql);

			// qna_num 값을 설정
			pstmt.setInt(1, noti_num);

			// 4. sql문 실행
			int count = pstmt.executeUpdate(); // executeUpdate는 성공적으로 변경된 행의 수를 반환합니다.

			if (count > 0) { // 성공적으로 행이 삭제되었다면
				System.out.println(" DAO : 글 정보 삭제완료! ");
				result = 1;
			} else { // 해당 번호의 게시글이 없는 경우
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// DB 자원 해제
			closeDB();
		}

		return result;
	}
	// 글 정보 삭제하기 - deleteNoti(bb)

////////////////////////////////////////////////////////////////////////////////////////

	// 글 정보 수정하기 - notiEdit(bb)
	public int notiEdit(CsDTO bb) {
		int result = 0; // 0(에러) 1(정상)

		try {
			// 1.2. 디비연결
			con = getCon();

			// 3. sql 구문 실행(update)
			sql = "update cs set noti_sub=?, noti_cont=? where noti_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bb.getNoti_sub());
			pstmt.setString(2, bb.getNoti_cont());
			pstmt.setInt(3, bb.getNoti_num());

			// 4. sql문 실행
			int updatedRows = pstmt.executeUpdate();

			if (updatedRows > 0) {
				System.out.println(" DAO : 글 정보 수정완료! ");
				result = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}
	// 글 정보 수정하기 - faqEdit(bb)

//////////////////////////////////////////////////////////////////////////////////

}
