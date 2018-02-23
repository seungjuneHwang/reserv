package com.test.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReservationDAO {
	public DataSource dataFactory;
	private String sql;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int cnt;

	public ReservationDAO() {// 생성자
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql");
			conn = dataFactory.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// 생성자

	public void reservationInsert(ReservationDTO reservationDTO) {// 추가
		try {
			sql = "insert into boxing(ch) values(?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservationDTO.getCh());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// 추가

	public void reservationSearch(ReservationDTO reservationDTO) {
		try {
			sql = "select ch, isch from boxing";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				reservationDTO.getCh();
				reservationDTO.getIsch();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void reservationUpdate(ReservationDTO reservationDTO) {
		try {
			sql = "update boxing set isch=? where ch=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservationDTO.getIsch());
			pstmt.setString(2, reservationDTO.getCh());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
