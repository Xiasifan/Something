package Dao;



import Bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
	public User login(Connection con, User user) throws  Exception{
		User resultUser = null;
		String sql = "select  * from t_user where userName=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,user.getUserName());
		pstmt.setString(2,user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("username"));
			resultUser.setPassword(rs.getString("password"));

		}
		return resultUser;

	}
	public User login(Connection connection,String userName) throws Exception {
		User user = null;
		String sql = "select * from t_user where userName=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,user.getUserName());
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			user = new User();
			user.setId(resultSet.getInt("id"));
			user.setUserName("userName");
			user.setPassword("password");
		}
		return user;
	}
}
