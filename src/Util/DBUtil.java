package Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private String duUrl="jdbc:mysql://localhost:3306/db_StudentInfo?useSSL=false";
	private String dbUserName = "root";
	private String dbPassWord = "123456";
	private String jdbcName="com.mysql.jdbc.Driver";


	public void CloseCon(Connection connection) throws Exception{
		if (connection != null) {
			connection.close();
		}
	}
	public Connection GetCon() throws Exception{
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(duUrl, dbUserName, dbPassWord);
		return con;
	}
	public static void main(String[] args){
		DBUtil dbUtil = new DBUtil();
		try {
			dbUtil.GetCon();
			System.out.println("连接成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
