package com.tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.tp.vo.MD;

public class MDDAO {

	private static MDDAO dao = new MDDAO();

	private MDDAO() {
	}

	public static MDDAO getInstance() {
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

	// ȸ�� �߰�
	public void MDInsert(MD md) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into movie values(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, 0);
			pstmt.setString(2, md.getMtitle());
			pstmt.setInt(3, md.getMcode());
			pstmt.setString(4, md.getLink());
			pstmt.setString(5, md.getImage());
			pstmt.setString(6, md.getSubtitle());
			pstmt.setString(7, md.getPubDate());
			pstmt.setString(8, md.getDirector());
			pstmt.setString(9, md.getActor());
			pstmt.setString(10, md.getUserRating());
			pstmt.setString(11, md.getGenre());
			pstmt.setString(12, md.getInfo());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("alter table movie AUTO_INCREMENT=1;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("SET @CNT = 0;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("update movie SET movie.mid = @CNT:=@CNT+1;");
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	// ȸ�� �˻�
	public MD MDSearch(String Mtitle) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MD md = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from movie where Mtitle=?");
			pstmt.setString(1, Mtitle);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				md = new MD();
				md.setMid(rs.getInt(1));
				md.setMtitle(rs.getString(2));
				md.setMcode(rs.getInt(3));
				md.setLink(rs.getString(4));
				md.setImage(rs.getString(5));
				md.setSubtitle(rs.getString(6));
				md.setPubDate(rs.getString(7));
				md.setDirector(rs.getString(8));
				md.setActor(rs.getString(9));
				md.setUserRating(rs.getString(10));
				md.setGenre(rs.getString(11));
				md.setInfo(rs.getString(12));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			close(conn, pstmt, rs);
		}
		return md;
	}

	// ȸ�� �߰�
	public void MDUpdate(MD md) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement(
					"UPDATE movie SET Mtitle=?, Mcode=?, link=?, image=?, subtitle=?, pubDate=?, director=?, actor=?, userRating=?, genre=?, info=?"
							+ " where mid=?");
			pstmt.setString(1, md.getMtitle());
			pstmt.setInt(2, md.getMcode());
			pstmt.setString(3, md.getLink());
			pstmt.setString(4, md.getImage());
			pstmt.setString(5, md.getSubtitle());
			pstmt.setString(6, md.getPubDate());
			pstmt.setString(7, md.getDirector());
			pstmt.setString(8, md.getActor());
			pstmt.setString(9, md.getUserRating());
			pstmt.setString(10, md.getGenre());
			pstmt.setString(11, md.getInfo());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	// MD ����
	public void MDDelete(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("DELETE from movie where Mtitle=?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("alter table movie AUTO_INCREMENT=1;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("SET @CNT = 0;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("update movie SET movie.mid = @CNT:=@CNT+1;");
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	// ȸ�� ����Ʈ
	public ArrayList<MD> MDList() {
		ArrayList<MD> list = new ArrayList<MD>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MD md = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("SELECT * from movie");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				md = new MD();
				md.setMid(rs.getInt(1));
				md.setMtitle(rs.getString(2));
				md.setMcode(rs.getInt(3));
				md.setLink(rs.getString(4));
				md.setImage(rs.getString(5));
				md.setSubtitle(rs.getString(6));
				md.setPubDate(rs.getString(7));
				md.setDirector(rs.getString(8));
				md.setActor(rs.getString(9));
				md.setUserRating(rs.getString(10));
				md.setGenre(rs.getString(11));
				md.setInfo(rs.getString(12));
				list.add(md);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list;
	}

}
