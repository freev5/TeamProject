package com.tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.tp.vo.Account;

public class AccountDAO {

	private static AccountDAO dao = new AccountDAO();

	private AccountDAO() {
	}

	public static AccountDAO getInstance() {
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
	public void AccountRegist(Account account) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into account values(?,?,?,?,?,?,now())");
			pstmt.setInt(1, 0);
			pstmt.setString(2, account.getId());
			pstmt.setString(3, account.getPwd());
			pstmt.setString(4, account.getName());
			pstmt.setString(5, account.getBirth());
			pstmt.setString(6, account.getEmail());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	// 회원 검색
	public Account AccountSearch(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Account account = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from account where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				account = new Account();
				account.setAid(rs.getString(1));
				account.setId(rs.getString(2));
				account.setPwd(rs.getString(3));
				account.setName(rs.getString(4));
				account.setBirth(rs.getString(5));
				account.setEmail(rs.getString(6));
				account.setRegdate(rs.getString(7));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			close(conn, pstmt, rs);
		}
		return account;
	}

	// 회원 추가
	public void AccountUpdate(Account account) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("UPDATE account SET pwd=?, name=?, email=?, birth=? where id=?");
			pstmt.setString(1, account.getPwd());
			pstmt.setString(2, account.getName());
			pstmt.setString(3, account.getEmail());
			pstmt.setString(4, account.getBirth());
			pstmt.setString(5, account.getId());
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}

	// 회원 추가
	public void AccountDelete(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("DELETE from account where id=?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("alter table account AUTO_INCREMENT=1;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("SET @CNT = 0;");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("update account SET account.aid = @CNT:=@CNT+1;");
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
	}
	
	// 회원 리스트
		public ArrayList<Account> AccountList() {
			ArrayList<Account> list = new ArrayList<Account>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			Account account = null;
			try {
				conn = connect();
				pstmt = conn.prepareStatement("SELECT * from account");
				rs = pstmt.executeQuery();
				while(rs.next()) {
					account = new Account();
					account.setAid(rs.getString(1));
					account.setId(rs.getString(2));
					account.setPwd(rs.getString(3));
					account.setName(rs.getString(4));
					account.setBirth(rs.getString(5));
					account.setEmail(rs.getString(6));
					account.setRegdate(rs.getString(7));
					list.add(account);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}
			return list;
		}

}
