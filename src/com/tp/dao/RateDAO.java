package com.tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.tp.vo.Account;
import com.tp.vo.Rate;
import com.tp.vo.Ratedirector;

public class RateDAO {

	private static RateDAO dao = new RateDAO();

	private RateDAO() {
	}

	public static RateDAO getInstance() {
		return dao;
	}

	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/tpro?serverTimezone=UTC&useSSL=false";
			conn = DriverManager.getConnection(url, "root", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		close(conn, ps);
	}

	public void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// 회원 추가
	public void RateInsert(String id, String Mtitle, String Rating, String Mcode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			if(Rating==null) {
			Rating = "1";	
			}
			conn = connect();
			pstmt = conn.prepareStatement("insert into rate values(?,?,?,?,?,now())");
			pstmt.setInt(1, 0);
			pstmt.setString(2, id);
			pstmt.setString(3, Mtitle);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, Integer.parseInt(Rating));
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("alter table rate AUTO_INCREMENT=1;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("SET @CNT = 0;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("update rate SET rate.Rid = @CNT:=@CNT+1;");
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	// 회원 검색
	public Rate RateSearch(String id, String Mtitle) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Rate rate = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from rate where id=? and Mtitle=?");
			pstmt.setString(1, id);
			pstmt.setString(2, Mtitle);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rate = new Rate();
				rate.setRid(rs.getString(1));
				rate.setId(rs.getString(2));
				rate.setMtitle(rs.getString(3));
				rate.setMcode(rs.getString(4));
				rate.setRating(rs.getString(5));
				rate.setRateDate(rs.getString(6));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			close(conn, pstmt, rs);
		}
		return rate;
	}

	// 회원 추가
	public void RateUpdate(String id, String Mtitle, String Rating) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("UPDATE rate SET Rating=?, ratedate=now() where id=? and Mtitle=?");
			pstmt.setString(1, Rating);
			pstmt.setString(2, id);
			pstmt.setString(3, Mtitle);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	// 회원 추가
	public void RateDelete(String id,String Mtitle) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("DELETE from rate where id=? and Mtitle=?");
			pstmt.setString(1, id);
			pstmt.setString(2, Mtitle);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("alter table rate AUTO_INCREMENT=1;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("SET @CNT = 0;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("update rate SET rate.Rid = @CNT:=@CNT+1;");
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
	
	// 회원 리스트
		public ArrayList<Rate> RateList(String id) {
			ArrayList<Rate> list = new ArrayList<Rate>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			Rate rate = null;
			try {
				conn = connect();
				pstmt = conn.prepareStatement("SELECT * from rate where id=?");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					rate = new Rate();
					rate.setRid(rs.getString(1));
					rate.setId(rs.getString(2));
					rate.setMtitle(rs.getString(3));
					rate.setMcode(rs.getString(4));
					rate.setRating(rs.getString(5));
					rate.setRateDate(rs.getString(6));
					list.add(rate);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}
			return list;
		}

		//
		public ArrayList<Ratedirector> RateDirector(String id, String director) {
			ArrayList<Ratedirector> list = new ArrayList<Ratedirector>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			Ratedirector rd = null;
			try {
				conn = connect();
				pstmt = conn.prepareStatement("SELECT count(director),director FROM tpro.rate, tpro.movie where rate.Mtitle = movie.Mtitle and rate.id = '"+id+"' and director like '%"+director+" %' order by count(director)");
				rs = pstmt.executeQuery();
				while(rs.next()) {
					rd = new Ratedirector();
					rd.setCount(rs.getInt(1));
					rd.setDirector(rs.getString(2));
					list.add(rd);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}
			return list;
		}
}
