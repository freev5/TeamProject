package com.tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.tp.vo.Account;
import com.tp.vo.WishList;

public class WishListDAO {

	private static WishListDAO dao = new WishListDAO();

	private WishListDAO() {
	}

	public static WishListDAO getInstance() {
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
	
	// 검색
		public WishList WishSearch(String id) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			WishList wish = null;

			try {
				conn = connect();
				pstmt = conn.prepareStatement("select * from WishList where id=? and Mtitle=?");
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					wish = new WishList();
					wish.setWid(rs.getString(1));
					wish.setId(rs.getString(2));
					wish.setMtitle(rs.getString(3));
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				close(conn, pstmt, rs);
			}
			return wish;
		}
		
	// 회원 추가
	public void WishInsert(String id, String mtitle, String mcode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into wishlist values(?,?,?,?,now())");
			pstmt.setInt(1, 0);
			pstmt.setString(2, id);
			pstmt.setString(3, mtitle);
			pstmt.setInt(4, Integer.parseInt(mcode));
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("alter table wishlist AUTO_INCREMENT=1;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("SET @CNT = 0;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("update wishlist SET wishlist.wid = @CNT:=@CNT+1;");
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
	
	public void WishDelete(String id, String Mtitle) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("DELETE from wishlist where id=? and Mtitle=?");
			pstmt.setString(1, id);
			pstmt.setString(2, Mtitle);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("alter table wishlist AUTO_INCREMENT=1;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("SET @CNT = 0;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("update wishlist SET wishlist.wid = @CNT:=@CNT+1;");
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
	
	public ArrayList<WishList> WishList(String id) {
		ArrayList<WishList> list = new ArrayList<WishList>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		WishList wishlist = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("SELECT * from wishlist where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				wishlist = new WishList();
				wishlist.setWid(rs.getString(1));
				wishlist.setId(rs.getString(2));
				wishlist.setMtitle(rs.getString(3));
				wishlist.setMcode(rs.getString(4));
				wishlist.setWishdate(rs.getString(5));
				list.add(wishlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}
}
