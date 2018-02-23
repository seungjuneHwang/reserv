package com.test.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
	
	// 좌석 선택 후 자리 비움
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
	
	// 전체 자리 유무 상태
	public HashMap<String, ReservationDTO> reservationStatusMap() {
		HashMap<String, ReservationDTO> map = new HashMap<>();
		try {
			sql = "select * from boxing";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReservationDTO reservationDTO = new ReservationDTO();
				reservationDTO.setIdx(rs.getInt("idx"));
				reservationDTO.setCh(rs.getString("ch"));
				reservationDTO.setIsch(rs.getInt("isch"));
				map.put(rs.getString("ch"), reservationDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	// 전체 자리 유무 상태
	public ArrayList<ReservationDTO> reservationStatusList() {
		ArrayList<ReservationDTO> list = new ArrayList<>();
		try {
			sql = "select * from boxing";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReservationDTO reservationDTO = new ReservationDTO();
				reservationDTO.setIdx(rs.getInt("idx"));
				reservationDTO.setCh(rs.getString("ch"));
				reservationDTO.setIsch(rs.getInt("isch"));
				list.add(reservationDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * 
Drop table boxing

CREATE TABLE `boxing` (
	`idx` INT(11) NOT NULL AUTO_INCREMENT,
	`ch` VARCHAR(10) NOT NULL,
	`isch` TINYINT(4) NOT NULL DEFAULT '0',
	PRIMARY KEY (`idx`),
	UNIQUE INDEX `ch` (`ch`)
)
	 */
	public void reservationDrop() {
		try {
			sql = "Drop table boxing";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void reservationCreate() {
		try {
			sql = "CREATE TABLE `boxing` (\r\n" + 
					"	`idx` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"	`ch` VARCHAR(10) NOT NULL,\r\n" + 
					"	`isch` TINYINT(4) NOT NULL DEFAULT '0',\r\n" + 
					"	PRIMARY KEY (`idx`),\r\n" + 
					"	UNIQUE INDEX `ch` (`ch`)\r\n" + 
					")";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
