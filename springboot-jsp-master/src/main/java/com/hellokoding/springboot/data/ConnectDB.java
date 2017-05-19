package com.hellokoding.springboot.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
	Connection connect = null;
	Statement stmt = null;
	ResultSet rs = null;

	protected void hasDriver() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			throw new Exception("Invalid Driver!!Please check this drver....");
		}
	}

	public Connection openConnect() throws Exception {
		if (connect == null) {
			hasDriver();
			String url = String.format("jdbc:mysql://google/%s?cloudSqlInstance=%s&"
				            + "socketFactory=com.google.cloud.sql.mysql.SocketFactory",
				            "ckeditor","icsse-nhom8-chieu6:asia-northeast1:mysql");
			// url_sql = "....";
			//String url = "jdbc:mysql://localhost/ckeditor?useUnicode=true&characterEncoding=utf-8";
			try {
				this.connect = DriverManager.getConnection(url, "root",
						"123456");
			} catch (SQLException e) {
				throw new Exception(e.getMessage()
						+ "Connect failed to database .... ");
			}
		}
		return connect;
	}
	public String GetContent() throws Exception{
		ResultSet rs = null;
		String strSQL = "select * from tbl_edit";
		String content = "";
		try {
			 rs = openConnect().createStatement().executeQuery(strSQL);
			while (rs.next()) {
				content = rs.getString("noidung");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage() + " Error at : " + strSQL);
		}
		return content;
	}
	public boolean UpdateContent(String noidung) throws SQLException, Exception{
		String sql = "update tbl_edit set noidung=?";
		PreparedStatement pst = openConnect().prepareStatement(sql);
		pst.setString(1, noidung);
		return pst.executeUpdate() > 0;
	}
	
	public void closeConnet() throws SQLException {
		if (rs != null && !rs.isClosed())
			rs.close();
		if (stmt != null)
			stmt.close();
		if (connect != null)
			connect.close();
	}
}
